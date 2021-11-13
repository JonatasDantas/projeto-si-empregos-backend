package br.com.jowdev.projetosiplataformaempregos.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import br.com.jowdev.projetosiplataformaempregos.controller.dto.KnowledgeDetailsDto;
import br.com.jowdev.projetosiplataformaempregos.controller.dto.KnowledgeDto;
import br.com.jowdev.projetosiplataformaempregos.controller.dto.job.ContentsDto;
import br.com.jowdev.projetosiplataformaempregos.controller.dto.job.JobApplicationDto;
import br.com.jowdev.projetosiplataformaempregos.controller.dto.job.JobRecruiterDetailsDto;
import br.com.jowdev.projetosiplataformaempregos.helper.UserHelper;
import br.com.jowdev.projetosiplataformaempregos.models.Job.JobApplication;
import br.com.jowdev.projetosiplataformaempregos.models.Job.JobApplicationId;
import br.com.jowdev.projetosiplataformaempregos.models.user.User;
import br.com.jowdev.projetosiplataformaempregos.repository.*;
import lombok.val;
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
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jowdev.projetosiplataformaempregos.controller.dto.job.JobDetailsDto;
import br.com.jowdev.projetosiplataformaempregos.controller.dto.job.JobDto;
import br.com.jowdev.projetosiplataformaempregos.controller.form.JobForm;
import br.com.jowdev.projetosiplataformaempregos.models.Job.Job;
import br.com.jowdev.projetosiplataformaempregos.models.Contents;
import br.com.jowdev.projetosiplataformaempregos.models.Knowledge;
import br.com.jowdev.projetosiplataformaempregos.models.Occupation;
import br.com.jowdev.projetosiplataformaempregos.models.SalaryRange;
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

	@Autowired
	private JobApplicationRepository jobApplicationRepository;

	@Autowired
	private UserHelper userHelper;

	@PreAuthorize("hasAnyRole('RECRUITER', 'ADMIN')")
	@PostMapping
	public ResponseEntity<JobDetailsDto> create(@RequestBody @Valid JobForm form, UriComponentsBuilder uriBuilder) {
		Job job = form.convert(companyRepository, knowledgeRepository);
		jobRepository.save(job);

		URI uri = uriBuilder.path("/jobs/{id}").buildAndExpand(job.getId()).toUri();
		return ResponseEntity.created(uri).body(new JobDetailsDto(job));
	}

	@GetMapping("/{id}")
	public ResponseEntity<JobDetailsDto> details(@PathVariable Long id) {
		Optional<Job> optional = jobRepository.findById(id);

		if (optional.isPresent()) {
			return ResponseEntity.ok(new JobDetailsDto(optional.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@GetMapping
	public ResponseEntity<Page<JobDto>> findJobs(
			@PageableDefault(sort = "id", direction = Direction.ASC) @Parameter(hidden = true) Pageable page,
			@RequestParam(required = false, defaultValue = "") String knowledges,
			@RequestParam(required = false, defaultValue = "") String salary,
			@RequestParam(required = false, defaultValue = "") String title) {
		try {
			Page<Job> jobs = jobRepository.getJobsByFilter(knowledges, salary, title, page);

			return ResponseEntity.ok(jobs.map(JobDto::new));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.ok(new PageImpl<JobDto>(null));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@GetMapping("/occupations")
	public Map<String, String> findOccupations() {
		Map<String, String> occupations = new HashMap<String, String>();

		for (Occupation occupation : Occupation.values()) {
			occupations.put(occupation.name(), occupation.toString());
		}

		return occupations;
	}

	@PreAuthorize("hasAnyRole('RECRUITER', 'ADMIN')")
	@GetMapping("/created")
	public ResponseEntity<Page<JobRecruiterDetailsDto>> getMyCreatedJobs(
			@PageableDefault(sort = "id", direction = Direction.ASC) @Parameter(hidden = true) Pageable page,
			@AuthenticationPrincipal User user) {
		return ResponseEntity
				.ok(jobRepository.findByCompanyUserId(user.getId(), page).map(JobRecruiterDetailsDto::new));
	}

	@GetMapping("/knowledge")
	public List<KnowledgeDto> findKnowledges() {

		return knowledgeRepository.findAll().stream().map(KnowledgeDto::new).collect(Collectors.toList());
	}

	@GetMapping("/knowledge/{id}")
	public ResponseEntity<KnowledgeDetailsDto> findKnowledge(@PathVariable("id") Long id) {

		val knowledge = knowledgeRepository.findById(id);

		return knowledge
				.map(value -> ResponseEntity.ok(new KnowledgeDetailsDto(value)))
				.orElseGet(() -> ResponseEntity.notFound().build());

	}

	@GetMapping("/salaries")
	public Map<String, String> findSalaryRanges() {
		Map<String, String> salaries = new HashMap<String, String>();

		for (SalaryRange salary : SalaryRange.values()) {
			salaries.put(salary.name(), salary.toString());
		}

		return salaries;
	}

	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@PostMapping("/{id}/apply")
	public ResponseEntity<JobApplicationDto> applyJob(@PathVariable Long id, @AuthenticationPrincipal User authUser) {
		Optional<Job> optional = jobRepository.findById(id);

		if (optional.isPresent()) {
			Job job = optional.get();
			User user = userRepository.findById(authUser.getId()).get();

			//Checar se ja existe
			final val jobApplicationFound = jobApplicationRepository.findByUserIdAndJobId(authUser.getId(), job.getId());
			if(jobApplicationFound.isPresent()) {
				jobApplicationRepository.delete(jobApplicationFound.get());
				return ResponseEntity.ok().build();
			}


			val jobApplication = JobApplication.builder().job(job).approved(null).user(user).build();

			final val application = jobApplicationRepository.save(jobApplication);

			return ResponseEntity.created(null).body(new JobApplicationDto(application));
		}

		return ResponseEntity.notFound().build();
	}

	@GetMapping("/knowledge/{id}/contents")
	public ResponseEntity<List<ContentsDto>> findContentsByKnowledge(@PathVariable Long id) {
		Optional<Knowledge> optional = knowledgeRepository.findById(id);

		if (optional.isPresent()) {
			Knowledge knowledge = optional.get();

			List<ContentsDto> contentsDto = knowledge.getContents().stream().map(ContentsDto::new)
					.collect(Collectors.toList());

			return ResponseEntity.ok(contentsDto);
		}

		return ResponseEntity.notFound().build();
	}

}
