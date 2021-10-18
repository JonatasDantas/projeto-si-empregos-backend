package br.com.jowdev.projetosiplataformaempregos.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import br.com.jowdev.projetosiplataformaempregos.repository.KnowledgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jowdev.projetosiplataformaempregos.controller.dto.JobDetailsDto;
import br.com.jowdev.projetosiplataformaempregos.controller.dto.JobDto;
import br.com.jowdev.projetosiplataformaempregos.controller.form.JobForm;
import br.com.jowdev.projetosiplataformaempregos.models.Job;
import br.com.jowdev.projetosiplataformaempregos.models.Knowledge;
import br.com.jowdev.projetosiplataformaempregos.models.SalaryRange;
import br.com.jowdev.projetosiplataformaempregos.models.User;
import br.com.jowdev.projetosiplataformaempregos.repository.CompanyRepository;
import br.com.jowdev.projetosiplataformaempregos.repository.JobRepository;
import br.com.jowdev.projetosiplataformaempregos.repository.UserRepository;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "Bearer Token")
@RequestMapping("/jobs")
public class JobController {

	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private KnowledgeRepository knowledgeRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@PreAuthorize("hasAnyRole('RECRUITER', 'ADMIN')")
	@PostMapping
	public ResponseEntity<JobDetailsDto> create(@RequestBody @Valid JobForm form, UriComponentsBuilder uriBuilder) {
		Job job = form.convert(companyRepository, knowledgeRepository);
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
	public ResponseEntity<Page<JobDto>> findJobs(@PageableDefault(sort = "id", direction = Direction.ASC) @Parameter(hidden = true) Pageable page,
			@RequestParam(required = false, defaultValue = "") String knowledges,
			@RequestParam(required = false, defaultValue = "") String salary,
			@RequestParam(required = false, defaultValue = "") String title
			) {
		try {
			Page<Job> jobs = jobRepository.getJobsByFilter(knowledges, salary, title, page);
			
			return ResponseEntity.ok(jobs.map(JobDto::new));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.ok(new PageImpl<JobDto>(null));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@GetMapping("/knowledge")
	public List<Knowledge> findKnowledges() {
		
		return knowledgeRepository.findAll();
	}
	
	@GetMapping("/salaries")
	public Map<String, String> findSalaryRanges() {
		Map<String, String> salaries = new HashMap<String, String>();
		
		for(SalaryRange salary : SalaryRange.values()) {
			salaries.put(salary.name(), salary.toString());
		}
		
		return salaries;
	}
	
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@Transactional
	@PostMapping("/{id}/apply")
	public ResponseEntity<Boolean> applyJob(@PathVariable Long id, @AuthenticationPrincipal User authUser) {
		Optional<Job> optional = jobRepository.findById(id);
		
		if (optional.isPresent()) {
			Job job = optional.get();
			User user = userRepository.findById(authUser.getId()).get();
			List<User> candidates = job.getUsers();
			
			candidates.add(user);
			job.setUsers(candidates);

			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
	
}
