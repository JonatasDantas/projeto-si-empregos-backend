package br.com.jowdev.projetosiplataformaempregos.models;

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
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String description;
	private Float salary;
	private Occupation occupation;
	
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

	public Job() {
		super();
	}

	public Job(String title, String description, Float salary, Occupation occupation, Company company) {
		super();
		this.title = title;
		this.description = description;
		this.salary = salary;
		this.occupation = occupation;
		this.company = company;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getSalary() {
		return salary;
	}

	public void setSalary(Float salary) {
		this.salary = salary;
	}

	public Occupation getOccupation() {
		return occupation;
	}

	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<User> getCandidates() {
		return users;
	}

	public void setCandidates(List<User> candidates) {
		this.users = candidates;
	}
}
