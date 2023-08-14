package com.yg.blog.services;

import com.yg.blog.payloads.CommentDto;

public interface CommentService {
	
	CommentDto creatComment(CommentDto commentDto,Integer postId);
	
	void deleteComment(Integer commentId);

}
