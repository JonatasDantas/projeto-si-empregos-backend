package br.com.jowdev.projetosiplataformaempregos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jowdev.projetosiplataformaempregos.models.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
	Page<Company> findByUserId(Pageable page, Long id);
}
