package br.com.jowdev.projetosiplataformaempregos.controller.form;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.jowdev.projetosiplataformaempregos.models.Company;
import br.com.jowdev.projetosiplataformaempregos.models.user.User;
import br.com.jowdev.projetosiplataformaempregos.repository.UserRepository;

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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Company convert(Long userId, UserRepository userRepository) {
		Optional<User> user = userRepository.findById(userId);

		if (user.isPresent()) {
			return new Company(cnpj, name, street, number, complement, cep, city, state, user.get());
		}

		return null;
	}
}
