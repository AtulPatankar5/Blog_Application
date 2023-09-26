package com.project.blog.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {

	private Integer id;

	@NotEmpty
	@Size(min = 4, message = "Length should be minimum of 10 character ")
	private String categoryTitle;

	@NotEmpty
	@Size(min = 10, message = "Length should be minimum of 10 character ")
	private String categoryDescription;

}
