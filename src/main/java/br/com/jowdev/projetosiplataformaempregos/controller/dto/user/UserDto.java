package br.com.jowdev.projetosiplataformaempregos.controller.dto.user;

import br.com.jowdev.projetosiplataformaempregos.controller.dto.KnowledgeDto;
import br.com.jowdev.projetosiplataformaempregos.models.Role;
import br.com.jowdev.projetosiplataformaempregos.models.user.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class UserDto {

	private Long id;
	private String name;
	private String email;
	private String cpf;
	private String phone;
	private List<Role> roles;
	private boolean emailVerified;
	private List<UserKnowledgeDto> knowledges;

	public UserDto(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.cpf = user.getCpf();
		this.phone = user.getPhone();
		this.emailVerified = user.isEmailVerified();
		this.setRoles(user.getRoles());
		this.knowledges = user
				.getKnowledges()
				.stream()
				.map(UserKnowledgeDto::new)
				.collect(Collectors.toList());
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public boolean isEmailVerified() {
		return emailVerified;
	}
	
	public static Page<UserDto> convert(Page<User> users) {
		return users.map(UserDto::new);
	}
}
