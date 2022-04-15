package com.example.restinnAPI.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restinnAPI.model.PropertyTypeModel;
import com.example.restinnAPI.repository.PropertyTypeDao;

@Service
public class PropertyTypeService {
	@Autowired
	private PropertyTypeDao propertyTypeDaoObj;

	public PropertyTypeModel createPropertyType(PropertyTypeModel type) {
		return propertyTypeDaoObj.insert(type);
	}

	public List<PropertyTypeModel> getAllPropTypes() {
		return propertyTypeDaoObj.findAll();
	}
}
