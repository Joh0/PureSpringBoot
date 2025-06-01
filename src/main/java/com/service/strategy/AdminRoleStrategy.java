package com.service.strategy;


public class AdminRoleStrategy implements RoleStrategy {
	@Override
	public String performRoleAction() {
		return "Able to delete users!";
	}
}
