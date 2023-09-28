package com.project.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.blog.DTO.APIResponse;
import com.project.blog.DTO.CommentDTO;
import com.project.blog.services.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private CommentService commentSer;

	
	
	@PostMapping("/post/{postId}/comment")
	public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentD, @PathVariable Integer postId) {
		CommentDTO createComment = commentSer.createComment(commentD, postId);
		return new ResponseEntity<CommentDTO>(createComment, HttpStatus.CREATED);
	}

	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<APIResponse> deleteComment(@PathVariable Integer commentId) {
		commentSer.deleteComment(commentId);
		return new ResponseEntity<>(new APIResponse("Deleted SuccessFully", true), HttpStatus.OK);
	}
	
	//Create Post method with user Details 
}
