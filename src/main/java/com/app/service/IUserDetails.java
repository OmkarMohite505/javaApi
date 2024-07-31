package com.app.service;

import java.io.IOException;

import com.app.dto.UserDTO;

public interface IUserDetails {
	public UserDTO registerUser(UserDTO user) throws IOException;
}
