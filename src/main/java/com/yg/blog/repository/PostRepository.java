package com.yg.blog.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.yg.blog.entities.Category;
import com.yg.blog.entities.Post;
import com.yg.blog.entities.User;

public interface PostRepository extends JpaRepository<Post,Integer>{

	
	Page<Post> findByUser(User user,Pageable p);
	Page<Post> findByCategory(Category category,Pageable p);
	
	List<Post> findByTitleContaining(String title);
	
	
	
	
}
