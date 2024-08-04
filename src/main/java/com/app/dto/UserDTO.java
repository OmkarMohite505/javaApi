package com.app.dto;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.app.entities.Role;
import com.app.entities.RoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;


public class UserDTO {
	private Long Id;
	private String email;
	@JsonIgnore
	private String password;
	private String firstName;
	private String lastName;
	private MultipartFile profilePicture;
	private Set<RoleEnum> userRoles;
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public Set<RoleEnum> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(Set<RoleEnum> userRoles) {
		this.userRoles = userRoles;
	}
	public MultipartFile getProfilePicture() {
		return profilePicture;
	}
	public void setProfilePicture(MultipartFile profilePicture) {
		this.profilePicture = profilePicture;
	}
	
	
	
}