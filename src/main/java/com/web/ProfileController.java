package com.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bean.Profile;
import com.bean.User;
import com.service.core.ProfileService;
import com.service.core.UserService;
import com.service.strategy.RoleStrategy;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/profile")
public class ProfileController {

	private final ProfileService profileService;
	private final UserService userService;
	
	public ProfileController(ProfileService profileService, UserService userService) {
		this.profileService = profileService;
		this.userService = userService;
	}


	@GetMapping("/profilePage")
	public String displayProfile(Model model, HttpSession session) {
		String username = (String) session.getAttribute("user");
		User user = userService.findUserByUsername(username);
		Profile profile = profileService.getProfileByUser(user);
		model.addAttribute("profile", profile);
		// Role strategies
		List<String> roleStategyActionList = user.executeRoleActions();
		model.addAttribute("roleStategyActionList", roleStategyActionList);
		
		return "profilePage";
	}
	
	@GetMapping("/editProfilePage")
	public String editProfilePage(Model model, HttpSession session) {
		String username = (String) session.getAttribute("user");
		User user = userService.findUserByUsername(username);
		Profile profile = profileService.getProfileByUser(user);
		if(profile == null) {
			profile = new Profile();
			profile.setId(0L);
		}
		model.addAttribute("profile", profile);
		System.out.println("Session user: " + session.getAttribute("user"));
		return "editProfile";
	}
	
	@PostMapping("/editProfile")
	public String editProfile(@ModelAttribute Profile profile, Model model, HttpSession session) {
		String username = (String) session.getAttribute("user");
		if (profile.getUser() == null) {
			User user = userService.findUserByUsername(username);
			profile.setUser(user);
		}
		
		profileService.createProfile(profile);
		model.addAttribute("message", username + "'s Profile Edited Successfully!");
		return "success";
	}
}
