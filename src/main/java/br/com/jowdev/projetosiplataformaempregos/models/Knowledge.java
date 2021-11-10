package br.com.jowdev.projetosiplataformaempregos.models;

import br.com.jowdev.projetosiplataformaempregos.models.Job.Job;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Knowledge {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String description;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<Job> jobs;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<Contents> contents;
}