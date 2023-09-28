package com.project.blog.servicesImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.blog.DTO.PostDTO;
import com.project.blog.DTO.PostResponse;
import com.project.blog.entities.Category;
import com.project.blog.entities.Post;
import com.project.blog.entities.User;
import com.project.blog.exceptions.ResourceNotFoundException;
import com.project.blog.repositories.CategoryRepo;
import com.project.blog.repositories.PostRepo;
import com.project.blog.repositories.UserRepo;
import com.project.blog.services.PostService;

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
		Post foundPost = pRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "PostID ", postId));
		foundPost.setTitle(postD.getTitle());
		foundPost.setContent(postD.getContent());
		foundPost.setImageName(postD.getImageName());
		Post updatedPost = pRepo.save(foundPost);
		return model.map(updatedPost, PostDTO.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post foundPost = pRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "PostID ", postId));
		pRepo.delete(foundPost);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {

		// Sort the pages according to the order asc / desc
		Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		Pageable p = PageRequest.of(pageNumber, pageSize, sort);// adding for sorting

		Page<Post> pagePost = pRepo.findAll(p);// returns pages of all post by pageNumber and page size

		List<Post> allPost = pagePost.getContent();// returns list of all pages

		List<PostDTO> allPostDTO = allPost.stream().map(post -> model.map(post, PostDTO.class))
				.collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(allPostDTO);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());

		return postResponse;
	}

	@Override
	public PostDTO getSinglePostById(Integer postId) {
		Post foundPost = pRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "PostID ", postId));
		return model.map(foundPost, PostDTO.class);
	}

	@Override
	public List<PostDTO> getPostByCategory(Integer CategoryId) {
		Category foundCategory = catRepo.findById(CategoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category ", "Category ID", CategoryId));
		List<Post> foundPost = pRepo.findByCategory(foundCategory);
		List<PostDTO> foundPostDTO = foundPost.stream().map(post -> model.map(post, PostDTO.class))
				.collect(Collectors.toList());
		return foundPostDTO;
	}

	@Override
	public List<PostDTO> getPostByUser(Integer UserId) {
		User foundUser = userrepo.findById(UserId)
				.orElseThrow(() -> new ResourceNotFoundException("User ", "User ID", UserId));
		List<Post> foundPost = pRepo.findByUser(foundUser);
		List<PostDTO> foundPostDTO = foundPost.stream().map(post -> model.map(post, PostDTO.class))
				.collect(Collectors.toList());
		return foundPostDTO;
	}

	@Override
	public List<PostDTO> searchPosts(String keyword) {
		List<Post> posts = pRepo.findByTitleContaining(keyword);
		List<PostDTO> collect = posts.stream().map(post->model.map(post, PostDTO.class)).collect(Collectors.toList());
		return collect;
	}
}
