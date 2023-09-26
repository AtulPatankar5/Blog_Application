package com.project.blog.servicesImpl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.blog.DTO.CategoryDTO;
import com.project.blog.entities.Category;
import com.project.blog.exceptions.ResourceNotFoundException;
import com.project.blog.repositories.CategoryRepo;
import com.project.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo catRepo;

	@Autowired
	private ModelMapper model;

	@Override
	public CategoryDTO createCategory(CategoryDTO catDTO) {
		Category newCat = model.map(catDTO, Category.class);
		Category saveCategory = catRepo.save(newCat);
		return model.map(saveCategory, CategoryDTO.class);
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO catDTO, Integer catid) {
		Category foundCategory = catRepo.findById(catid)
				.orElseThrow(() -> new ResourceNotFoundException("Category ", "Category ID", catid));
		foundCategory.setCategoryTitle(catDTO.getCategoryTitle());
		foundCategory.setCategoryDescription(catDTO.getCategoryDescription());
		catRepo.save(foundCategory);
		return model.map(foundCategory, CategoryDTO.class);
	}

	@Override
	public void deleteCategory(Integer catid) {
		Category foundID = catRepo.findById(catid)
				.orElseThrow(() -> new ResourceNotFoundException("Category ", "Category Id ", catid));
		catRepo.delete(foundID);
	}

	@Override
	public List<CategoryDTO> getAllCategory() {
		List<Category> allcategory = catRepo.findAll();
		List<CategoryDTO> CategoryDTO = allcategory.stream().map(cat -> model.map(cat, CategoryDTO.class))
				.collect(Collectors.toList());
		return CategoryDTO;
	}

	@Override
	public CategoryDTO getsingleCategory(Integer catid) {
		Category foundID = catRepo.findById(catid)
				.orElseThrow(() -> new ResourceNotFoundException("Category ", "Category Id ", catid));

		return model.map(foundID, CategoryDTO.class);
	}

}
