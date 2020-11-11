package br.com.jowdev.projetosiplataformaempregos.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jowdev.projetosiplataformaempregos.controller.dto.JobDetailsDto;
import br.com.jowdev.projetosiplataformaempregos.controller.dto.JobDto;
import br.com.jowdev.projetosiplataformaempregos.controller.form.JobForm;
import br.com.jowdev.projetosiplataformaempregos.models.Job;
import br.com.jowdev.projetosiplataformaempregos.models.Occupation;
import br.com.jowdev.projetosiplataformaempregos.models.SalaryRange;
import br.com.jowdev.projetosiplataformaempregos.models.User;
import br.com.jowdev.projetosiplataformaempregos.repository.CompanyRepository;
import br.com.jowdev.projetosiplataformaempregos.repository.JobRepository;

@RestController
@RequestMapping("/jobs")
public class JobController {

	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@PreAuthorize("hasAnyRole('RECRUITER', 'ADMIN')")
	@PostMapping
	public ResponseEntity<JobDetailsDto> create(@RequestBody @Valid JobForm form, UriComponentsBuilder uriBuilder) {
		Job job = form.convert(companyRepository);
		jobRepository.save(job);

		URI uri = uriBuilder.path("/jobs/{id}").buildAndExpand(job.getId()).toUri();
		return ResponseEntity.created(uri).body(new JobDetailsDto(job));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<JobDetailsDto> details(@PathVariable Long id, @AuthenticationPrincipal User authUser) {
		Optional<Job> optional = jobRepository.findById(id);
		
		if (optional.isPresent()) {
			return ResponseEntity.ok(new JobDetailsDto(optional.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping
	public Page<JobDto> findJobs(@PageableDefault(sort = "id", direction = Direction.ASC) Pageable page,
			@RequestParam(required = false, defaultValue = "") String occupation,
			@RequestParam(required = false, defaultValue = "") String salary) {
		try {
			Page<Job> jobs = jobRepository.getJobsByFilter(occupation, salary, page);
			
			return jobs.map(JobDto::new);	
		} catch (IllegalArgumentException e) {
			return new PageImpl<JobDto>(null);
		}
	}
	
	@GetMapping("/occupations")
	public Map<String, String> findOccupations() {
		Map<String, String> occupations = new HashMap<String, String>();
		
		for(Occupation occupation : Occupation.values()) {
			occupations.put(occupation.name(), occupation.toString());
		}
		
		return occupations;
	}
	
	@GetMapping("/salaries")
	public Map<String, String> findSalaryRanges() {
		Map<String, String> salaries = new HashMap<String, String>();
		
		for(SalaryRange salary : SalaryRange.values()) {
			salaries.put(salary.name(), salary.toString());
		}
		
		return salaries;
	}
}
