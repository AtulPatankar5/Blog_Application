package com.project.blog.DTO;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//details sent to response showing all the details of page
@NoArgsConstructor
@Setter
@Getter
public class PostResponse {

	private List<PostDTO> content;
	private int pageNumber;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean lastPage;

}
