package br.com.jowdev.projetosiplataformaempregos.models;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;

@Entity
public @Data class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column
	private String title;
	
	@Column
	private String description;
	
	@Column
	private User author;
	
	@Column
	@ManyToMany(mappedBy = "jobs")
	private List<Qualification> qualifications; 
	
	@Column
	private BigDecimal location_x;
	
	@Column
	private BigDecimal location_y;
	
}
