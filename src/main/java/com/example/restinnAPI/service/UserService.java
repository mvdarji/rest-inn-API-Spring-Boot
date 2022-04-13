package com.example.restinnAPI.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restinnAPI.model.UserModel;
import com.example.restinnAPI.repository.UserDao;

//We mark beans with @Service to indicate that they're holding the business logic
@Service
public class UserService {
	@Autowired
	private UserDao userDaoObj;

	// read all users from DB
	public List<UserModel> getAllUsers() {
		return userDaoObj.findAll();
	}

	// create new user in DB
	public UserModel createUser(UserModel user) {
		return userDaoObj.insert(user);
	}
}