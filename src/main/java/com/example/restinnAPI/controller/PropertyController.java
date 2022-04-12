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

import com.example.restinnAPI.model.PropertyModel;
import com.example.restinnAPI.service.PropertyService;

// In Spring's approach to building RESTful web services, HTTP requests are handled by a controller. 
// These components are identified by the @RestController annotation
@RestController
public class PropertyController {
	// Dependency Injection using @Autowired
	@Autowired
	private PropertyService propServiceObj;
	
	// for reading all properties
	// @GetMapping handles the HTTP GET requests matched with given URI expression.
	@GetMapping("/property")
	public ResponseEntity<List<PropertyModel>> getAllProperties(){
		List<PropertyModel> allProps = propServiceObj.getAllProperties();	
		if(allProps.isEmpty() || allProps == null) {
			return new ResponseEntity<List<PropertyModel>>(allProps, HttpStatus.NOT_FOUND);			
		}
		return new ResponseEntity<List<PropertyModel>>(allProps, HttpStatus.OK);
	}
	
	// for reading single property
	// @PathVariable: Annotation which indicates that a method parameter should be bound to a URI template variable.
	@GetMapping("/property/{propId}")
	public ResponseEntity<PropertyModel> getSingleProperty(@PathVariable String propId){
		PropertyModel singleProp = propServiceObj.getSingleProperty(propId);
		if(singleProp == null) {
			return new ResponseEntity<PropertyModel>(new PropertyModel(), HttpStatus.NOT_FOUND);		
		}
		return new ResponseEntity<PropertyModel>(singleProp, HttpStatus.OK);
	}

	// for creating new property
	// @PostMapping handles the HTTP POST requests matched with given URI expression.
	@PostMapping(value = "/property", consumes = {
			MediaType.APPLICATION_JSON_VALUE
	})
	public ResponseEntity<PropertyModel> createProperty(@RequestBody PropertyModel property){
		PropertyModel newProp = propServiceObj.createProperty(property);
		return new ResponseEntity<PropertyModel>(newProp, HttpStatus.CREATED);
	}
}
