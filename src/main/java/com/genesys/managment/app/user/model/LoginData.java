package com.genesys.managment.app.user.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class LoginData {
	
	@NotBlank(message = "Username is mandatory")
	@Size(min = 3, max=20)
    private String username;
	
	@NotBlank(message = "Password is mandatory")
	@Size(min = 3, max=20)
    private String password;

}
