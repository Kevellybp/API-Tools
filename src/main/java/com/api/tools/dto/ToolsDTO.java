package com.api.tools.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ToolsDTO {
	
	@NotBlank
	private String title;
	
	@NotBlank
	private String links;
	
	@NotBlank
	@Size (max = 256)
	private String description;
	
//	@NotBlank
	private String[] tags = new String[8];

	
}
