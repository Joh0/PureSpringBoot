package com.web;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bean.Role;
import com.bean.RoleType;
import com.bean.User;
import com.service.core.RoleService;
import com.service.core.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

	private final UserService userService;
	private final RoleService roleService;
	
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public UserController(UserService userService, RoleService roleService) {
		this.userService = userService;
		this.roleService = roleService;
	}
	
	@GetMapping("/registerUserPage")
	public String registerUserPage() {
		return "registerUser";
	}
	
	@PostMapping("/registerUser")
	public String registerOutcome(@RequestParam String username, @RequestParam String email, @RequestParam String password, @RequestParam Set<String> roles, RedirectAttributes redirectAttributes){
		
		System.out.println("Username: " + username);
		System.out.println("Email: " + email);
		System.out.println("Password: " + password);
		System.out.println("Printing roles: ");
		roles.forEach(role -> System.out.println(role));
		
		String encryptedPassword = passwordEncoder.encode(password);
		
		User user = new User();
		
		//Ensure that user object has a HashSet of roles initiated, otherwise you won't be able to call add roles method.
		if(user.getRoles() == null) {
			user.setRoles(new HashSet<>());
		}
		
		user.setId(0);
		user.setUsername(username);
		user.setPassword(encryptedPassword);
		user.setEmail(email);
		for(String roleString : roles) {
			RoleType roleType = RoleType.valueOf(roleString.toUpperCase());
			Role role = roleService.findRoleByRoleType(roleType);
			
			
			//Important to save the role
			if(role == null) {
				role = new Role(); // Only create new Role object if the role currently doesn't exist
				role.setRoleType(roleType);
				roleService.createRole(role);
			}
			user.addRole(role);
		}
		
		userService.createUser(user);
		
		redirectAttributes.addFlashAttribute("message", "User " + username + " registered successfully!");
		return "redirect:/user/success";
		
	}
	
	@GetMapping("/success")
	public String registerSuccess() {
		return "success";
	}	
	
	@GetMapping("/loginPage")
	public String loginPage() {
		return "login";
	}
	
	//tim
	//pass
	@PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password, Model model, RedirectAttributes redirectAttributes, HttpSession session) {
		User user = userService.findUserByUsername(username);
		if(user == null) {
			redirectAttributes.addFlashAttribute("message", "Username " + username + "not found, please register!");
			return "redirect:/user/loginPage";
		}
		String storedPassword = user.getPassword();
		boolean matches = passwordEncoder.matches(password, storedPassword);
		if(!matches) {
			redirectAttributes.addFlashAttribute("message", "Incorrect password for " + username);
			return "redirect:/user/loginPage";
		}
		redirectAttributes.addFlashAttribute("message", "User " + username + " login successful!");
		
		session.setAttribute("user", username);
		return "redirect:/user/success";
	}
	
	@GetMapping("/logout")
	public String logout(RedirectAttributes redirectAttributes, HttpSession session) {
		redirectAttributes.addFlashAttribute("message", "Log Out successful!");
		session.removeAttribute("user");
		return "redirect:/user/loginPage";
		
	}
	
	@GetMapping("/displayUsersPage")
	public String displayAllUsers(Model model, HttpSession session) {
		List<User> userList = userService.findAllUsers();
		String username = (String) session.getAttribute("user");
		User user = userService.findUserByUsername(username);
		userList.remove(user);
		model.addAttribute("userList", userList);
		return "displayUsers";
	}
	
}
