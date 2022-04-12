package com.example.restinnAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restinnAPI.model.PropertyModel;
import com.example.restinnAPI.repository.PropertyDao;


// We mark beans with @Service to indicate that they're holding the business logic
@Service
public class PropertyService {
	// dependency injection as per singleton spring design pattern, only one object across the whole application
	@Autowired
	PropertyDao propertyDaoObj;

	// create new property in DB
	public PropertyModel createProperty(PropertyModel property) {
		PropertyModel newPropCreated = propertyDaoObj.insert(property);
		return newPropCreated;
	}
}