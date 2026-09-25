package io.altar.jeeexample.model;

import javax.persistence.Entity;

/**
 * Entity class representing a User in the application. This class maps to the
 * database table for users and contains all user-related attributes.
 */
@Entity
public class User extends Entity_ {

	private String name;
	private String email;
	private String password;
	private int age;

	/**
	 * Gets the user's name.
	 * 
	 * @return The user's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the user's name.
	 * 
	 * @param name The name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the user's email address.
	 * 
	 * @return The user's email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the user's email address.
	 * 
	 * @param email The email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the user's password.
	 * 
	 * @return The user's password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the user's password.
	 * 
	 * @param password The password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the user's age.
	 * 
	 * @return The user's age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Sets the user's age.
	 * 
	 * @param age The age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
}
