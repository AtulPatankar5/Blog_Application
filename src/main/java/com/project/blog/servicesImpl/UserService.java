package com.project.blog.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.blog.entities.User;

@Service
public class UserService {

	private List<User> users = new ArrayList<>();

	//Contains list of users
	public UserService() {
		users.add(new User( "Atul", "atul@gmail.com"));
		users.add(new User("Arushi", "arushi@gmail.com"));
		users.add(new User("chelsea", "chelsea@gmail.com"));
		users.add(new User("gappu", "gappu@gmail.com"));
	}

	public List<User> getUsers() {
		return users;
	}
}
