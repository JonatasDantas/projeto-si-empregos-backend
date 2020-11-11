package br.com.jowdev.projetosiplataformaempregos.controller.dto;

import br.com.jowdev.projetosiplataformaempregos.models.Job;
import br.com.jowdev.projetosiplataformaempregos.models.Occupation;

public class JobDetailsDto {
	private Long id;
	private String title;
	private String description;
	private Float salary;
	private Occupation occupation;
	private CompanyDto company;

	public JobDetailsDto(Job job) {
		super();
		this.id = job.getId();
		this.title = job.getTitle();
		this.description = job.getDescription();
		this.salary = job.getSalary();
		this.occupation = job.getOccupation();
		this.company = new CompanyDto(job.getCompany());
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Float getSalary() {
		return salary;
	}

	public Occupation getOccupation() {
		return occupation;
	}

	public CompanyDto getCompany() {
		return company;
	}

}
