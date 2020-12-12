package br.com.jowdev.projetosiplataformaempregos.repository;

import java.util.List;

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

import br.com.jowdev.projetosiplataformaempregos.models.Job;
import br.com.jowdev.projetosiplataformaempregos.models.Occupation;
import br.com.jowdev.projetosiplataformaempregos.models.SalaryRange;

public class JobRepositoryCustomImpl implements JobRepositoryCustom {

    @PersistenceContext
    private EntityManager em;
	
	@Override
	public Page<Job> getJobsByFilter(String occupation, String salary, String title, Pageable pageable) {
		
		/**
		 * Código antigo - concatenando strings
		boolean hasWhere = false;
		String queryString = "SELECT j from Job j where";
		
		if (!occupation.isEmpty()) {
			hasWhere = true;
			queryString += "j.occupation = :occupation";
		}
		
		if (!salary.isEmpty()) {
			hasWhere = true;			
			queryString += "j.salary Between :salaryEnum.minValue and :salaryEnum.maxValue";
		}
		
		if (!title.isEmpty()) {
			hasWhere = true;
			queryString += "j.salary like %:title%";
		}
		
		if (!hasWhere) {
			queryString += "true";
		}
		
		TypedQuery<Job> typedQuery = em.createQuery(queryString);
		**/
		
		// Código pós-refatoração - usando Criteria
    	CriteriaBuilder builder = em.getCriteriaBuilder();
    	CriteriaQuery<Job> query = builder.createQuery(Job.class);
    	Root<Job> root = query.from(Job.class);
    	
    	Predicate conjunction = builder.conjunction();
    	
    	if (!occupation.isEmpty()) {
    		Occupation occupationEnum = Occupation.valueOf(occupation);
    		
    		Predicate occupationEquals = builder.equal(root.get("occupation"), occupationEnum);
    		conjunction = builder.and(occupationEquals);
    	}

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
    	
    	int start = (int) pageable.getOffset();
    	int end = (start + pageable.getPageSize()) > jobs.size() ? jobs.size() : (start + pageable.getPageSize());
    	Page<Job> page = new PageImpl<Job>(jobs.subList(start, end), pageable, jobs.size());

		return page;
	}

}
