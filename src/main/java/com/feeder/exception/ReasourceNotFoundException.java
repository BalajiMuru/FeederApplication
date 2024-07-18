package com.feeder.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReasourceNotFoundException extends RuntimeException{
	
	String resourceName;
	String fieldName;
	long fieldValue;
	
	
	public ReasourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
		super(String.format("%s not found %s : %s", resourceName,fieldName,fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	

}
