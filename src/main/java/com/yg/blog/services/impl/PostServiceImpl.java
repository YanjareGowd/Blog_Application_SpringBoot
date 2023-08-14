package com.yg.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.yg.blog.entities.Category;
import com.yg.blog.entities.Post;
import com.yg.blog.entities.User;
import com.yg.blog.exceptions.ResourceNotFoundException;
import com.yg.blog.payloads.PostDto;
import com.yg.blog.payloads.PostResponse;
import com.yg.blog.repository.CategoryRepository;
import com.yg.blog.repository.PostRepository;
import com.yg.blog.repository.UserRepo;
import com.yg.blog.services.PostService;

import jakarta.transaction.Transactional;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private  ModelMapper modelMapper;
    
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		
		User user =this.userRepo.findById(userId).orElseThrow(
				()-> new ResourceNotFoundException("User"," id ",userId));
		
		Category category=this.categoryRepository.findById(categoryId).orElseThrow(
				()-> new ResourceNotFoundException("Category", " id ", categoryId));
		
		Post post = this.modelMapper.map(postDto, Post.class);
		
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		
		Post newPost = this.postRepository.save(post);
		
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post=this.postRepository.findById(postId).orElseThrow(
				()-> new ResourceNotFoundException("Category", " id ", postId));
	    
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post updatedPost =this.postRepository.save(post);
		
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post=this.postRepository.findById(postId).orElseThrow(
				()-> new ResourceNotFoundException("Category", " id ", postId));
		this.postRepository.delete(post);
	}
	
	@Transactional

	@Override
	public PostResponse getAllPosts(Integer pageName,Integer pageSize,String sortBy,String sortDir) {
		
	
		Sort sort =null;
		if(sortDir.equalsIgnoreCase("asc"))
		{
			sort=Sort.by(sortBy).ascending();
		}
		else
		{
			sort=Sort.by(sortBy).descending();
		}
		//	Pageable p =PageRequest.of(pageName, pageSize,Sort.by(sortBy)..ascending());
		Pageable p =PageRequest.of(pageName, pageSize,sort);
		
		Page<Post> pagePost=this.postRepository.findAll(p);
		
		List<Post> posts=pagePost.getContent();
		
		List<PostDto> postDtos=posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse=new PostResponse(); 
		
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		return postResponse;

	}

	@Override
	public PostResponse getPostsByCategory(Integer categoryId,Integer pageName,Integer pageSize,String sortBy,String sortDir) {
		
		Category category=this.categoryRepository.findById(categoryId).orElseThrow(
				()-> new ResourceNotFoundException("Category"," id ",categoryId));
		
		Sort sort =null;
		if(sortDir.equalsIgnoreCase("asc"))
		{
			sort=Sort.by(sortBy).ascending();
		}
		else
		{
			sort=Sort.by(sortBy).descending();
		}
		
		Pageable p =PageRequest.of(pageName, pageSize,sort);
		
		Page<Post> pagePost=this.postRepository.findByCategory(category, p);
		
		List<Post> posts=pagePost.getContent();
		
		
		List<PostDto> postDtos=posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		PostResponse postResponse=new PostResponse(); 
		
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		return postResponse;
		
		
	}

	@Transactional
	@Override
	public PostResponse getPostsByUser(Integer userId,Integer pageName,Integer pageSize,String sortBy,String sortDir) {
		
		User user=this.userRepo.findById(userId).orElseThrow(
				()-> new ResourceNotFoundException("Category"," id ",userId));
		
		Sort sort =null;
		if(sortDir.equalsIgnoreCase("asc"))
		{
			sort=Sort.by(sortBy).ascending();
		}
		else
		{
			sort=Sort.by(sortBy).descending();
		}
		
		Pageable p =PageRequest.of(pageName, pageSize,sort);
		
		Page<Post> pagePost=this.postRepository.findByUser(user, p);
		
		List<Post> posts=pagePost.getContent();
		
		List<PostDto> postDtos=posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse=new PostResponse(); 
		
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		return postResponse;
	}

	//search
	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts=this.postRepository.findByTitleContaining(keyword);
		
		List<PostDto> postDto=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDto;
		
	}

	@Override
	@Transactional
	public PostDto getPostById(Integer postId) {
		Post post=this.postRepository.findById(postId).orElseThrow(
				()-> new ResourceNotFoundException("User"," id ",postId));
		//post.getComments().size();
		return this.modelMapper.map(post, PostDto.class);
	}

}
