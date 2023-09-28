package com.project.blog.controllers;

import static com.project.blog.config.AppConstants.PAGE_NUMBER;
import static com.project.blog.config.AppConstants.PAGE_SIZE;
import static com.project.blog.config.AppConstants.SORT_BY;
import static com.project.blog.config.AppConstants.SORT_ORDER;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.blog.DTO.APIResponse;
import com.project.blog.DTO.PostDTO;
import com.project.blog.DTO.PostResponse;
import com.project.blog.services.FileService;
import com.project.blog.services.PostService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postSer;

	@Autowired
	private FileService fileSer;

	@Value("${project.image}")
	private String path;

	// create Post
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDTO> createPost(@RequestBody @Valid PostDTO postD, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {

		PostDTO createPost = postSer.createPost(postD, userId, categoryId);
		return new ResponseEntity<PostDTO>(createPost, HttpStatus.CREATED);
	}

	// GetPost BY User
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDTO>> getByUser(@RequestBody @PathVariable Integer userId) {
		List<PostDTO> postByUser = postSer.getPostByUser(userId);
		return new ResponseEntity<List<PostDTO>>(postByUser, HttpStatus.OK);
	}

	// GetPost by Category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDTO>> getByCategory(@RequestBody @PathVariable Integer categoryId) {
		List<PostDTO> postByCategory = postSer.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDTO>>(postByCategory, HttpStatus.OK);
	}

	// GetALL Post
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue = PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = SORT_BY, required = false) String sortBy, // sort can be done
																										// by title also
			@RequestParam(value = "sortOrder", defaultValue = SORT_ORDER, required = false) String sortOrder) {
		PostResponse postResponse = postSer.getAllPost(pageNumber, pageSize, sortBy, sortOrder);
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}

	// GetSinglePost by ID
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDTO> getSinglePost(@PathVariable Integer postId) {
		PostDTO singlePostById = postSer.getSinglePostById(postId);
		return ResponseEntity.ok(singlePostById);
	}

	// Delete the post bY ID
	@DeleteMapping("/posts/{postId}")
	public APIResponse deleteById(@PathVariable Integer postId) {
		postSer.deletePost(postId);
		return new APIResponse("SucessFully Deleted ", true);
	}

	// Update the Post
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDTO> updatePost(@Valid @RequestBody PostDTO postD, @PathVariable Integer postId) {
		PostDTO updatedPostDTo = postSer.updatePost(postD, postId);
		return ResponseEntity.ok(updatedPostDTo);
	}

	// Search the post by title
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDTO>> searchPostByTitle(@PathVariable String keywords) {
		List<PostDTO> searchPosts = postSer.searchPosts(keywords);
		return new ResponseEntity<List<PostDTO>>(searchPosts, HttpStatus.OK);
	}

	// post Image upload
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDTO> uploadPostImage(@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId) throws IOException {
		PostDTO postDTO = postSer.getSinglePostById(postId);
		String fileName = fileSer.uploadImage(path, image);
		postDTO.setImageName(fileName);
		PostDTO updatePost = postSer.updatePost(postDTO, postId);
		return new ResponseEntity<PostDTO>(updatePost, HttpStatus.OK);
	}

	
	//Method to serve files
	@GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable ("imageName") String imageName, HttpServletResponse response) throws IOException
	{
		InputStream resource= fileSer.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
}
