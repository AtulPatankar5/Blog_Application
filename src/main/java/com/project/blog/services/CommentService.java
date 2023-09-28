package com.project.blog.services;

import com.project.blog.DTO.CommentDTO;

public interface CommentService {

	CommentDTO createComment(CommentDTO commentD, Integer postId);

	void deleteComment(Integer commentId);
}
