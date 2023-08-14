package com.yg.blog.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	
	private Integer categoryId;
	
	@NotEmpty
	@Size(min = 4,message = "Category Title must be minimum of length 4 ")
	private String categoryTitle;
	@NotBlank
	@Size(min = 5,max=100, message = "CategoryDescriptions must be of minimum of 5 and max of 100 leteters")
	private String categoryDescription;

}
