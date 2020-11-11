package br.com.jowdev.projetosiplataformaempregos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jowdev.projetosiplataformaempregos.models.Job;

public interface JobRepository extends JpaRepository<Job, Long>, JobRepositoryCustom {

}
