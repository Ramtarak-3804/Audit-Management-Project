package com.mfpe.model;

import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
@ApiModel(value = "JWT Response Model")
public class JWTResponse {

	@ApiModelProperty(notes = "jwt Token")
	private String authToken;
	@ApiModelProperty(notes = "Manager name")
	private String name;
	
}
