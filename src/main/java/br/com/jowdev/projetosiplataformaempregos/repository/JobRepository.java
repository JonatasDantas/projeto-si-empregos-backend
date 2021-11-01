package br.com.jowdev.projetosiplataformaempregos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jowdev.projetosiplataformaempregos.models.Job.Job;

public interface JobRepository extends JpaRepository<Job, Long>, JobRepositoryCustom {

    Page<Job> findByCompanyUserId(Long company_user_id, Pageable pageable);

}
