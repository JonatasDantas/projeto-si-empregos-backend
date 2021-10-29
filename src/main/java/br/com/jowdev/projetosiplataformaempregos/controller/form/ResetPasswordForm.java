package br.com.jowdev.projetosiplataformaempregos.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.jowdev.projetosiplataformaempregos.models.PasswordResetToken;
import br.com.jowdev.projetosiplataformaempregos.models.user.User;

public class ResetPasswordForm {
	@NotNull
	@NotEmpty
	private String token;
	
	@NotNull
	@NotEmpty
	private String password;

	@NotNull
	@NotEmpty
	private String passwordConfirmation;

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
	
	public User updateUser(PasswordResetToken token) {
		User user = token.getUser();
		String hashedPassword = new BCryptPasswordEncoder().encode(password);
		
		user.setPassword(hashedPassword);
		
		return user;
	}
}
