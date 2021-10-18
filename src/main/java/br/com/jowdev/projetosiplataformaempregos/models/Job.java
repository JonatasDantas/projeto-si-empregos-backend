package br.com.jowdev.projetosiplataformaempregos.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String description;
	private Float salary;

	@ManyToMany
	private List<Knowledge> knowledges;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Company company;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinTable(name = "job_users", 
	joinColumns = {
			@JoinColumn(name = "job_id", referencedColumnName = "id", nullable = false, updatable = false)},
	inverseJoinColumns = {
            @JoinColumn(name = "users_id", referencedColumnName = "id", nullable = false, updatable = false)}
	)
	private List<User> users = new ArrayList<>();

}
