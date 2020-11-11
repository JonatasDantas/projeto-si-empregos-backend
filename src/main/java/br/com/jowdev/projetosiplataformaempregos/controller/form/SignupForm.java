package br.com.jowdev.projetosiplataformaempregos.controller.form;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.jowdev.projetosiplataformaempregos.models.Role;
import br.com.jowdev.projetosiplataformaempregos.models.User;
import br.com.jowdev.projetosiplataformaempregos.models.UserGender;
import br.com.jowdev.projetosiplataformaempregos.repository.RoleRepository;

public class SignupForm {

	@NotNull
	@NotEmpty
	private String firstName;

	@NotNull
	@NotEmpty
	private String lastName;

	@Email
	private String email;

	@NotNull
	@NotEmpty
	private String password;

	@NotNull
	private UserGender gender;

	@NotNull
	@NotEmpty
	private String cpf;

	@NotNull
	@NotEmpty
	private String phone;

	@NotNull
	private boolean recruiter;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserGender getGender() {
		return gender;
	}

	public void setGender(UserGender gender) {
		this.gender = gender;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isRecruiter() {
		return recruiter;
	}

	public void setRecruiter(boolean recruiter) {
		this.recruiter = recruiter;
	}

	public User convert(RoleRepository roleRepository) {
		List<Role> roles;

		if (isRecruiter()) {
			roles = roleRepository.findByName("ROLE_RECRUITER");
		} else {
			roles = roleRepository.findByName("ROLE_USER");			
		}

		String name = firstName.trim() + " " + lastName.trim();
		String hashedPassword = new BCryptPasswordEncoder().encode(password);
		
		return new User(name, email, hashedPassword, gender, cpf, phone, false, roles, new ArrayList<>());
	}
}
