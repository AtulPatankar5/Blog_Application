package com.project.blog.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.blog.DTO.APIResponse;
import com.project.blog.DTO.CategoryDTO;
import com.project.blog.services.CategoryService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService catService;

	// create
	@PostMapping("/")
	public ResponseEntity<CategoryDTO> createCategory(@RequestBody @Valid CategoryDTO catDTO) {
		CategoryDTO createdCategory = catService.createCategory(catDTO);
		return new ResponseEntity<CategoryDTO>(createdCategory, HttpStatus.CREATED);
	}

	// update
	@PutMapping("/{catid}")
	public ResponseEntity<CategoryDTO> updateCategory(@RequestBody @Valid CategoryDTO catDTO,
			@PathVariable Integer catid) {
		CategoryDTO updateCategory = catService.updateCategory(catDTO, catid);
		return ResponseEntity.ok(updateCategory);
	}

	// delete
	@DeleteMapping("/{catId}")
	public ResponseEntity<APIResponse> deleteCategory(@RequestBody @PathVariable Integer catId) {
		catService.deleteCategory(catId);
		return new ResponseEntity<>(new APIResponse("SuccessFuly Deleted ", true), HttpStatus.OK);
	}

	// getSingle
	@GetMapping("/{catID}")
	public ResponseEntity<CategoryDTO> getSingleCategory(@RequestBody @PathVariable Integer catID) {
		CategoryDTO getsingleCategory = catService.getsingleCategory(catID);
		return ResponseEntity.ok(getsingleCategory);
	}

	// getALL
	@GetMapping("/")
	public ResponseEntity<List<CategoryDTO>> getAll() {
		List<CategoryDTO> allCategory = catService.getAllCategory();
		return ResponseEntity.ok(allCategory);
	}

}
