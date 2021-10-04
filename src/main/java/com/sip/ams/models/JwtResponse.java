package com.sip.ams.models;

import java.io.Serializable;

public class JwtResponse implements Serializable {
	private final String jwttoken;
	
	private  String email;
	
	private  String name;
	private  String lastname;
	private  String role;
	
	
	public JwtResponse(String jwttoken, String email, String name, String lastname, String role) {
		super();
		this.jwttoken = jwttoken;
		this.email = email;
		this.name = name;
		this.lastname = lastname;
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getJwttoken() {
		return jwttoken;
	}

	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}
}
 