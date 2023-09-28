package com.project.blog.servicesImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.blog.DTO.UserDTO;
import com.project.blog.config.AppConstants;
import com.project.blog.entities.Role;
import com.project.blog.entities.User;
import com.project.blog.exceptions.ResourceNotFoundException;
import com.project.blog.repositories.RoleRepo;
import com.project.blog.repositories.UserRepo;
import com.project.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private PasswordEncoder passEncode;

	@Autowired
	private RoleRepo rolerepo;

	@Override
	public UserDTO addUser(UserDTO userD) {
		User userinfo = mapper.map(userD, User.class);
//		User = new User(userD.getName(), userD.getEmail(), userD.getPassword(), userD.getAbout());// USer DTO
		// to User
		User saveuser = userRepo.save(userinfo);// User saved in Database
		return mapper.map(saveuser, UserDTO.class);// convert user to DTO and returned to display
	}

	@Override
	public UserDTO updateUser(UserDTO user, Integer userID) {
		User userDetails = userRepo.findById(userID)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userID));
		userDetails.setName(user.getName());
		userDetails.setAbout(user.getAbout());
		userDetails.setEmail(user.getEmail());
		userDetails.setPassword(user.getPassword());

		User updatedUser = userRepo.save(userDetails);
		return mapper.map(updatedUser, UserDTO.class);
	}

	@Override
	public UserDTO getUserById(Integer userID) {
		User foundUser = userRepo.findById(userID)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userID));

		return mapper.map(foundUser, UserDTO.class);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> allUsers = userRepo.findAll();
		List<UserDTO> userDtos = allUsers.stream().map(user -> mapper.map(user, UserDTO.class))
				.collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer UserId) {
		User foundUser = userRepo.findById(UserId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", UserId));
		userRepo.delete(foundUser);
	}

	@Override
	public UserDTO registerUser(UserDTO userDto) {
		User foundUser = mapper.map(userDto, User.class);// USerDTO to user
		foundUser.setPassword(passEncode.encode(foundUser.getPassword()));// encoded the password
		Role role = rolerepo.findById(AppConstants.NORMAL).get();// get the user role from repo
		foundUser.getRoles().add(role);// Give the default role as user role
		User newUser = userRepo.save(foundUser);// save the user with roles
		return mapper.map(newUser, UserDTO.class);
	}
}
