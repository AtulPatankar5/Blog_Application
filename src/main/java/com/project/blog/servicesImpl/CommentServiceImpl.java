package com.project.blog.servicesImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.blog.DTO.CommentDTO;
import com.project.blog.entities.Comment;
import com.project.blog.entities.Post;
import com.project.blog.exceptions.ResourceNotFoundException;
import com.project.blog.repositories.CommentRepo;
import com.project.blog.repositories.PostRepo;
import com.project.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepo postR;

	@Autowired
	private CommentRepo commentR;

	@Autowired
	private ModelMapper model;

	@Override
	public CommentDTO createComment(CommentDTO commentD, Integer postId) {
		Post foundPost = postR.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
		Comment foundComment = model.map(commentD, Comment.class);
		foundComment.setPost(foundPost);
		commentR.save(foundComment);

		return model.map(foundComment, CommentDTO.class);
	}

	@Override
	public void deleteComment(Integer commentId) {

		Comment foundComment = commentR.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "CommentId", commentId));
		commentR.delete(foundComment);

	}

}
