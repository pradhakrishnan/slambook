package org.slambook.application.service;

import java.util.ArrayList;
import java.util.List;

import org.slambook.application.service.search.SearchQuery;
import org.slambook.mongoservices.SlamBookServices;
import org.slambook.mongoservices.domain.SlamBookUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private SlamBookServices<SlamBookUser> userServices;
	
	@Value("${collection}")
	private String collection;
	
	public SlamBookUser getByUsername(String username) {
		return userServices.findDocument(new Query(Criteria.where("username").is(username)), collection);
	}
	
	public void insert(SlamBookUser user){
		userServices.insertDocument(user, collection);
	}
	
	public List<SlamBookUser> findUser(SearchQuery query){
		if(!query.isByEmail() && !query.isByFirstName() && !query.isByLastname()){
			return userServices.findAllDocument(collection);
		}
		Criteria c = new Criteria();
		List<Criteria> crList = new ArrayList<Criteria>();
		if(query.isByFirstName()){
			crList.add(Criteria.where("firstname").is(query.getFirstname()));
		}
		if(query.isByLastname()){
			crList.add(Criteria.where("lastname").is(query.getLastname()));
		}
		if(query.isByEmail()){
			crList.add(Criteria.where("email").is(query.getEmail()));
		}
		System.out.println("*** size ***"+ crList.size());
		c = c.orOperator(crList.toArray(new Criteria[crList.size()]));
		System.out.println("Criteria is "+ c.getKey());
		return userServices.findDocumentsByQuery(new Query(c), collection);
	}
}
