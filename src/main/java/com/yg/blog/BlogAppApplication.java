package com.yg.blog;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;



@SpringBootApplication
public class BlogAppApplication implements CommandLineRunner{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(BlogAppApplication.class, args);
	}

    @Bean
    ModelMapper modelMapper()
    {
        return new ModelMapper();
    }

	@Override
	public void run(String... args) throws Exception {
		System.out.print(this.passwordEncoder.encode("abc234"));
		
	/*	
		try {
			
			Role role1 = new Role();
			role1.setId(AppConstants.ADMIN_USER);
			role1.setName("ADMIN_USER");
			
			Role role2 = new Role();
			role2.setId(AppConstants.NORMAL_USER);
			role2.setName("NORMAL_USER");
			
			List<Role> roles=List.of(role1,role2);
			
			List<Role> result= this.roleRepo.saveAll(roles);
			
			result.forEach(r->{
				System.out.println(r.getName());
			});
		 }catch (Exception e) {
			e.printStackTrace();
		}
		
		*/
	}

 

}
