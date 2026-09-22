package io.altar.relations.repositories;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import io.altar.relations.models.Subscription;

@ApplicationScoped
public class SubscriptionRepository extends EntityRepository<Subscription> {

	@Override
	protected Class<Subscription> getEntityClass() {
		return Subscription.class;
	}
	
	public List<Subscription> getAll() {
		return entityManager.createNamedQuery(Subscription.GET_ALL_SUBSCRIPTIONS, getEntityClass()).getResultList();
	}
}