package com.project.blog.DTO;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.project.blog.entities.Comment;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

	private Integer id;
	
	@NotEmpty
	private String title;

	@NotEmpty
	private String content;

	private String imageName;

	private Date addedDate;

	private CategoryDTO category;

	private UserDTO user;
	
	private Set<CommentDTO> comments = new HashSet<>();

	
}
