package br.com.jowdev.projetosiplataformaempregos.models.Job;

import br.com.jowdev.projetosiplataformaempregos.models.BaseEntity;
import br.com.jowdev.projetosiplataformaempregos.models.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@IdClass(JobApplicationId.class)
@Builder
public class JobApplication extends BaseEntity {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    private Boolean approved;
    private String message;
}
