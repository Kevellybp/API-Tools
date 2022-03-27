package com.api.tools.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table (name = "TB_TOOLS")
@Data
public class ToolsModel implements Serializable {
	private static final long serialVersionUID = 1l;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column (name = "tile", unique = true, length =50)
	private String title;
	
	@Column (name ="links", length= 100)
	private String links;
	
	@Column (name ="description", length =256)
	private String description;
	
	
	@Column (name = "tags")
	@Size (min =0, max = 8)
	private String[] tags;
	

}
