package br.com.jowdev.projetosiplataformaempregos.controller.dto;

import org.springframework.data.domain.Page;

import br.com.jowdev.projetosiplataformaempregos.models.Company;

public class CompanyDto {

	private Long id;
	private String name;
	private String city;
	private String state;
	private String street;
	private Integer number;
	private String complement;

	public CompanyDto(Company company) {
		super();
		this.id = company.getId();
		this.name = company.getName();
		this.city = company.getCity();
		this.state = company.getState();
		this.street = company.getStreet();
		this.number = company.getNumber();
		this.complement = company.getComplement();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getStreet() {
		return street;
	}

	public Integer getNumber() {
		return number;
	}

	public String getComplement() {
		return complement;
	}
	
	public static Page<CompanyDto> convert(Page<Company> companies) {
		return companies.map(CompanyDto::new);
	}

}
