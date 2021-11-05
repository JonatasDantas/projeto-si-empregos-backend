package br.com.jowdev.projetosiplataformaempregos.models;

import br.com.jowdev.projetosiplataformaempregos.models.Job.Job;
import br.com.jowdev.projetosiplataformaempregos.models.user.User;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Experience {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String office;
	private String companyName;

	private String initialDate;
	private String endDate;



	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	

	public Experience(String office, String companyName, String initialDate, String endDate, User user) {
		super();
		this.office = office;
		this.companyName = companyName;
		this.initialDate = initialDate;
		this.endDate = endDate;
	}
}
