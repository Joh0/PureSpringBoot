package com.bean;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.service.factory.RoleStrategyFactory;
import com.service.strategy.RoleStrategy;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, unique = true)
	private String username;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Profile profile;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Post> posts;
	
	@ManyToMany()
	@JoinTable(
		name = "user_roles",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<Role> roles;
	
	@Transient
	private RoleStrategy rolestrategy;
	
	public List<RoleStrategy> getRoleStrategies() {
		return roles.stream()
				.map(role -> RoleStrategyFactory.getRoleStrategy(role.getRoleType()))
				.collect(Collectors.toList());
	}
	
	public List<String> executeRoleActions() {
	    return getRoleStrategies().stream()
	            .map(roleStrategy -> roleStrategy.performRoleAction())         
	            .collect(Collectors.toList());
	}
	

	public User(long id, String username, String email, String password, Profile profile, List<Post> posts,
			Set<Role> roles, RoleStrategy rolestrategy) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.profile = profile;
		this.posts = posts;
		this.roles = roles;
		this.rolestrategy = rolestrategy;
	}

	public User() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	public void addPost(Post post) {
	    this.posts.add(post);
	    post.setUser(this);
	}

	public void removePost(Post post) {
	    this.posts.remove(post);
	    post.setUser(null);
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public void addRole(Role role) {
	    this.roles.add(role);
	}

	public void removeRole(Role role) {
	    this.roles.remove(role);
	}
	
	
	
}
