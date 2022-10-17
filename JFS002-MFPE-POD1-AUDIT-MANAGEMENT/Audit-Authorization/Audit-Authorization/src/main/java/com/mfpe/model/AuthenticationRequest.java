package com.mfpe.model;

import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;


@Component
@Getter
@ApiModel(value = "Authentication Request Model")
public class AuthenticationRequest {
	
	@ApiModelProperty(notes = "username of the project manager")
	@NotEmpty
	private String userName;
	@ApiModelProperty(notes = "password of the project manager")
	@NotEmpty
	private String password;
}
