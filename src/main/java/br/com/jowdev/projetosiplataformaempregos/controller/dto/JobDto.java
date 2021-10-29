package br.com.jowdev.projetosiplataformaempregos.controller.dto;

import br.com.jowdev.projetosiplataformaempregos.models.Job;
import br.com.jowdev.projetosiplataformaempregos.models.Knowledge;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDto {
	private long id;
	private String title;
	private String description;
	private Float salary;
	private List<Knowledge> knowledges;
	private String city;
	private String companyLogo;
	private LocalDateTime createdAt;

	public JobDto(Job job) {
		super();
		this.id = job.getId();
		this.title = job.getTitle();
		this.description = job.getDescription();
		this.salary = job.getSalary();
		this.knowledges = job.getKnowledges();
		this.city = job.getCompany().getCity();
		this.companyLogo = job.getCompany().getLogoUrl();
		this.createdAt = job.getCreatedAt();
	}
}
