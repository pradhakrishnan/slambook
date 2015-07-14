package org.slambook.application.controller;

import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slambook.application.domain.SlamBookUser;
import org.slambook.application.service.UserService;
import org.slambook.application.util.SlamBookHelper;
import org.springframework.beans.factory.annotation.Autowired;
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
		userService.insert(user);
		ModelAndView mv = new ModelAndView("searchfriends");
		return mv;
	}
}
