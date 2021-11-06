package br.com.jowdev.projetosiplataformaempregos.controller.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.jowdev.projetosiplataformaempregos.models.Experience;
import br.com.jowdev.projetosiplataformaempregos.models.user.User;
import br.com.jowdev.projetosiplataformaempregos.models.user.UserGender;
import br.com.jowdev.projetosiplataformaempregos.repository.ExperienceRepository;

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

	public User update(User user, ExperienceRepository experienceRepository) {
		if (name != null)
			user.setName(name.trim());

		if (email != null)
			user.setEmail(email);

		if (gender != null)
			user.setGender(gender);

		if (cpf != null)
			user.setCpf(cpf);

		if (phone != null)
			user.setPhone(phone);

		if (profilePic != null)
			user.setProfilePic(profilePic);

		if (biography != null)
			user.setBiography(biography);

		if (experience != null) {
			/*
			System.out.println("current user");
			System.out.println("has experience" + experience.size());

			List<Experience> newexperience = new ArrayList<>();
			
			experience.forEach(item -> {
				System.out.println("experience item" + item);
				item.setUser(user);
				newexperience.add(item);
			});
			
			System.out.print("new experience" + newexperience);
			*/
			
			List<Experience> newExperiences = new ArrayList<Experience>();
			
			experience.forEach(item -> {
				System.out.println("item experince: " + item);
				if (item.getId() == null) {
					Experience newExperience = new Experience(item.getOffice(), item.getCompanyName(), item.getInitialDate(), item.getEndDate(), user);

					experienceRepository.save(newExperience);
					
					newExperiences.add(newExperience);					
				} else {
					newExperiences.add(item);
				}
			});
			
			//user.setExperience(newExperiences);
		}

		return user;
	}
}
