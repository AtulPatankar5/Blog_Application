package com.project.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.blog.entities.Category;
import com.project.blog.entities.Post;
import com.project.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{

//	custom finder methods 
	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category cat);
	
}
