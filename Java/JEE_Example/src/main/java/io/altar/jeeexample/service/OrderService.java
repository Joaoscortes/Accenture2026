package io.altar.jeeexample.service;

import javax.enterprise.context.ApplicationScoped;

import io.altar.jeeexample.model.Order;
import io.altar.jeeexample.persistence.OrderPersistence;

@ApplicationScoped
public class OrderService extends EntityService<OrderPersistence, Order> {

}
