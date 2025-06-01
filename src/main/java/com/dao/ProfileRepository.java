package com.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bean.Profile;
import com.bean.User;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long>{
	//Spring Data JPA reads the method name as "findByFieldName(Type fieldName)" and generates SQL based on it.
	Profile getProfileByUser(User user);
}
