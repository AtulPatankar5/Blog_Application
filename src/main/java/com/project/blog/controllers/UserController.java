package com.project.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.blog.DTO.APIResponse;
import com.project.blog.DTO.UserDTO;
import com.project.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userservice;

	// POST-create User
	@PostMapping("/")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userdto) {
		UserDTO addUser = userservice.addUser(userdto);
		return new ResponseEntity<>(addUser, HttpStatus.CREATED);
	}

	// PUT-Update User
	@PutMapping("/{userID}")
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userdto, @PathVariable("userID") Integer id) {
		UserDTO updateUser = userservice.updateUser(userdto, id);
		return ResponseEntity.ok(updateUser);
//		return new ResponseEntity<>(updateUser, HttpStatus.OK);
	}

	// GET- Get ALL User
	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> allUsers() {
		List<UserDTO> allUsers = userservice.getAllUsers();
		return ResponseEntity.ok(allUsers);
	}

	//GET- get Single User
	@GetMapping("/{userID}")
	public ResponseEntity<UserDTO> getSingleUser(@RequestBody @PathVariable Integer userID) {
		UserDTO userById = userservice.getUserById(userID);
		return ResponseEntity.ok(userById);
	}

	// DELETE - Delete User BY ADMIN only
	@DeleteMapping("/{userID}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<APIResponse> deleteUser(@RequestBody @PathVariable Integer userID) {
		userservice.deleteUser(userID);
		return new ResponseEntity<>(new APIResponse("Deleted successfully", true), HttpStatus.OK);
	}
}
