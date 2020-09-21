package br.com.jowdev.projetosiplataformaempregos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jowdev.projetosiplataformaempregos.models.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
