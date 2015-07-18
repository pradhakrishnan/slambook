package org.slambook.application.util;

import java.util.HashSet;
import java.util.Map;

import org.slambook.mongoservices.domain.SlamBookUser;


public class SlamBookHelper {

	public static SlamBookUser convertMapToUser(Map<String, String[]> map) {
		SlamBookUser user = new SlamBookUser();
		if(null != map.get("firstname")){
			user.setFirstname(map.get("firstname")[0]);
		}
		if(null != map.get("firstname")){
			user.setLastName(map.get("lastname")[0]);
		}
		if(null != map.get("username")){
			user.setUsername(map.get("username")[0]);
		}
		if(null != map.get("email")){
			System.out.println("email ==== "+map.get("email")[0]);
			user.setEmail(map.get("email")[0]);
		}
		if(null != map.get("password")){
			user.setPassword(map.get("password")[0]);
		}
		user.setRole("ROLE_USER");
		user.setFriends(new HashSet<SlamBookUser>());
		return user;
	}

}
