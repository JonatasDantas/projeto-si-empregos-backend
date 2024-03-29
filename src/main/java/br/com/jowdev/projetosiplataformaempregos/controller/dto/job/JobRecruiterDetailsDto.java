package br.com.jowdev.projetosiplataformaempregos.controller.dto.job;

import br.com.jowdev.projetosiplataformaempregos.controller.dto.KnowledgeDto;
import br.com.jowdev.projetosiplataformaempregos.controller.dto.user.UserDto;
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
public class JobRecruiterDetailsDto {

    private Long id;
    private String title;
    private String description;
    private String fullDescription;
    private String companyName;
    private String companyLogo;
    private Float salary;
    private List<KnowledgeDto> knowledges;
    private Long companyId;
    private List<JobApplicationDto> jobApplications;
	private LocalDateTime createdAt;

    public JobRecruiterDetailsDto(Job job) {
        this.id = job.getId();
        this.title = job.getTitle();
        this.description = job.getDescription();
        this.fullDescription = job.getFullDescription();
        this.salary = job.getSalary();
        this.knowledges = job.getKnowledges().stream().map(KnowledgeDto::new).collect(Collectors.toList());
        this.companyId = job.getCompany().getId();
        this.jobApplications = job.getJobApplications()
                            .stream()
                            .map(JobApplicationDto::new)
                            .collect(Collectors.toList());
        this.createdAt = job.getCreatedAt();
        this.companyName = job.getCompany().getName();
        this.companyLogo = job.getCompany().getLogoUrl();

    }

}
