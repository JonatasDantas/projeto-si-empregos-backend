package br.com.jowdev.projetosiplataformaempregos.controller.dto;

import br.com.jowdev.projetosiplataformaempregos.models.Job;
import br.com.jowdev.projetosiplataformaempregos.models.Occupation;

public class JobDto {
	private String title;
	private String description;
	private Float salary;
	private Occupation occupation;
	private String city;

	public JobDto(Job job) {
		super();
		this.title = job.getTitle();
		this.description = job.getDescription();
		this.salary = job.getSalary();
		this.occupation = job.getOccupation();
		this.city = job.getCompany().getCity();
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

	public String getCity() {
		return city;
	}

}
