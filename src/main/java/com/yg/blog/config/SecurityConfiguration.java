package com.yg.blog.config;
/*
//import org.springframework.beans.factory.annotation.Autowired;
//import org.modelmapper.internal.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record.ForDefinedMethod.WithAnnotationDefaultValue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity

@Configuration
public class SecurityConfiguration {
	
	//@Autowired
	//private CustomUserDetailService customUserDetailService;

	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	//Basic Auth
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
              .authorizeHttpRequests((authz) -> authz
                                .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    //in memory
    
    @Bean
    UserDetailsService userDetailsService()
    {
        UserDetails yanjare = User.builder()
                .username("yanju")
                .password(passwordEncoder().encode("Yg@36369"))
                .roles("ADMIN")
                .build();

        UserDetails user = User.builder()
                .username("Pradeep")
                .password(passwordEncoder().encode("Pradu@123"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(yanjare, user);

    }

  /*  @Bean
    DaoAuthenticationProvider daoAuthenticationProvider() {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(this.customUserDetailService);
     //   provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
    */
    /*
    @Bean
    PasswordEncoder passwordEncoder()
    {
    	return new BCryptPasswordEncoder();  
    }
    

}*/