package io.altar.relations.repositories;

import javax.enterprise.context.ApplicationScoped;

import io.altar.relations.models.Campaign;

@ApplicationScoped
public class CampaignRepository extends EntityRepository<Campaign> {
	
	@Override
	protected Class<Campaign> getEntityClass() {
		return Campaign.class;
	}
}
