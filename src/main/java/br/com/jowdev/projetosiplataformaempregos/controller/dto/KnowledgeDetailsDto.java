package br.com.jowdev.projetosiplataformaempregos.controller.dto;


import br.com.jowdev.projetosiplataformaempregos.controller.dto.job.ContentsDto;
import br.com.jowdev.projetosiplataformaempregos.models.Knowledge;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class KnowledgeDetailsDto extends KnowledgeDto {

    private List<ContentsDto> contents;

    public KnowledgeDetailsDto(Knowledge knowledge) {
        super(knowledge);
        this.contents = knowledge.getContents().stream()
                .map(ContentsDto::new)
                .collect(Collectors.toList());
    }
}