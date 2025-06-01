package com.bean;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    // Many-to-Many with User (Many Users can have Many Roles)
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

	public Role(Long id, RoleType roleType, Set<User> users) {
		super();
		this.id = id;
		this.roleType = roleType;
		this.users = users;
	}

	public Role() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoleType getRoleType() {
		return roleType;
	}

	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
    
	
    
    
    
    
}