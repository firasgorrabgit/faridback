package com.sip.ams.controllers;

import java.util.Objects;

//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.sip.ams.configuration.JwtTokenUtil;
import com.sip.ams.entities.User;
import com.sip.ams.models.JwtRequest;
import com.sip.ams.models.JwtResponse;
import com.sip.ams.services.UserService;

@RestController
@CrossOrigin(origins = "*")
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	/*@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;
	*/
	@Autowired
	private  UserService userservice;

	@RequestMapping(value = { "/auth" }, method = RequestMethod.POST)

	public ResponseEntity<?> generateAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
			throws Exception {
		
		
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userservice.findUserByEmail(authenticationRequest.getUsername());
		System.out.println("userDetails  :->  " + userDetails);
		
		User user = (User)userDetails;
				//.loadUserByUsername(authenticationRequest.getUsername());
	/*	Authentication auth = SecurityContextHolder.getContext().getAuthentication() ;
		UserDetails userDetails = userservice.findUserByEmail(auth.getName());*/
		final String token = jwtTokenUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new JwtResponse(token ,user.getEmail(),user.getName()
				,user.getLastName(),user.getTemp() ));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
