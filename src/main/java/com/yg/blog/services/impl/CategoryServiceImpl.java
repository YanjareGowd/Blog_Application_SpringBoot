package com.yg.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yg.blog.entities.Category;
import com.yg.blog.exceptions.ResourceNotFoundException;
import com.yg.blog.payloads.CategoryDto;
import com.yg.blog.repository.CategoryRepository;
import com.yg.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		Category cat = this.modelMapper.map(categoryDto, Category.class);
		Category addedCategory =this.categoryRepository.save(cat);
		
		return this.modelMapper.map(addedCategory,CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId) {
	
		Category existingCategory=this.categoryRepository.findById(categoryId).orElseThrow(
				()-> new ResourceNotFoundException("Category"," id ",categoryId));
		existingCategory.setCategoryDescription(categoryDto.getCategoryDescription());
		existingCategory.setCategoryTitle(categoryDto.getCategoryTitle());
		Category updatedCategory=this.categoryRepository.save(existingCategory);
		
		return this.modelMapper.map(updatedCategory,CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category category= this.categoryRepository.findById(categoryId).orElseThrow(
				()-> new ResourceNotFoundException("Category"," id ",categoryId));
		this.categoryRepository.delete(category);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category category=this.categoryRepository.findById(categoryId).orElseThrow(
				()-> new ResourceNotFoundException("Category"," id ",categoryId));
		return this.modelMapper.map(category,CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		 List<Category> categories =this.categoryRepository.findAll();
		List<CategoryDto> collect = categories.stream().map((cat)-> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return collect;
	}

}
