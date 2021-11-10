package br.com.jowdev.projetosiplataformaempregos.controller.dto.job;

import br.com.jowdev.projetosiplataformaempregos.controller.dto.CompanyDto;
import br.com.jowdev.projetosiplataformaempregos.controller.dto.KnowledgeDto;
import br.com.jowdev.projetosiplataformaempregos.models.Job.Job;
import br.com.jowdev.projetosiplataformaempregos.models.Knowledge;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDetailsDto {
	private Long id;
	private String title;
	private String description;
	private String fullDescription;
	private Float salary;
	private List<KnowledgeDto> knowledges;
	private CompanyDto company;
	private LocalDateTime createdAt;

	public JobDetailsDto(Job job) {
		super();
		this.id = job.getId();
		this.title = job.getTitle();
		this.description = job.getDescription();
		this.fullDescription = job.getFullDescription();
		this.salary = job.getSalary();
		this.knowledges = job.getKnowledges().stream().map(KnowledgeDto::new).collect(Collectors.toList());
		this.company = new CompanyDto(job.getCompany());
		this.createdAt = job.getCreatedAt();
	}
}
