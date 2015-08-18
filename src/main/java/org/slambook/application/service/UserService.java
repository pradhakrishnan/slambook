package org.slambook.application.service;

import java.util.ArrayList;
import java.util.List;

import org.slambook.application.service.search.SearchQuery;
import org.slambook.application.util.SlamBookContentType;
import org.slambook.mongoservices.SlamBookServices;
import org.slambook.mongoservices.domain.SlamBookUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

@Service
public class UserService {

	@Autowired
	private SlamBookServices<SlamBookUser> userServices;
	
	@Value("${collection}")
	private String collection;
	
	@Autowired
	private GridFS gridFS;
	
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
		c = c.orOperator(crList.toArray(new Criteria[crList.size()]));
		return userServices.findDocumentsByQuery(new Query(c), collection);
	}
	
	public void insertProfilePic(byte[] data, String filename, String fileFormat){
		GridFSInputFile profilePic = gridFS.createFile(data);
		profilePic.setFilename(filename);
		profilePic.setMetaData(new BasicDBObject(filename, fileFormat));
		profilePic.setContentType(SlamBookContentType.IMAGE.toString());
		profilePic.save();
	}
	
	public GridFSDBFile retrieveProfilePic(String filename){
		return gridFS.findOne(filename);
	}
}
