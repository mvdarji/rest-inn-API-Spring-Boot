package com.example.restinnAPI.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.restinnAPI.model.PropertyTypeModel;

public interface PropertyTypeDao extends MongoRepository<PropertyTypeModel, String> {
	public List<PropertyTypeModel> findByPropType(String propType);
}
