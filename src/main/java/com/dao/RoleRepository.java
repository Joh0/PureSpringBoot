package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bean.Role;
import com.bean.RoleType;
import com.bean.User;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	
	//"Users" not "User" is the attribute in Role entity
	List<Role> findAllRolesByUsers(User user);
	
	Role findRoleByRoleType(RoleType roleType);

}
