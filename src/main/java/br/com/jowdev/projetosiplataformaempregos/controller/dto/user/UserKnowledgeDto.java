package br.com.jowdev.projetosiplataformaempregos.controller.dto.user;

import br.com.jowdev.projetosiplataformaempregos.controller.dto.KnowledgeDto;
import br.com.jowdev.projetosiplataformaempregos.models.user.KnowledgeLevel;
import br.com.jowdev.projetosiplataformaempregos.models.user.UserKnowledge;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class UserKnowledgeDto {

    KnowledgeDto knowledge;
    @Enumerated(EnumType.STRING)
    KnowledgeLevel knowledgeLevel;
    boolean validated;

    public UserKnowledgeDto(UserKnowledge userKnowledge) {
        this.knowledge = new KnowledgeDto(userKnowledge.getKnowledge());
        this.knowledgeLevel = userKnowledge.getKnowledgeLevel();
        this.validated = userKnowledge.isValidated();
    }

}
