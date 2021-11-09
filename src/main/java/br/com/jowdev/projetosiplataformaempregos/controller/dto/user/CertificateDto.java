package br.com.jowdev.projetosiplataformaempregos.controller.dto.user;

import br.com.jowdev.projetosiplataformaempregos.controller.dto.ExperienceDto;
import br.com.jowdev.projetosiplataformaempregos.models.user.Certificate;
import lombok.Data;

@Data
public class CertificateDto {
	private String name;
	private String url;
	
	public CertificateDto(Certificate certificate) {
		this.name = certificate.getName();
		this.url = certificate.getUrl();
	}
}
