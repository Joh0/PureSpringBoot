package com.bean;

import jakarta.persistence.*;

@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String bio;

    @Column(nullable = false)
    private Integer age;
    
    @Column(nullable = false)
    private String gender;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;

	public Profile(Long id, String bio, Integer age, String gender, User user) {
		super();
		this.id = id;
		this.bio = bio;
		this.age = age;
		this.gender = gender;
		this.user = user;
	}

	public Profile() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

    
}
