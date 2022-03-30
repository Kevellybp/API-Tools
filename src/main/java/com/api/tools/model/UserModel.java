package com.api.tools.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table (name = "USER_TB")
@Data
public class UserModel implements Serializable {
	private static final long serialVersionUID = 1l;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column (name ="user_name")
	private String userName;
	
	@NotNull
	@Size(min = 5, max = 100)
	@Column (name = "email")
	private String email;
	
	@Column (name ="password")
	@Size(min = 5, max = 100)
	private String password;
	

	@OneToMany(mappedBy = "created_user", fetch = FetchType.EAGER)
	@JsonIgnoreProperties({"created_user"})
	private List<ToolsModel> user_tools = new ArrayList<>();
}
