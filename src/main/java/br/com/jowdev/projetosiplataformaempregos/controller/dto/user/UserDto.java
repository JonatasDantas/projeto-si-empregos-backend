package br.com.jowdev.projetosiplataformaempregos.controller.dto.user;

import br.com.jowdev.projetosiplataformaempregos.controller.dto.KnowledgeDto;
import br.com.jowdev.projetosiplataformaempregos.models.Role;
import br.com.jowdev.projetosiplataformaempregos.models.user.User;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserDto {

	private Long id;
	private String name;
	private String email;
	private String cpf;
	private String phone;
	private List<Role> roles;
	private boolean emailVerified;
	private List<UserKnowledgeDto> knowledges;
	private String city;
	private String state;
	private String biography;

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
		this.state = user.getState();
		this.city = user.getCity();
		this.biography = user.getBiography();
	}
	public static Page<UserDto> convert(Page<User> users) {
		return users.map(UserDto::new);
	}
}
