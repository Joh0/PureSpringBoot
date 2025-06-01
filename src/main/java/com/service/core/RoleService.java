package com.service.core;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Role;
import com.bean.RoleType;
import com.bean.User;
import com.dao.RoleRepository;

@Service
public class RoleService {

	private final RoleRepository roleRepository;

	@Autowired
	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	public List<Role> getAllRoles(User user){
		return this.roleRepository.findAllRolesByUsers(user);
	}
	
	public Role findRoleByRoleType(RoleType roleType) {
		return this.roleRepository.findRoleByRoleType(roleType);
	}
	
	public Role createRole(Role role){
		return this.roleRepository.save(role);
	}
	
	public void deleteRole(Role role){
		this.roleRepository.delete(role);
	}

	
	
}
