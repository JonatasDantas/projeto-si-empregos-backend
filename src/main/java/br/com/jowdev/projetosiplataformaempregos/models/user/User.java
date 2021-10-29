package br.com.jowdev.projetosiplataformaempregos.models.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;
import javax.transaction.Transactional;

import br.com.jowdev.projetosiplataformaempregos.models.Company;
import br.com.jowdev.projetosiplataformaempregos.models.Job;
import br.com.jowdev.projetosiplataformaempregos.models.Role;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Data
@Table(name = "users")
public class User implements UserDetails {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String email;
	private String password;
	private String profilePic;

	@Enumerated(EnumType.STRING)
	private UserGender gender;

	private String cpf;
	private String phone;
	private boolean emailVerified = false;


	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<UserKnowledge> knowledges;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Role> roles = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<Company> companies = new ArrayList<>();

	@ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
	private List<Job> jobs = new ArrayList<>();

	public User() {
		super();
	}

	public User(String name, String email, String password, UserGender gender, String cpf, String phone,
			boolean emailVerified, List<Role> roles, List<Company> companies, String profilePic) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.cpf = cpf;
		this.phone = phone;
		this.emailVerified = emailVerified;
		this.roles = roles;
		this.companies = companies;
		this.profilePic = profilePic;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}

	public boolean isEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(boolean emailVerified) {
		this.emailVerified = emailVerified;
	}

	public List<Job> getJobApplications() {
		return jobs;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Transactional
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
