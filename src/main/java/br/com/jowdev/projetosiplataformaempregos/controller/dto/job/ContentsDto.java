package br.com.jowdev.projetosiplataformaempregos.controller.dto.job;

import br.com.jowdev.projetosiplataformaempregos.models.ContentType;
import br.com.jowdev.projetosiplataformaempregos.models.Contents;


public class ContentsDto {
	private ContentType contentType;
	
	private String title;
	private String url;
	
	public ContentsDto(Contents contents) {
		this.title = contents.getTitle();
		this.url = contents.getUrl();
		this.contentType = contents.getContentType();
	}

	public ContentType getContentType() {
		return contentType;
	}

	public void setContentType(ContentType contentType) {
		this.contentType = contentType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
