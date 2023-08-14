package com.yg.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yg.blog.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	
}
