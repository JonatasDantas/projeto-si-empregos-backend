package br.com.jowdev.projetosiplataformaempregos.models.user;

import br.com.jowdev.projetosiplataformaempregos.models.Knowledge;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@IdClass(UserKnowledgeId.class)
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
