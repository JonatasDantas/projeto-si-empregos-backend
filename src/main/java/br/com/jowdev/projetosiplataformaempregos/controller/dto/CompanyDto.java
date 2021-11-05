package br.com.jowdev.projetosiplataformaempregos.controller.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import br.com.jowdev.projetosiplataformaempregos.models.Company;

@Data 
public class CompanyDto {

	private Long id;
	private String name;
	private String city;
	private String state;
	private String street;
	private Integer number;
	private String complement;
	private String logoUrl;

	public CompanyDto(Company company) {
		super();
		this.id = company.getId();
		this.name = company.getName();
		this.city = company.getCity();
		this.state = company.getState();
		this.street = company.getStreet();
		this.number = company.getNumber();
		this.complement = company.getComplement();
		this.logoUrl = company.getLogoUrl();
	}
	
	public static Page<CompanyDto> convert(Page<Company> companies) {
		return companies.map(CompanyDto::new);
	}

}
