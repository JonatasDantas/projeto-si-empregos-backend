package br.com.jowdev.projetosiplataformaempregos.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(example = "{\r\n" + 
		"  \"email\": \"admin@admin.com\",\r\n" + 
		"  \"password\": \"123456\"\r\n" + 
		"}")
public class LoginForm {
	private String email;
	private String password;

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
}
