package com.service.core;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Profile;
import com.bean.User;
import com.dao.ProfileRepository;

@Service
public class ProfileService{

	private final ProfileRepository profileRepository;

	
	@Autowired
	public ProfileService(ProfileRepository profileRepository) {
		this.profileRepository = profileRepository;
	}
	
	public Profile getProfileByUser(User user){
		return this.profileRepository.getProfileByUser(user);
	}
	
	public Profile createProfile(Profile profile) {
		return this.profileRepository.save(profile);
	}
	
	public void deleteProfile(Long id) {
		this.profileRepository.deleteById(id);
	}
	
}
