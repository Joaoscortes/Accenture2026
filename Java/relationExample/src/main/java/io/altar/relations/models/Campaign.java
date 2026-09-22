package io.altar.relations.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Campaign extends Entity_ {

	private static final long serialVersionUID = 1L;

	private String name;
	@OneToMany(mappedBy = "campaign")
	private List<Subscription> subscriptions;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

}