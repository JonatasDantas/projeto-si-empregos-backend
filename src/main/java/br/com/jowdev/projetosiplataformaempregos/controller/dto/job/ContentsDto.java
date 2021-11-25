package br.com.jowdev.projetosiplataformaempregos.controller.dto.job;

import br.com.jowdev.projetosiplataformaempregos.models.ContentType;
import br.com.jowdev.projetosiplataformaempregos.models.Contents;
import br.com.jowdev.projetosiplataformaempregos.models.user.KnowledgeLevel;
import lombok.Data;


@Data
public class ContentsDto {

	private ContentType contentType;
	private String title;
	private String url;
	private String description;
	private String imageUrl;
	private KnowledgeLevel knowledgeLevel;
	
	public ContentsDto(Contents contents) {
		this.title = contents.getTitle();
		this.url = contents.getUrl();
		this.contentType = contents.getContentType();
		this.description = contents.getDescription();
		this.imageUrl = contents.getImageUrl();
		this.knowledgeLevel = contents.getKnowledgeLevel();
	}

}
