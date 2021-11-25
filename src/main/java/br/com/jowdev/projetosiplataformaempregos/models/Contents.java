package br.com.jowdev.projetosiplataformaempregos.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import br.com.jowdev.projetosiplataformaempregos.models.Job.Job;
import br.com.jowdev.projetosiplataformaempregos.models.user.KnowledgeLevel;
import br.com.jowdev.projetosiplataformaempregos.models.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contents {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private ContentType contentType;
	
	private String title;
	private String url;
	private String imageUrl;
	private String description;
	private KnowledgeLevel knowledgeLevel;

	@ManyToOne(fetch = FetchType.LAZY)
	private Knowledge knowledge;
	
	public Contents(ContentType contentType, String title, String url, Knowledge knowledge, String description, String imageUrl, KnowledgeLevel knowledgeLevel) {
		super();
		this.contentType = contentType;
		this.title = title;
		this.url = url;
		this.knowledge = knowledge;
		this.description = description;
		this.imageUrl = imageUrl;
		this.knowledgeLevel = knowledgeLevel;
	}

}
