package com.project.blog.services;

import java.util.List;

import com.project.blog.DTO.CategoryDTO;

public interface CategoryService {

	// create
	CategoryDTO createCategory(CategoryDTO catDTO);

	// update
	CategoryDTO updateCategory(CategoryDTO catDTO, Integer catid);

	// delete
	void deleteCategory(Integer catid);

	// getAll
	List<CategoryDTO> getAllCategory();

	// get-Single
	CategoryDTO getsingleCategory(Integer catid);

}
