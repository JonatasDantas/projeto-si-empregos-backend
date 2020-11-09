package br.com.jowdev.projetosiplataformaempregos.controller.form;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.jowdev.projetosiplataformaempregos.models.Company;
import br.com.jowdev.projetosiplataformaempregos.models.Job;
import br.com.jowdev.projetosiplataformaempregos.models.Occupation;
import br.com.jowdev.projetosiplataformaempregos.repository.CompanyRepository;

public class JobForm {

	@NotNull
	@NotEmpty
	private String title;

	@NotNull
	@NotEmpty
	private String description;

	private Float salary;

	@NotNull
	private Occupation occupation;

	@NotNull
	private Long companyId;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getSalary() {
		return salary;
	}

	public void setSalary(Float salary) {
		this.salary = salary;
	}

	public Occupation getOccupation() {
		return occupation;
	}

	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	
	public Job convert(CompanyRepository companyRepository) {
		Optional<Company> company = companyRepository.findById(companyId);
		
		if (company.isPresent()) {
			return new Job(title, description, salary, occupation, company.get());
		}
		
		return null;
	}
}
