package com.example.restinnAPI.model;

import org.springframework.data.annotation.Id;

public class PropertyTypeModel {
	@Id
	private String id;
	private String type;
	
	public PropertyTypeModel() {
		super();
	}

	public PropertyTypeModel(String id, String type) {
		super();
		this.id = id;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "PropertyTypeModel [id=" + id + ", type=" + type + "]";
	}	
}