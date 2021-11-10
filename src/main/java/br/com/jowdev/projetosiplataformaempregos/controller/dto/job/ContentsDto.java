package br.com.jowdev.projetosiplataformaempregos.controller.dto.job;

import br.com.jowdev.projetosiplataformaempregos.models.ContentType;
import br.com.jowdev.projetosiplataformaempregos.models.Contents;


public class ContentsDto {
	private ContentType contentType;
	
	private String title;
	private String url;
	private String description;
	private String imageUrl;
	
	public ContentsDto(Contents contents) {
		this.title = contents.getTitle();
		this.url = contents.getUrl();
		this.contentType = contents.getContentType();
		this.description = contents.getDescription();
		this.imageUrl = contents.getImageUrl();
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
