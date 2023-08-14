package com.yg.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {
	
	
	private int id;
	
	@NotEmpty
	@Size(min = 4,message = "Username must be minimum of length 4 ")
	private String name;
	
	@Email(message = "Email adress is not valid !!")
	private String email;
	
	@NotEmpty
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
    	message = "Password must be at least 8 characters long and contain at least one uppercase letter,"
    			+ " one lowercase letter, one digit, and one special character.")
	private String password;
	
	@NotEmpty
	
	private String about;

}
