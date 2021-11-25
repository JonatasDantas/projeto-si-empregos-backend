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
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String cnpj;
	private String name;

	private String logoUrl;

	//Endereco
	private String street;
	private Integer number;
	private String complement;
	private String cep;
	private String city;
	private String state;


	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	@OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
	private List<Job> jobs = new ArrayList<>();

	public Company(String cnpj, String name, String street, Integer number, String complement, String cep,
			String city, String state, User user, String logoUrl) {
		super();
		this.cnpj = cnpj;
		this.name = name;
		this.street = street;
		this.number = number;
		this.complement = complement;
		this.cep = cep;
		this.city = city;
		this.state = state;
		this.user = user;
		this.logoUrl = logoUrl;
	}
}
