package io.altar.jeeexample.controller;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Path;

import io.altar.jeeexample.model.Order;
import io.altar.jeeexample.persistence.OrderPersistence;
import io.altar.jeeexample.service.OrderService;

@RequestScoped
@Path("orders")
public class OrderController extends EntityController<OrderService, OrderPersistence, Order> {

}
