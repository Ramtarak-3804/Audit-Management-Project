package com.mfpe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="project_manager")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ApiModel(value = "Project Manager table to store the project details")
public class ProjectManager{
	
	@ApiModelProperty(notes = "id of the project manager table")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ApiModelProperty(notes = "name of the project manager")
	private String name;
	@ApiModelProperty(notes = "unique username of the project manager")
	@Column(unique = true)
	private String username;
	@ApiModelProperty(notes = "password of the project manager")
	private String password;
	
}