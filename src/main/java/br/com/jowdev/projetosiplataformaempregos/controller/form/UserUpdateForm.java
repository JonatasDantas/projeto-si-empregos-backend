package br.com.jowdev.projetosiplataformaempregos.controller.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;

import br.com.jowdev.projetosiplataformaempregos.models.Experience;
import br.com.jowdev.projetosiplataformaempregos.models.user.User;
import br.com.jowdev.projetosiplataformaempregos.models.user.UserGender;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class UserUpdateForm {

	@Size(min = 3)
	private String name;

	@Email
	private String email;

	@URL
	private String profilePic;

	private UserGender gender;

	@Size(min = 11, max = 11)
	@JsonProperty("cpfcnpj")
	private String cpf;

	@Size(min = 10, max = 11)
	private String phone;
	
	private String biography;
	
	private List<Experience> experience;

	
	public User update(User user) {
		if(name != null)
			user.setName(name.trim());

		if(email != null)
			user.setEmail(email);

		if(gender != null)
			user.setGender(gender);

		if(cpf != null)
			user.setCpf(cpf);

		if(phone != null)
			user.setPhone(phone);

		if(profilePic != null)
			user.setProfilePic(profilePic);
		
		if(biography != null)
			user.setBiography(biography);
		
		if(experience != null) {
			List<Experience> newexperience = user.getExperience();
			newexperience.addAll(experience);
			user.setExperience(newexperience);
		}
			

		return user;
	}
}
