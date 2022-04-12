package com.example.restinnAPI.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	// read all properties from DB
	public List<PropertyModel> getAllProperties() {
		return propertyDaoObj.findAll();
	}

	// read bestseller properties from DB
	public List<PropertyModel> getBestsellerProperties() {
		List<PropertyModel> bestSellerProps = new ArrayList<PropertyModel>();
		List<PropertyModel> allProps = propertyDaoObj.findAll();

        for (PropertyModel property: allProps) {
            if (property.isBestSeller()) {
            	bestSellerProps.add(property);
            }
        }
        
        if(bestSellerProps.isEmpty()) {
        	return null;
        }
		return bestSellerProps;
	}
	
	// read single property from DB
	public PropertyModel getSingleProperty(String propId) {
		Optional<PropertyModel> singleProp = propertyDaoObj.findById(propId);
		if(singleProp.isPresent()) {
			return singleProp.get();
		}
		return null;
	}
	
	// create new property in DB
	public PropertyModel createProperty(PropertyModel property) {
		PropertyModel newPropCreated = propertyDaoObj.insert(property);
		return newPropCreated;
	}

	// delete single property in DB
	public void deleteProperty(String propId) {
		propertyDaoObj.deleteById(propId);
	}
}