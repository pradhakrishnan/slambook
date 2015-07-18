package org.slambook.application.service.search;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class SearchQueryBuilder {

	public static SearchQuery build(HttpServletRequest request) {
		Map<String, String[]> paramsMap = request.getParameterMap();
		SearchQuery query = new SearchQuery();
		if(null != paramsMap.get("email")){
			query.setEmail(paramsMap.get("email")[0]);
		}
		return query;
	}

}
