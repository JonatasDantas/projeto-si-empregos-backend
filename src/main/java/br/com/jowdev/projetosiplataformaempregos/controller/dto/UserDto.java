package br.com.jowdev.projetosiplataformaempregos.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.jowdev.projetosiplataformaempregos.models.User;

public class UserDto {

	private Long id;
	private String name;
	private String email;
	private String cpf;
	private String phone;
	private boolean emailVerified;
	private List<RoleDto> roles;

	public UserDto(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.cpf = user.getCpf();
		this.phone = user.getPhone();
		this.emailVerified = user.isEmailVerified();
		this.roles = new ArrayList<>();
		this.roles.addAll(user.getRoles().stream().map(RoleDto::new).collect(Collectors.toList()));
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getCpf() {
		return cpf;
	}

	public String getPhone() {
		return phone;
	}

	public List<RoleDto> getRoles() {
		return roles;
	}

	public boolean isEmailVerified() {
		return emailVerified;
	}
	
	public static Page<UserDto> convert(Page<User> users) {
		return users.map(UserDto::new);
	}
}
