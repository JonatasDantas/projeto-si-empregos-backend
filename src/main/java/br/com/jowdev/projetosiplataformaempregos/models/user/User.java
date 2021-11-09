package br.com.jowdev.projetosiplataformaempregos.models.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.transaction.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.jowdev.projetosiplataformaempregos.models.Company;
import br.com.jowdev.projetosiplataformaempregos.models.Experience;
import br.com.jowdev.projetosiplataformaempregos.models.Role;
import br.com.jowdev.projetosiplataformaempregos.models.Job.JobApplication;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
	private String city;
	private String state;
	private String biography;
	@Lob
	@Column(name = "full_biography", length = 2147483647)
	private String fullBiography;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<UserKnowledge> knowledges;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Role> roles = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<Company> companies = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	private List<Experience> experience = new ArrayList<>();

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<JobApplication> jobApplications = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE })
	private List<Certificate> certificates = new ArrayList<>();

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

	public List<JobApplication> getJobApplications() {
		return jobApplications;
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
