package io.altar.auth_example.model;

import javax.persistence.Entity;

import io.altar.auth_example.util.PasswordUtil;

@Entity
public class User extends Entity_ {

	private static final long serialVersionUID = 1L;

	private String username;
	private String email;
	private String password;
	private int age;
	private Role role;

	

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
		// Hash the password before storing
		this.password = PasswordUtil.hashPassword(password);
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
}
