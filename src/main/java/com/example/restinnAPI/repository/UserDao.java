package com.example.restinnAPI.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.restinnAPI.model.UserModel;

//This interface helps to connect to MongoDB
public interface UserDao extends MongoRepository<UserModel, String> {
	// this tells mongoDB that there needs to be an implementation for this
	UserModel findByEmail(String email);
}