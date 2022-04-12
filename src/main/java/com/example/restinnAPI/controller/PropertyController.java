package com.example.restinnAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	// for reading bestseller properties
	// @RequestParams extract values from the query string
	@GetMapping("/property/bestseller")
	public ResponseEntity<List<PropertyModel>> getBestsellerProperties(@RequestParam(name = "limit", required = false) String limit){
		List<PropertyModel> bestSellerProps = propServiceObj.getBestsellerProperties(limit);
		if(bestSellerProps == null) {
			return new ResponseEntity<List<PropertyModel>>(bestSellerProps, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<PropertyModel>>(bestSellerProps, HttpStatus.OK);
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
	
	// for filtering properties by title
	@GetMapping("/property/search")
	public ResponseEntity<List<PropertyModel>> getPropertiesByTitle(@RequestParam String searchTerm){
		List<PropertyModel> searchResults = propServiceObj.getPropertiesByTitle(searchTerm);
		if(searchResults == null) {
			return new ResponseEntity<List<PropertyModel>>(searchResults, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<PropertyModel>>(searchResults, HttpStatus.OK);
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
	
	// for updating found property or add this new property
	// @PutMapping handles the HTTP PUT requests matched with given URI expression.
	@PutMapping(value = "/property/{propId}", consumes = {
			MediaType.APPLICATION_JSON_VALUE
	})
	public ResponseEntity<PropertyModel> updateProperty(@RequestBody PropertyModel prop, @PathVariable String propId){
		PropertyModel propFromDB = propServiceObj.getSingleProperty(propId);
		if(propFromDB == null) {
			PropertyModel newProp = propServiceObj.createProperty(prop);
			return new ResponseEntity<PropertyModel>(newProp, HttpStatus.CREATED);
		}else {
			prop.setId(propId);
			PropertyModel newProp = propServiceObj.updateProperty(prop);
			return new ResponseEntity<PropertyModel>(newProp, HttpStatus.OK);
		}
	}
	
	// for deleting specific property
	// @DeleteMapping handles the HTTP DELETE requests matched with given URI expression.
	@DeleteMapping("/property/{propId}")
	public ResponseEntity<String> deleteProperty(@PathVariable String propId){
		PropertyModel propFromDB = propServiceObj.getSingleProperty(propId);
		if(propFromDB == null) {
			return new ResponseEntity<String>("Property ID: " + propId + " not found.", HttpStatus.NOT_FOUND);
		}
		propServiceObj.deleteProperty(propId);
		return new ResponseEntity<String>("Property ID: " + propId + " deleted.", HttpStatus.OK);
	}
}
