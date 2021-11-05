package br.com.jowdev.projetosiplataformaempregos.controller.dto;

import org.springframework.data.domain.Page;

import br.com.jowdev.projetosiplataformaempregos.models.Company;
import br.com.jowdev.projetosiplataformaempregos.models.Experience;
import lombok.Data;

@Data
public class ExperienceDto {
	
	private String office;
	private String companyName;
	private String initialDate;
	private String endDate;

	public ExperienceDto(Experience experience) {
		super();
		this.office = experience.getOffice();
		this.companyName = experience.getCompanyName();
		this.initialDate = experience.getInitialDate();
		this.endDate = experience.getEndDate();
	}
	
	public static Page<ExperienceDto> convert(Page<Experience> experience) {
		return experience.map(ExperienceDto::new);
	}

}
