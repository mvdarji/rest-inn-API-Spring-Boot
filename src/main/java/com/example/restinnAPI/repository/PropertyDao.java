package com.example.restinnAPI.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.restinnAPI.model.PropertyModel;
import com.example.restinnAPI.model.PropertyTypeModel;

// This interface helps to connect to MongoDB
public interface PropertyDao extends MongoRepository<PropertyModel, String> {
	public List<PropertyModel> findByType(PropertyTypeModel type);
}