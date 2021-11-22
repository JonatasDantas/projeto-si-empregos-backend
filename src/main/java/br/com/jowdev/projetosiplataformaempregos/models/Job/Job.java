package br.com.jowdev.projetosiplataformaempregos.models.Job;

import br.com.jowdev.projetosiplataformaempregos.models.BaseEntity;
import br.com.jowdev.projetosiplataformaempregos.models.Company;
import br.com.jowdev.projetosiplataformaempregos.models.Knowledge;
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
	@Column(name="description", length=2147483647)
	private String description;
	@Lob
	@Column(name="full_description", length=2147483647)
	private String fullDescription;
	private Float salary;

	@ManyToMany
	private List<Knowledge> knowledges;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Company company;
	
	@OneToMany(mappedBy = "job", cascade = CascadeType.REMOVE)
	private List<JobApplication> jobApplications = new ArrayList<>();

}
