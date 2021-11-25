package br.com.jowdev.projetosiplataformaempregos.controller.dto.job;

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
public class JobDto {
	private long id;
	private String title;
	private String description;
	private Float salary;
	private List<KnowledgeDto> knowledges;
	private String city;
	private String companyLogo;
	private String companyName;
	private LocalDateTime createdAt;

	public JobDto(Job job) {
		super();
		this.id = job.getId();
		this.title = job.getTitle();
		this.description = job.getDescription();
		this.salary = job.getSalary();
		this.knowledges = job.getKnowledges().stream().map(KnowledgeDto::new).collect(Collectors.toList());
		this.city = job.getCompany().getCity();
		this.companyLogo = job.getCompany().getLogoUrl();
		this.companyName = job.getCompany().getName();
		this.createdAt = job.getCreatedAt();
	}
}
