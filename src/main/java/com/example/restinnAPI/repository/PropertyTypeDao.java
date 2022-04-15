package com.example.restinnAPI.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.restinnAPI.model.PropertyTypeModel;

public interface PropertyTypeDao extends MongoRepository<PropertyTypeModel, String> {

}
