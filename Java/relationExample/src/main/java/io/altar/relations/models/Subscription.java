package io.altar.relations.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@NamedQueries({ @NamedQuery(name = Subscription.GET_ALL_SUBSCRIPTIONS, query = "SELECT s FROM Subscription s") })
public class Subscription extends Entity_ {

	public static final String GET_ALL_SUBSCRIPTIONS = "getAllSubscriptions";

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JsonIgnoreProperties("subscriptions")
	private Product product;
	@ManyToOne
	@JsonIgnoreProperties("subscriptions")
	private Campaign campaign;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Campaign getCampaign() {
		return campaign;
	}

	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}

}
