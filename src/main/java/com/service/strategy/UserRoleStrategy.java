package com.service.strategy;


public class UserRoleStrategy implements RoleStrategy{

	@Override
	public String performRoleAction() {
		return "Able to create, delete and edit posts";
	}

	
}
