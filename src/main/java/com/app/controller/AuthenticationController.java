package com.app.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AuthRequest;
import com.app.dto.AuthResponse;
import com.app.entities.Role;
import com.app.entities.RoleEnum;
import com.app.entities.User;
import com.app.security.CustomUserDetails;
import com.app.security.JWTUtils;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthenticationController {
	@Autowired
	private JWTUtils utils;

	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private ModelMapper mapper;

	@Value("${ADMIN_SECRET_KEY}")
	private String adminSecretKey;

	@PostMapping("/sign-in")
	public ResponseEntity<?> validateuserAndCreateJwtToken(@RequestBody AuthRequest authReq) throws IOException {
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(authReq.getEmail(),
				authReq.getPassword());
		Authentication authenticationDetails = authManager.authenticate(authToken);
		CustomUserDetails customUserDetails = (CustomUserDetails) authenticationDetails.getPrincipal();
		User user = customUserDetails.getUser();
		var roleList = authenticationDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toSet());
		AuthResponse authResponse = new AuthResponse();
		roleList.forEach(role -> {
			authResponse.getUserRoles().add(RoleEnum.valueOf(role));
		});
		authResponse.setJwtToken(utils.generateJwtToken(authenticationDetails));
		authResponse.setMessage("Authentication Successfull !!");
		mapper.map(user, authResponse);
		byte profilePictureBlob[] = Files.readAllBytes(Paths.get(user.getProfilePicPath()));
		
		authResponse.setProfilePicture(profilePictureBlob);
		
		return ResponseEntity.status(HttpStatus.OK).body(authResponse);

	}
}