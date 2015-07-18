package org.slambook.application.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slambook.application.service.UserService;
import org.slambook.application.service.search.SearchQuery;
import org.slambook.application.service.search.SearchQueryBuilder;
import org.slambook.application.util.SlamBookHelper;
import org.slambook.mongoservices.domain.SlamBookUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SlamBookWebController {
	
	@Autowired
	private UserService userService;

	@RequestMapping("/admin")
	public ModelAndView index(Principal principal){
		System.out.println("Logged in user "+ principal.getName());
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/admin");
		return mv;
	}
	
	@RequestMapping("/registration")
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response){
		Map<String, String[]> map = request.getParameterMap();
		SlamBookUser user = SlamBookHelper.convertMapToUser(map);
		System.out.println("User "+ user);
		userService.insert(user);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("search");
		mv.getModel().put("searchModel", new SearchQuery());
		return mv;
	}
	
	@RequestMapping("/dashboard")
	public ModelAndView dashBoard(Principal principal){
		ModelAndView mv = new ModelAndView();
		String username = principal.getName();
		SlamBookUser user = userService.getByUsername(username);
		mv.getModel().put("slamBookUser", user);
		mv.addObject("slamBookUser", user);
		mv.setViewName("dashboard");
		return mv;
	}
	
	@RequestMapping("/viewprofile")
	public ModelAndView viewProfile(Principal principal, HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		//String username = principal.getName();
		SearchQuery query = SearchQueryBuilder.build(request);
		List<SlamBookUser> user = userService.findUser(query);
		mv.getModel().put("slamBookUser", user.get(0));
		mv.setViewName("dashboard");
		return mv;
	}
	
	@RequestMapping("/search")
	public ModelAndView search(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("search");
		mv.getModel().put("searchModel", new SearchQuery());
		return mv;
	}
	
	@RequestMapping("/searchUser")
	public ModelAndView searchUser(@ModelAttribute SearchQuery query, HttpServletResponse response){
		List<SlamBookUser> userList = userService.findUser(query);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("search");
		mv.getModel().put("userList", userList);
		return mv;
	}
}
