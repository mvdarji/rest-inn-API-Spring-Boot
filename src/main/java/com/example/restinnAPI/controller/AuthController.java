package com.example.restinnAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.restinnAPI.model.UserModel;
import com.example.restinnAPI.repository.UserDao;

@CrossOrigin(origins = "https://rest-inn-mvdarji.netlify.app/")
@RestController
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManagerObj;
	
	@Autowired
	private UserDao userDaoObj;
	
	@PostMapping(value = "/auth", consumes = {
		MediaType.APPLICATION_JSON_VALUE
	})
	private ResponseEntity<UserModel> login(@RequestBody UserModel user){
		try {
			authenticationManagerObj.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
			UserModel fullUserDetails = userDaoObj.findByEmail(user.getEmail());
			return new ResponseEntity<UserModel>(fullUserDetails, HttpStatus.OK);
		} catch (BadCredentialsException e) {
			return new ResponseEntity<UserModel>(user, HttpStatus.UNAUTHORIZED);
		}
	}
}