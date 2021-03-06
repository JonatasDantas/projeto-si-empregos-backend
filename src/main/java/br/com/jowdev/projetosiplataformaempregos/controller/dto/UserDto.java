package br.com.jowdev.projetosiplataformaempregos.controller.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.jowdev.projetosiplataformaempregos.models.Role;
import br.com.jowdev.projetosiplataformaempregos.models.User;

public class UserDto {

	private Long id;
	private String name;
	private String email;
	private String cpf;
	private String phone;
	private List<Role> role;
	private boolean emailVerified;

	public UserDto(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.cpf = user.getCpf();
		this.phone = user.getPhone();
		this.emailVerified = user.isEmailVerified();
		this.setRole(user.getRoles());
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

	public List<Role> getRole() {
		return role;
	}

	public void setRole(List<Role> role) {
		this.role = role;
	}

	public boolean isEmailVerified() {
		return emailVerified;
	}
	
	public static Page<UserDto> convert(Page<User> users) {
		return users.map(UserDto::new);
	}
}
