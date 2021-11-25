package br.com.jowdev.projetosiplataformaempregos.controller.dto.job;

import br.com.jowdev.projetosiplataformaempregos.controller.dto.user.UserDto;
import br.com.jowdev.projetosiplataformaempregos.models.Job.JobApplication;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JobApplicationDto {

    private UserDto user;
    private JobDto job;
    private Boolean approved;
    private String message;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public JobApplicationDto(JobApplication app) {
        this.user = new UserDto(app.getUser());
        this.job = new JobDto(app.getJob());
        this.approved = app.getApproved();
        this.message = app.getMessage();
        this.createdAt = app.getCreatedAt();
        this.updatedAt = app.getUpdatedAt();
    }

}
