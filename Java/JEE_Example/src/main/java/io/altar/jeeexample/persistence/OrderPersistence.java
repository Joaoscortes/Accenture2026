package io.altar.jeeexample.persistence;

import javax.enterprise.context.ApplicationScoped;

import io.altar.jeeexample.model.Order;

@ApplicationScoped
public class OrderPersistence extends EntityPersistence<Order> {

	@Override
	protected Class<Order> getEntityClass() {
		return Order.class;
	}
}
