
/*
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yg.blog.utils.CommentListConverter;

@Configuration
public class ModelMapperConfig {

    @Autowired
    private CommentListConverter commentListConverter;

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Add the custom converter for the comments list
        modelMapper.addConverter(commentListConverter);

        return modelMapper;
    }
}
*/
/*
package com.yg.blog.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yg.blog.utils.PersistentBagToListConverter; // Import the correct converter

@Configuration
public class ModelMapperConfig {

    @Autowired
    private PersistentBagToListConverter persistentBagToListConverter; // Use the PersistentBagToListConverter

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Add the custom converter for the comments list
        modelMapper.addConverter(persistentBagToListConverter); // Use the PersistentBagToListConverter

        return modelMapper;
    }
}
*/

