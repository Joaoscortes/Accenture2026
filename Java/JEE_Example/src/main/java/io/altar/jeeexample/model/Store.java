package io.altar.jeeexample.model;

import javax.persistence.Entity;

@Entity
public class Store extends Entity_ {

	private String name;
	private String location;
	private int[] opening;
	private int code;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int[] getOpening() {
		return opening;
	}

	public void setOpening(int[] opening) {
		this.opening = opening;
	}

}
