package com.sip.ams.controllers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sip.ams.entities.Provider;
import com.sip.ams.entities.Role;
import com.sip.ams.entities.User;
import com.sip.ams.repositories.RoleRepository;
import com.sip.ams.repositories.UserRepository;
import com.sip.ams.services.UserService;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
public class RegistrationController {
	@Autowired
	private final UserService userservice;
	
	//@Autowired
//	private final UserRepository userRepository;
	@Autowired
	private final RoleRepository roleRepository;
	

	@Autowired
	public RegistrationController(UserService userservice, RoleRepository roleRepository) {
		this.userservice = userservice;
		this.roleRepository = roleRepository;
	}

	@RequestMapping(value="registration"  , method=RequestMethod.POST)
	public User createNewUser( @RequestBody User user ) {
/* si on veut donner  le role USER atoute les users
 *
        user.setRoles(new HashSet<Role>(Arrays.asList( new Role("USER")));
		userservice.saveUser(user);
		return user;
 */
		Role userRole =roleRepository.findByRole(user.getTemp());
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userservice.saveUser(user);
		return user;
	}

	
}
