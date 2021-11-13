package br.com.jowdev.projetosiplataformaempregos.repository;

import br.com.jowdev.projetosiplataformaempregos.models.Job.JobApplication;
import br.com.jowdev.projetosiplataformaempregos.models.Job.JobApplicationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, JobApplicationId> {

    public Optional<JobApplication> findByUserIdAndJobId(Long user_id, Long job_id);

}
