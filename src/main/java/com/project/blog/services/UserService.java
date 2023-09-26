package com.project.blog.services;

import java.util.List;

import com.project.blog.DTO.UserDTO;

public interface UserService {

	UserDTO addUser(UserDTO user);

	UserDTO updateUser(UserDTO user, Integer userID);

	UserDTO getUserById(Integer userID);

	List<UserDTO> getAllUsers();

	void deleteUser(Integer UserId);
}
