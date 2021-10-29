package br.com.jowdev.projetosiplataformaempregos.controller.dto;

import br.com.jowdev.projetosiplataformaempregos.models.Knowledge;
import lombok.Data;

@Data
public class KnowledgeDto {
    private Long id;
    private String name;

    public KnowledgeDto(Knowledge knowledge) {
        this.id = knowledge.getId();
        this.name = knowledge.getName();
    }
}
