package com.yg.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yg.blog.entities.Comment;
import com.yg.blog.entities.Post;
import com.yg.blog.exceptions.ResourceNotFoundException;
import com.yg.blog.payloads.CommentDto;
import com.yg.blog.repository.CommentRepository;
import com.yg.blog.repository.PostRepository;
import com.yg.blog.services.CommentService;
@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public CommentDto creatComment(CommentDto commentDto, Integer postId) {
		
		Post post=this.postRepository.findById(postId).orElseThrow(
				()->new ResourceNotFoundException("Post", "post id", postId));
		
		Comment comment=this.modelMapper.map(commentDto, Comment.class);
		
		comment.setPost(post);
		
		Comment savedComment=this.commentRepository.save(comment);
		
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		
		Comment com= this.commentRepository.findById(commentId).orElseThrow(
				()->new ResourceNotFoundException("Comment", "commentId", commentId));
		this.commentRepository.delete(com);
		
	}

}
