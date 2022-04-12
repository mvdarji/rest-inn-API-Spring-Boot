package com.example.restinnAPI.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.restinnAPI.model.PropertyModel;

// This interface helps to connect to MongoDB
public interface PropertyDao extends MongoRepository<PropertyModel, String> {
}