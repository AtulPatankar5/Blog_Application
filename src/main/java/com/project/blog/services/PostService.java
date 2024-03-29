package com.project.blog.services;

import java.util.List;

import com.project.blog.DTO.PostDTO;
import com.project.blog.DTO.PostResponse;

public interface PostService {

	PostDTO createPost(PostDTO postD, Integer userID, Integer catID);

	PostDTO updatePost(PostDTO postD, Integer postId);

	void deletePost(Integer postId);

	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

	PostDTO getSinglePostById(Integer postId);

	List<PostDTO> getPostByCategory(Integer CategoryId);

	List<PostDTO> getPostByUser(Integer UserId);

	List<PostDTO> searchPosts(String keyword);
	

}
