package br.com.jowdev.projetosiplataformaempregos.controller.dto;

import br.com.jowdev.projetosiplataformaempregos.models.Qualification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public @Data class QualificationDto {

	private Long id;
	private String name;
	
	public QualificationDto(Qualification qualification) {
		id = qualification.getId();
		name = qualification.getName();
	}
	
}
