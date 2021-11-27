package br.com.jowdev.projetosiplataformaempregos.repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.jowdev.projetosiplataformaempregos.models.Job.Job;
import br.com.jowdev.projetosiplataformaempregos.models.SalaryRange;

public class JobRepositoryCustomImpl implements JobRepositoryCustom {

    @PersistenceContext
    private EntityManager em;
	
	@Override
	public Page<Job> getJobsByFilter(Long[] knowledges, String salary, String title, Pageable pageable) {

    	CriteriaBuilder builder = em.getCriteriaBuilder();
    	CriteriaQuery<Job> query = builder.createQuery(Job.class);
    	Root<Job> root = query.from(Job.class);
    	
    	Predicate conjunction = builder.conjunction();


    	if (!salary.isEmpty()) {
    		SalaryRange salaryEnum = SalaryRange.valueOf(salary);
    		
    		Predicate salaryEquals = builder.between(root.get("salary"), salaryEnum.minValue, salaryEnum.maxValue);
    		conjunction = builder.and(conjunction, salaryEquals);
    	}
    	
    	if (!title.isEmpty()) {
    		Predicate titleLike = builder.like(root.get("title"), "%" + title + "%");
    		conjunction = builder.and(conjunction, titleLike);
    	}
    	
    	TypedQuery<Job> typedQuery = em.createQuery(query.where(conjunction));
    	List<Job> jobs = typedQuery.getResultList();

		if (knowledges.length > 0) {
			jobs = jobs.stream().filter(j -> j.getKnowledges().stream().anyMatch(e -> Arrays.stream(knowledges).anyMatch(know -> know.equals(e.getId())))).collect(Collectors.toList());
		}
    	
    	int start = (int) pageable.getOffset();
    	int end = (start + pageable.getPageSize()) > jobs.size() ? jobs.size() : (start + pageable.getPageSize());
    	Page<Job> page = new PageImpl<Job>(jobs.subList(start, end), pageable, jobs.size());

		return page;
	}

}
