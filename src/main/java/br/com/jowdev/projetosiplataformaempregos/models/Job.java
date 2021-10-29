package br.com.jowdev.projetosiplataformaempregos.models;

import br.com.jowdev.projetosiplataformaempregos.models.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String description;
	@Lob
	@Column(name="full_description", length=2147483647)
	private String fullDescription;
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
