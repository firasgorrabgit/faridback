package com.sip.ams.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sip.ams.entities.AuthenticationBean;
import com.sip.ams.entities.User;
import com.sip.ams.repositories.RoleRepository;
import com.sip.ams.services.UserService;

@CrossOrigin(origins = "*")
@RestController
public class BasicAuthRestController {
	/*
	 // basic authentication static (username et passward fournit par sparing security)
	@GetMapping(path = "/basicauth")
	public AuthenticationBean basicauth() {
		
		return new AuthenticationBean("You are authenticated");
	}
	*/ 
	
	//  basic authentication dynamic 
	@Autowired
	private final UserService userservice;
	
	@Autowired
	public BasicAuthRestController(UserService userservice) {
		this.userservice = userservice;
		
	}

	
	
		@RequestMapping({ "/greeting" })
		public String welcomePage() {
			return "Welcome!"; 
		}
	/*
	@GetMapping(path = "/basicauth")
	public User basicauth() {
		
		
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication() ;
		 User user =userservice.findUserByEmail(auth.getName());
		 return user;
		 
	}*/
} 