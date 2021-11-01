package br.com.jowdev.projetosiplataformaempregos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.jowdev.projetosiplataformaempregos.models.Job.Job;

public interface JobRepositoryCustom {
	Page<Job> getJobsByFilter(String occupation, String salary, String title, Pageable pageable);
}
