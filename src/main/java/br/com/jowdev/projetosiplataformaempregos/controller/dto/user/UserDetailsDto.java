package br.com.jowdev.projetosiplataformaempregos.controller.dto.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.jowdev.projetosiplataformaempregos.controller.dto.CompanyDto;
import br.com.jowdev.projetosiplataformaempregos.controller.dto.JobDto;
import br.com.jowdev.projetosiplataformaempregos.controller.dto.KnowledgeDto;
import br.com.jowdev.projetosiplataformaempregos.controller.dto.RoleDto;
import br.com.jowdev.projetosiplataformaempregos.models.user.User;
import br.com.jowdev.projetosiplataformaempregos.models.user.UserGender;
import lombok.Data;


@Data
public class UserDetailsDto {
	private Long id;
	private String name;
	private String email;
	private String cpf;
	private String phone;
	private String profilePic;
	private UserGender gender;
	private boolean emailVerified;
	private List<RoleDto> roles;
	private List<CompanyDto> companies;
	private List<JobDto> applications;
	private List<UserKnowledgeDto> knowledges;


	public UserDetailsDto(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.cpf = user.getCpf();
		this.phone = user.getPhone();
		this.emailVerified = user.isEmailVerified();
		this.profilePic = user.getProfilePic();
		this.gender = user.getGender();

		this.roles = new ArrayList<>();
		this.roles.addAll(user.getRoles().stream().map(RoleDto::new).collect(Collectors.toList()));

		this.companies = new ArrayList<>();
		this.companies.addAll(user.getCompanies().stream().map(CompanyDto::new).collect(Collectors.toList()));
		
		this.applications = new ArrayList<>();
		this.applications.addAll(user.getJobApplications().stream().map(JobDto::new).collect(Collectors.toList()));

		this.knowledges = user
				.getKnowledges()
				.stream()
				.map(UserKnowledgeDto::new)
				.collect(Collectors.toList());
	}
}
