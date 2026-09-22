package io.altar.relations.services;

import java.util.Collection;

import javax.enterprise.context.RequestScoped;

import io.altar.relations.models.Subscription;
import io.altar.relations.repositories.SubscriptionRepository;

@RequestScoped
public class SubscriptionService extends EntityService<SubscriptionRepository, Subscription> {

	public Collection<Subscription> getAll() {
		return repository.getAll();
	}
}
