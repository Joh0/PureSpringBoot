package com.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bean.Post;
import com.bean.User;
import com.service.core.PostService;
import com.service.core.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/post/")
public class PostController {
	
	private final PostService postService;
	private final UserService userService;
	
	public PostController(PostService postService, UserService userService) {
		this.postService = postService;
		this.userService = userService;
	}
	
	@GetMapping("createPostPage")
	public String createPostPage() {
		return "postCreation";
	}
	
	@PostMapping("submitForm")
	public String createPost(@RequestParam String title, @RequestParam String content, RedirectAttributes redirectAttributes, HttpSession session, Model model) {
		Post post = new Post();
		post.setTitle(title);
		post.setContent(content);
		String username = (String) session.getAttribute("user");
		User user = userService.findUserByUsername(username);
		post.setUser(user);
		postService.createPost(post);
		model.addAttribute("message", "Post successfully created");
		return "success";
	}
	
	@GetMapping("postsPage")
	public String listAllPosts(Model model, HttpSession session) {
		String username = (String) session.getAttribute("user");
		User user = userService.findUserByUsername(username);
		List<Post> posts = postService.getAllPosts(user);
		model.addAttribute("postsList", posts);
		return "posts";
	}
	
	@GetMapping("othersPostsPage")
	public String listAllPosts(Model model, @RequestParam String username) {
		User user = userService.findUserByUsername(username);
		List<Post> posts = postService.getAllPosts(user);
		model.addAttribute("postsList", posts);
		model.addAttribute("username", username);
		return "othersPosts";
	}

}
