package com.project.blog.DTO;

import java.util.Date;

import com.project.blog.entities.Category;
import com.project.blog.entities.User;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

	@NotEmpty
	private String title;

	@NotEmpty
	private String content;

	private String imageName;

	private Date addedDate;

	private CategoryDTO category;

	private UserDTO user;
}
