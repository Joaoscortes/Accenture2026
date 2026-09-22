package io.altar.relations.repositories;

import javax.enterprise.context.ApplicationScoped;

import io.altar.relations.models.Tag;

@ApplicationScoped
public class TagRepository extends EntityRepository<Tag> {

	@Override
	protected Class<Tag> getEntityClass() {
		return Tag.class;
	}
}