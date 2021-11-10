package br.com.jowdev.projetosiplataformaempregos.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.jowdev.projetosiplataformaempregos.controller.dto.job.ContentsDto;
import br.com.jowdev.projetosiplataformaempregos.models.Knowledge;
import lombok.Data;

@Data
public class KnowledgeDto {
    private Long id;
    private String name;
    private String description;
    private List<ContentsDto> contents;

    public KnowledgeDto(Knowledge knowledge) {
        this.id = knowledge.getId();
        this.name = knowledge.getName();
        this.description = knowledge.getDescription();
        this.contents = knowledge.getContents().stream().map(ContentsDto::new).collect(Collectors.toList());
    }
}
