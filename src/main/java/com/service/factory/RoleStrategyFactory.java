package com.service.factory;

import com.bean.RoleType;
import com.service.strategy.AdminRoleStrategy;
import com.service.strategy.RoleStrategy;
import com.service.strategy.UserRoleStrategy;

public class RoleStrategyFactory {

	public static RoleStrategy getRoleStrategy(RoleType roleType) {
		switch(roleType) {
			case ADMIN:
				return new AdminRoleStrategy();
			case USER:
				return new UserRoleStrategy();
			default:
				throw new IllegalArgumentException("Unknown Role Type: " + roleType);
		}
	}
}
