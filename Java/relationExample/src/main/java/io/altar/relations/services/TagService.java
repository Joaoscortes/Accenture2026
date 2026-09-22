package io.altar.relations.services;

import javax.enterprise.context.RequestScoped;

import io.altar.relations.models.Tag;
import io.altar.relations.repositories.TagRepository;

@RequestScoped
public class TagService extends EntityService<TagRepository, Tag> {

}
