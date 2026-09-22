package io.altar.relations.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.altar.relations.models.Campaign;
import io.altar.relations.models.Product;
import io.altar.relations.models.Shelf;
import io.altar.relations.models.Subscription;
import io.altar.relations.models.Tag;
import io.altar.relations.services.CampaignService;
import io.altar.relations.services.ProductService;
import io.altar.relations.services.ShelfService;
import io.altar.relations.services.SubscriptionService;
import io.altar.relations.services.TagService;

@RequestScoped
@Path("")
public class ExamplesController {
	
	@Inject
	ProductService PS;
	
	@Inject
	ShelfService SS;
	
	@Inject
	TagService TS;
	
	@Inject
	CampaignService CS;
	
	@Inject
	SubscriptionService SUBS;

	@GET
	@Path("oneToMany")
	public void oneToMany() {
		 Product p1 = new Product();
		 p1 = PS.create(p1);
		 		 
		 Shelf s1 = new Shelf();
		 s1.setProduct(p1);
		 s1 = SS.create(s1);
		 
		 Shelf s2 = new Shelf();
		 s2.setProduct(p1);
		 s2 = SS.create(s2);
	}
	
	@GET
	@Path("manyToOne")
	public void manyToOne() {
		 Shelf s1 = new Shelf();
		 s1 = SS.create(s1);
		 
		 Shelf s2 = new Shelf();
		 s2 = SS.create(s2);
		 
		 Product p1 = new Product();
		 p1 = PS.create(p1);
		 
		 List<Shelf> shelves = new ArrayList<Shelf>();
		 p1.setShelves(shelves);
		 
		 s1.setProduct(p1);
		 shelves.add(s1);
		 s2.setProduct(p1);
		 shelves.add(s2);

		 PS.update(p1);
	}
	
	@GET
	@Path("manyToMany")
	public void manyToMany() {
		Product p1 = new Product();
		Product p2 = new Product();
		
	    Tag t1 = new Tag();
	    t1 = TS.create(t1);
	    Tag t2 = new Tag();
	    t2 = TS.create(t2);
	    
	    Set<Tag> st1 = new HashSet<>();
	    st1.add(t1);
	    st1.add(t2);
	    p1.setTags(st1);
	    Set<Tag> st2 = new HashSet<>();
	    st2.add(t1);
	    p2.setTags(st2);
	    
	    PS.create(p1);
	    PS.create(p2);   
	}
	
	@GET
	@Path("manyToManySubscription")
	public void manyToManySubscription() {
		Product p1 = new Product();
		p1 = PS.create(p1);
		Product p2 = new Product();
		p2 = PS.create(p2);
		Product p3 = new Product();
		p3 = PS.create(p3);
		
	    Campaign c1 = new Campaign();
	    c1 = CS.create(c1);
	    Campaign c2 = new Campaign();
	    c2 = CS.create(c2);
	    
	    Subscription s1 = new Subscription();
	    s1.setCampaign(c1);
	    s1.setProduct(p1);
	    SUBS.create(s1);
	    
	    Subscription s2 = new Subscription();
	    s2.setCampaign(c1);
	    s2.setProduct(p2);
	    SUBS.create(s2);
	    
	    Subscription s3 = new Subscription();
	    s3.setCampaign(c2);
	    s3.setProduct(p2);
	    SUBS.create(s3); 
	    
	    Subscription s4 = new Subscription();
	    s4.setCampaign(c2);
	    s4.setProduct(p3);
	    SUBS.create(s4); 
	}
	
	@GET
	@Path("allProducts")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getAllProducts() {
		return PS.getAll().stream().collect(Collectors.toList());
	}
	
	@GET
	@Path("allSubscriptions")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Subscription> getAllSubscriptions() {
		return SUBS.getAll().stream().collect(Collectors.toList());
	}
}
