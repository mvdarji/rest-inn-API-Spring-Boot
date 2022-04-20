package com.example.restinnAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.restinnAPI.model.PropertyTypeModel;
import com.example.restinnAPI.service.PropertyTypeService;

@CrossOrigin(origins = "https://rest-inn-mvdarji.netlify.app/")
@RestController
public class PropertyTypeController {
	@Autowired
	private PropertyTypeService propertyTypeServiceObj;
	
	// for reading all property types
	// @GetMapping handles the HTTP GET requests matched with given URI expression.
	@GetMapping("/propertyTypes")
	public ResponseEntity<List<PropertyTypeModel>> getAllPropTypes(@RequestParam(name = "limit", required = false) String limit){
		List<PropertyTypeModel> allPropTypes = propertyTypeServiceObj.getAllPropTypes(limit);	
		if(allPropTypes.isEmpty() || allPropTypes == null) {
			return new ResponseEntity<List<PropertyTypeModel>>(allPropTypes, HttpStatus.NOT_FOUND);			
		}
		return new ResponseEntity<List<PropertyTypeModel>>(allPropTypes, HttpStatus.OK);
	}
	
	// for creating new property type
	// @PostMapping handles the HTTP POST requests matched with given URI expression.
	@PostMapping(value = "/propertyTypes", consumes = {
			MediaType.APPLICATION_JSON_VALUE
	})
	public ResponseEntity<PropertyTypeModel> createPropertyType(@RequestBody PropertyTypeModel type){
		PropertyTypeModel newPropType = propertyTypeServiceObj.createPropertyType(type);
		return new ResponseEntity<PropertyTypeModel>(newPropType, HttpStatus.CREATED);
	}
}
