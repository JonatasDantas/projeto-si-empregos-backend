package br.com.jowdev.projetosiplataformaempregos.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.jowdev.projetosiplataformaempregos.controller.dto.job.ContentsDto;
import br.com.jowdev.projetosiplataformaempregos.models.Knowledge;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KnowledgeDto {
    private Long id;
    private String name;
    private String description;
    private String image;

    public KnowledgeDto(Knowledge knowledge) {
        this.id = knowledge.getId();
        this.name = knowledge.getName();
        this.description = knowledge.getDescription();
        this.image = knowledge.getImageUrl();
    }
}
