package com.yg.blog.controllers;

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

import com.yg.blog.payloads.ApiResponse;
import com.yg.blog.payloads.CategoryDto;
import com.yg.blog.services.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Category")
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	//create
	
	@Operation(
			description = "Create Category",
			summary = "Create Category"
			)
	@PostMapping("/create")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto)
	{
		CategoryDto categoryDto2=this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(categoryDto2,HttpStatus.CREATED);
	}
	
	//update
	@Operation(
			description = "Update Category",
			summary = "Update Category by Id"
	        )
	@PutMapping("/update/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer id)
	{
		CategoryDto updateCategoryDto=this.categoryService.updateCategory(categoryDto, id);
		return new ResponseEntity<CategoryDto>(updateCategoryDto,HttpStatus.OK);
	}
	
	//delete
	@Operation(
			description = "Delete Category",
			summary = "Delete Category by Id"
	        )
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deleteCategory( @PathVariable Integer id)
	{
		this.categoryService.deleteCategory(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted Sucessfully",true) ,HttpStatus.OK);
	}
	
	//get 
	@Operation(
			description = "Get Category",
			summary = "Get Category By Id"
	        )
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer id)
	{
		CategoryDto getCategoryDto=this.categoryService.getCategory(id);
		return new ResponseEntity<CategoryDto>(getCategoryDto,HttpStatus.OK);
	}
	
	//get all
	@Operation(
			description = "Get ALL Category",
			summary = "GET ALL Category"
	        )
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory()
	{
		List<CategoryDto> allCategoryDtos=this.categoryService.getAllCategory();
		return new ResponseEntity<List<CategoryDto>>(allCategoryDtos,HttpStatus.OK);
	}
	
	

}
