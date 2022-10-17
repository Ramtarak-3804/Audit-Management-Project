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
@ApiModel(value = "Authentication Response Model")
public class AuthenticationResponse {
	/*@ApiModelProperty(notes = "name of the Project manager")
	private String name;*/
	@ApiModelProperty(notes = "check isValid")
	private boolean isValid;
}
