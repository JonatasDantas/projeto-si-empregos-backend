package br.com.jowdev.projetosiplataformaempregos.models.user;

import br.com.jowdev.projetosiplataformaempregos.models.Knowledge;
import br.com.jowdev.projetosiplataformaempregos.models.Job.Job;
import br.com.jowdev.projetosiplataformaempregos.models.Job.JobApplication;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@IdClass(UserKnowledgeId.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserKnowledge {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "knowledge_id")
    Knowledge knowledge;

    @Enumerated(EnumType.STRING)
    KnowledgeLevel knowledgeLevel;
    boolean validated;
}
