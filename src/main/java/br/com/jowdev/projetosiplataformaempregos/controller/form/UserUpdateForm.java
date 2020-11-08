package br.com.jowdev.projetosiplataformaempregos.controller.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.jowdev.projetosiplataformaempregos.models.User;
import br.com.jowdev.projetosiplataformaempregos.models.UserGender;

public class UserUpdateForm {
	@NotNull
	@NotEmpty
	private String firstName;

	@NotNull
	@NotEmpty
	private String lastName;

	@Email
	private String email;
	
	@NotNull
	private UserGender gender;

	@NotNull
	@NotEmpty
	private String cpf;

	@NotNull
	@NotEmpty
	private String phone;

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setGender(UserGender gender) {
		this.gender = gender;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public User update(User user) {
		user.setName(firstName.trim() + " " + lastName.trim());
		user.setEmail(email);
		user.setGender(gender);
		user.setCpf(cpf);
		user.setPhone(phone);
		
		return user;
	}
}
