package br.com.jowdev.projetosiplataformaempregos.controller.dto;

import br.com.jowdev.projetosiplataformaempregos.models.User;

public class TokenDto {
	private String token;
	private String type;
	private UserDto user;

	public TokenDto(String token, String type, User user) {
		super();
		this.token = token;
		this.type = type;
		this.user = new UserDto(user);
	}

	public String getToken() {
		return token;
	}

	public String getType() {
		return type;
	}

	public UserDto getUser() {
		return user;
	}
}
