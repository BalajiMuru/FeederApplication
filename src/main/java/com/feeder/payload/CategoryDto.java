package com.feeder.payload;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CategoryDto {
	
    private Integer categoryId;
	private String categoryTitle;
	private String categoryDescription;


}
