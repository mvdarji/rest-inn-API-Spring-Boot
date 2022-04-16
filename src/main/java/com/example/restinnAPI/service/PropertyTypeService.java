package com.example.restinnAPI.service;

import java.util.ArrayList;
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

	public List<PropertyTypeModel> getAllPropTypes(String number) {
		List<PropertyTypeModel> allPropTypes = propertyTypeDaoObj.findAll();
		
		// return all prop types if number is null
		if(number == null) {
			return allPropTypes;
		}		
		
		int limit = Integer.parseInt(number);
		
		// return all prop types if limit is less than or equal to 0
		if(limit <= 0) {
			return allPropTypes;
		}
				
		List<PropertyTypeModel> limitedPropTypes = new ArrayList<PropertyTypeModel>();
        for (PropertyTypeModel propType: allPropTypes) {
        	if(limitedPropTypes.size() == limit) {
        		break;
        	}
        	limitedPropTypes.add(propType);        		
        }		
		return limitedPropTypes;
	}
}
