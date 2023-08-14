package com.yg.blog.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
		info = @Info(
				contact = @Contact(
						
						name = "Yanjare Gowd",
						email = "yanjaregowd1213@gmail.com",
						url = "http://yg.com/"
						
						),
				description = "OpenApi documentation for Blog Application",
				title = "Blog Application",
				version = "v1.0",
				license = @License(
						
						name = "License Name",
						url = "htpps://yg.url.com"
						
						),
				termsOfService = "Terms of Service"
				
				),
		servers = {
				@Server(
						description = "Local ENV",
						url = "http://localhost:8080/"
						),
				@Server(
						description = "PROD ENV",
						url = "http://yg.com/"	
						)
					}
	/*	security = @SecurityRequirement(
				name = "bearerAuth"
				)
	*/
		)
@SecurityScheme(
		name = "bearerAuth",
		description = "JWT auth description",
		scheme = "bearer",
		type = SecuritySchemeType.HTTP,
		bearerFormat = "JWT",
		in = SecuritySchemeIn.HEADER
		)
public class SwaggerConfig {
	
	//public Dock


}
