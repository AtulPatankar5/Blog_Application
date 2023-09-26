package com.project.blog.servicesImpl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.blog.DTO.PostDTO;
import com.project.blog.entities.Category;
import com.project.blog.entities.Post;
import com.project.blog.entities.User;
import com.project.blog.exceptions.ResourceNotFoundException;
import com.project.blog.repositories.CategoryRepo;
import com.project.blog.repositories.PostRepo;
import com.project.blog.repositories.UserRepo;
import com.project.blog.services.PostService;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo pRepo;

	@Autowired
	private ModelMapper model;

	@Autowired
	private CategoryRepo catRepo;;

	@Autowired
	private UserRepo userrepo;

	@Override
	public PostDTO createPost(PostDTO postD, Integer userID, Integer catID) {

		User userfound = userrepo.findById(userID)
				.orElseThrow(() -> new ResourceNotFoundException("User ", "user ID ", userID));
		Category catfound = catRepo.findById(catID)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "cat ID ", catID));

		Post post = model.map(postD, Post.class);
		post.setImageName("default.jpg");
		post.setAddedDate(new Date());
		post.setCategory(catfound);
		post.setUser(userfound);

		Post save = pRepo.save(post);
		return model.map(save, PostDTO.class);
	}

	@Override
	public PostDTO updatePost(PostDTO postD, Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PostDTO> getAllPost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostDTO getSinglePostById(Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDTO> getPostByCategory(Integer CategoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDTO> getPostByUser(Integer UserId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDTO> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
