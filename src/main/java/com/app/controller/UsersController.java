package com.app.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.UserDTO;
import com.app.service.IUserDetails;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UsersController {
	
	@Autowired
	private IUserDetails userDetails;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(UserDTO user) throws IOException{
		userDetails.registerUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}
}
