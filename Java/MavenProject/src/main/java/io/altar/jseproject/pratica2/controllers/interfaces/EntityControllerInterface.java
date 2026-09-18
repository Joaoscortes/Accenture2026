package io.altar.jseproject.pratica2.controllers.interfaces;

import java.util.Collection;
import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import io.altar.jseproject.pratica2.models.DTOs.EntityDTO;

public abstract interface EntityControllerInterface<T extends EntityDTO> {

	public String status();

	public long count();

	public Collection<T> get();

	public Collection<Long> getIds();

	public boolean isEmpty();

	public Response add(T entity);

	public String add(List<T> entities);

	public Response get(@PathParam("id") long id);

	public Response edit(T entity);

	public Response remove(@PathParam("id") long id);
}
