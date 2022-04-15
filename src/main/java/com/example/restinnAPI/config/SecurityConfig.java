package com.example.restinnAPI.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.restinnAPI.service.UserService;

//Spring boot security
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	// UserService should implement userDetailsService & override loadUserByUsername method
	@Autowired
	private UserService userServiceObj;
	
	// This allows us to configure our AUTHENTICATION part (login part)
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userServiceObj);
	}
	
	// This allows us to configure our AUTHORIZATION part (URL access controls)
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/properties/**").permitAll()
			.antMatchers("/propertyType/**").permitAll()			
			.antMatchers("/users/**").permitAll()
			.antMatchers("/auth").permitAll()
			.antMatchers("/register").permitAll()
			.anyRequest().authenticated();
	}
	
	// Spring @Bean annotation tells that a method produces a bean to be managed by the Spring container. 
	// It is a method-level annotation. During Java configuration ( @Configuration ), 
	// the method is executed and its return value is registered as a bean within a BeanFactory
	// BCrypt Password Encoder 
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// this is our AuthenticationManager
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
}