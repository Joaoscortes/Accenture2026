package io.altar.jeeexample.model;

import javax.persistence.Entity;

/**
 * Entity class representing a Store in the application. This class maps to the
 * database table for stores and contains all store-related attributes.
 */
@Entity
public class Store extends Entity_ {

	private String name;
	private String location;
	private int[] opening;
	private int code;

	/**
	 * Gets the store's name.
	 * 
	 * @return The store's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the store's name.
	 * 
	 * @param name The name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the store's location.
	 * 
	 * @return The store's location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Sets the store's location.
	 * 
	 * @param location The location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Gets the store's opening hours.
	 * 
	 * @return Array of opening hours
	 */
	public int[] getOpening() {
		return opening;
	}

	/**
	 * Sets the store's opening hours.
	 * 
	 * @param opening Array of opening hours to set
	 */
	public void setOpening(int[] opening) {
		this.opening = opening;
	}

	/**
	 * Gets the store's code.
	 * 
	 * @return The store's code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Sets the store's code.
	 * 
	 * @param code The code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}
}
