package com.app.dto;

import java.util.*;

import org.springframework.web.multipart.MultipartFile;

import com.app.entities.RoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class AuthResponse {
	private String message;
	private String jwtToken;
	private Long id;
	private String email;
	private String firstName;
	private String lastName;
	private MultipartFile profilePicture;
	private Set<RoleEnum> userRoles = new HashSet<RoleEnum>();
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getJwtToken() {
		return jwtToken;
	}
	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public MultipartFile getProfilePicture() {
		return profilePicture;
	}
	public void setProfilePicture(MultipartFile profilePicture) {
		this.profilePicture = profilePicture;
	}
	public Set<RoleEnum> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(HashSet<RoleEnum> userRoles) {
		this.userRoles = userRoles;
	}
	
	
	
}
