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
    private List<ContentsDto> dto;

    public KnowledgeDto(Knowledge knowledge) {
        this.id = knowledge.getId();
        this.name = knowledge.getName();
        this.dto = knowledge.getContents().stream().map(ContentsDto::new).collect(Collectors.toList());
    }
}
