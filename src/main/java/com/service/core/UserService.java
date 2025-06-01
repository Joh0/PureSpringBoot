package com.service.core;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.User;
import com.dao.UserRepository;

@Service
public class UserService {

	
	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<User> findAllUsers(){
		return this.userRepository.findAll();
	}
	
	public User findUserByUsername(String username){
		return this.userRepository.findUserByUsername(username);
	}
	
	public User createUser(User user) {
		return this.userRepository.save(user);
	}
	
	public void deleteUser(User user) {
		this.userRepository.delete(user);
	}
	
}
