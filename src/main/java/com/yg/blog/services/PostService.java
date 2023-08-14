package com.yg.blog.services;

import java.util.List;

import com.yg.blog.payloads.PostDto;
import com.yg.blog.payloads.PostResponse;

public interface PostService {
	
	//create
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	//update
	PostDto updatePost(PostDto postDto,Integer postId);
	
	//delete
	void deletePost(Integer postId);
	
	//get all posts
	PostResponse getAllPosts(Integer pageName,Integer pageSize,String sortBy,String sortDir);
	
	//get post by id
	PostDto getPostById(Integer postId);
	
	//get all posts by category
	PostResponse getPostsByCategory(Integer categoryaId,Integer pageName,Integer pageSize,String sortBy,String sortDir);
	
	//get all posts by user
	PostResponse getPostsByUser(Integer userId,Integer pageName,Integer pageSize,String sortBy,String sortDir);
	
	//search posts
	List<PostDto> searchPosts(String keyword);
	

}
