package br.com.jowdev.projetosiplataformaempregos.controller.form;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.jowdev.projetosiplataformaempregos.models.Company;
import br.com.jowdev.projetosiplataformaempregos.models.user.User;
import br.com.jowdev.projetosiplataformaempregos.repository.UserRepository;
import lombok.Data;

@Data
public class CompanyForm {

	@NotNull
	@NotEmpty
	private String cnpj;

	@NotNull
	@NotEmpty
	private String name;

	@NotNull
	@NotEmpty
	private String street;

	@NotNull
	private Integer number;

	private String complement;

	@NotNull
	@NotEmpty
	private String cep;

	@NotNull
	@NotEmpty
	private String city;

	@NotNull
	@NotEmpty
	private String state;

	@NotNull
	@NotEmpty
	private String imageUrl;


	public Company convert(Long userId, UserRepository userRepository) {
		Optional<User> user = userRepository.findById(userId);

		if (user.isPresent()) {
			return new Company(cnpj, name, street, number, complement, cep, city, state, user.get(), imageUrl);
		}

		return null;
	}
}
