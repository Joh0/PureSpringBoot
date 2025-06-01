package com.service.core;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Post;
import com.bean.User;
import com.dao.PostRepository;

@Service
public class PostService {
	
	private final PostRepository postRepository;

	// There will be an implementation class of postRepository that overrides the methods of postRepository interface so the service need not do so.
	@Autowired
	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
	// Have to get posts by a particular user only
	public List<Post> getAllPosts(User user){
		return postRepository.findAllByUser(user);
	}
	
	public Optional<Post> getPostById(Long id){
		return postRepository.findById(id);
	}
	
	public Post createPost(Post post) {
		return postRepository.save(post);
	}
	
	public void deletePost(Long id) {
		postRepository.deleteById(id);
	}

}
