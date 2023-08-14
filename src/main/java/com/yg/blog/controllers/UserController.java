package com.yg.blog.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.query.JpqlParser.New_valueContext;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yg.blog.payloads.ApiResponse;
import com.yg.blog.payloads.UserDto;
import com.yg.blog.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@SecurityRequirement(name = "bearerAuth")
@Tag(name = "User")
@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//POST Create user
	@Operation(
			description = "Create User",
			summary = "Create User"
			)
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
	{
		UserDto createUserDto=this.userService.createUser(userDto);
		
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
	}
	
	//PUT update user
	@Operation(
			description = "Update User",
			summary = "Udate User"
			)
	public ResponseEntity<UserDto> updateUserById(@Valid @RequestBody UserDto userDto, @PathVariable Integer id)
	{
		UserDto updateUserDto =this.userService.updateUser(userDto, id);
		return new ResponseEntity<>(updateUserDto,HttpStatus.OK);
	}
	
	//GET get all users
	@Operation(
			description = "Get all Users",
			summary = "Get all User"
			)
	@GetMapping("/getusers")
	public ResponseEntity<List<UserDto>> getAllUser()
	{
		List<UserDto> usersList = this.userService.getAllUser();
		return new ResponseEntity<>(usersList,HttpStatus.CREATED);
	}
	
	//DELETE delete user by id
	//Admin - delete user
	@Operation(
			description = "Delete User",
			summary = "Delete User by id"
			)
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deleteUserById(@PathVariable Integer id){
		this.userService.deletUser(id);
		//return new ResponseEntity(Map.of("message","User Deleted Successfully"),HttpStatus.OK);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Sucessfully", true),HttpStatus.OK);
	}
	
	//GET get user by id
	@Operation(
			description = "Get User",
			summary = "Get User by Id"
			)
	@GetMapping("/user/{id}")
	public ResponseEntity<UserDto> getSingleUserById(@PathVariable Integer id)
	{
		UserDto getUserDto=this.userService.getUserById(id);
		
		return new ResponseEntity<UserDto>(getUserDto,HttpStatus.OK);
	}

}
