package com.yg.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SuppressWarnings("deprecation")
@EnableWebSecurity
@Configuration
@EnableWebMvc
 @EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	
	public static String[] pUBLIC_URL = {
			"/api/v1/auth/**",
			"/v3/api-docs",
			"/v2/api-docs",
			"/v3/api-docs/**",
			"/swagger-resources/**",
			"/swagger-resources",
			"/configuration/ui",
			"/coufiguration/security",
			"/swagger-ui/**",
			"/webjars/**",
			"/swagger-ui.html"
	};
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
    private JwtAuthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .authorizeRequests()
              //.requestMatchers("/test").authenticated().requestMatchers("/auth/login").permitAl l()
                .requestMatchers(pUBLIC_URL).permitAll()
                //.requestMatchers(HttpMethod.GET).permitAll())
                .anyRequest()
                .authenticated()
                .and().exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        
        http.authenticationProvider(daoAuthenticationProvider());
        DefaultSecurityFilterChain builddDefaultSecurityFilterChain= http.build();
        return builddDefaultSecurityFilterChain;
    }
    
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception
    {
    	return authenticationConfiguration.getAuthenticationManager();
    }
    
    @Bean
    PasswordEncoder passwordEncoder()
    {
    	return new BCryptPasswordEncoder();
    }
    
    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider()
    {
    	 DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
         provider.setUserDetailsService(this.customUserDetailsService);
         provider.setPasswordEncoder(passwordEncoder());
         return provider;
    }
    


}
