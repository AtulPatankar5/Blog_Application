package com.project.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.blog.DTO.PostDTO;
import com.project.blog.services.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	PostService postSer;

	@PostMapping("/user/{userId}/category/{categoryId}")
	public ResponseEntity<PostDTO> createPost(@RequestBody @Valid PostDTO postD, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {

		PostDTO createPost = postSer.createPost(postD, userId, categoryId);
		return new ResponseEntity<PostDTO>(createPost, HttpStatus.CREATED);
	}

}
