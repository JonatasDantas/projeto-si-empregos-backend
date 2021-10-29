package br.com.jowdev.projetosiplataformaempregos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableJpaAuditing
public class ProjetoSiPlataformaEmpregosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoSiPlataformaEmpregosApplication.class, args);
	}

}
