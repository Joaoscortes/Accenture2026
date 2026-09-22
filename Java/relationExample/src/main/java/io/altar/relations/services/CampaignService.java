package io.altar.relations.services;

import javax.enterprise.context.RequestScoped;

import io.altar.relations.models.Campaign;
import io.altar.relations.repositories.CampaignRepository;

@RequestScoped
public class CampaignService extends EntityService<CampaignRepository, Campaign> {

}
