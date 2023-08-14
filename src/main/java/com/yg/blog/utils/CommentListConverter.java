package com.yg.blog.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import com.yg.blog.entities.Comment;
import com.yg.blog.payloads.CommentDto;

@Component
public class CommentListConverter extends AbstractConverter<List<Comment>, List<CommentDto>> {

    @Override
    protected List<CommentDto> convert(List<Comment> source) {
        return source.stream()
                .map(comment -> {
                    CommentDto commentDto = new CommentDto();
                    commentDto.setId(comment.getId());
                    commentDto.setContent(comment.getContent());
                    // Map other properties as needed
                    return commentDto;
                })
                .collect(Collectors.toList());
    }
}

