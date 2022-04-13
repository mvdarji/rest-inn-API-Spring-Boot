package com.example.restinnAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.restinnAPI.model.UserModel;
import com.example.restinnAPI.service.UserService;

//In Spring's approach to building RESTful web services, HTTP requests are handled by a controller. 
//These components are identified by the @RestController annotation
@RestController
public class UserController {
	// Dependency Injection using @Autowired
	@Autowired
	private UserService userServiceObj;
	
	// for reading all users
	// @GetMapping handles the HTTP GET requests matched with given URI expression.
	@GetMapping("/user")
	public ResponseEntity<List<UserModel>> getAllUsers(){
		List<UserModel> allUsers = userServiceObj.getAllUsers();	
		if(allUsers.isEmpty() || allUsers == null) {
			return new ResponseEntity<List<UserModel>>(allUsers, HttpStatus.NOT_FOUND);			
		}
		return new ResponseEntity<List<UserModel>>(allUsers, HttpStatus.OK);
	}
	
	// for reading single user
	// @PathVariable: Annotation which indicates that a method parameter should be bound to a URI template variable.
	@GetMapping("/user/{userId}")
	public ResponseEntity<UserModel> getSingleUser(@PathVariable String userId){
	    UserModel singleUser = userServiceObj.getSingleUser(userId);
	    if(singleUser == null) {
	        return new ResponseEntity<UserModel>(new UserModel(), HttpStatus.NOT_FOUND);		
	    }
	    return new ResponseEntity<UserModel>(singleUser, HttpStatus.OK);
	}
	
	// for creating new user
	// @PostMapping handles the HTTP POST requests matched with given URI expression.
	@PostMapping( value = "/user", consumes = {
			MediaType.APPLICATION_JSON_VALUE
	})
	public ResponseEntity<UserModel> createUser(@RequestBody UserModel user){
		UserModel newUser = userServiceObj.createUser(user);
		return new ResponseEntity<UserModel>(newUser, HttpStatus.CREATED);
	}	
}