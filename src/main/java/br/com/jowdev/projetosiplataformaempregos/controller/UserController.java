package br.com.jowdev.projetosiplataformaempregos.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jowdev.projetosiplataformaempregos.controller.dto.CompanyDto;
import br.com.jowdev.projetosiplataformaempregos.controller.dto.JobDto;
import br.com.jowdev.projetosiplataformaempregos.controller.dto.user.UserDetailsDto;
import br.com.jowdev.projetosiplataformaempregos.controller.dto.user.UserDto;
import br.com.jowdev.projetosiplataformaempregos.controller.form.CompanyForm;
import br.com.jowdev.projetosiplataformaempregos.controller.form.UserUpdateForm;
import br.com.jowdev.projetosiplataformaempregos.models.Company;
import br.com.jowdev.projetosiplataformaempregos.models.user.User;
import br.com.jowdev.projetosiplataformaempregos.repository.CompanyRepository;
import br.com.jowdev.projetosiplataformaempregos.repository.UserRepository;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "Bearer Token")
@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CompanyRepository companyRepository;


	@GetMapping("/me")
	public ResponseEntity<UserDetailsDto> getMyself(@AuthenticationPrincipal @Parameter(hidden = true) User authUser) {
		val optionalUser = userRepository.findById(authUser.getId());
		if(optionalUser.isPresent()) {
			return ResponseEntity.ok(new UserDetailsDto(optionalUser.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public Page<UserDto> findAll(@PageableDefault(sort = "id", direction = Direction.ASC) @Parameter(hidden = true) Pageable page) {
		Page<User> users = userRepository.findAll(page);
		return UserDto.convert(users);
	}

	@PreAuthorize("hasRole('ADMIN') or #authUser.getId() == #id")
	@GetMapping("/{id}")
	public ResponseEntity<UserDetailsDto> details(@PathVariable Long id, @AuthenticationPrincipal @Parameter(hidden = true) User authUser) {
		Optional<User> optional = userRepository.findById(id);

		if (optional.isPresent()) {
			return ResponseEntity.ok(new UserDetailsDto(optional.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@PreAuthorize("hasRole('ADMIN') or #authUser.getId() == #id")
	@PatchMapping("/{id}")
	@Transactional
	public ResponseEntity<UserDetailsDto> updateUser(@PathVariable Long id, @RequestBody @Valid UserUpdateForm form, 
			@AuthenticationPrincipal User authUser) {
		Optional<User> optional = userRepository.findById(id);
		
		if (optional.isPresent()) {
			User user = form.update(optional.get());
			return ResponseEntity.ok(new UserDetailsDto(user));
		}
		
		return ResponseEntity.notFound().build();
	}

	@PreAuthorize("hasAnyRole('RECRUITER', 'ADMIN') and #authUser.getId() == #userId")
	@PostMapping("/{userId}/companies")
	@Transactional
	public ResponseEntity<CompanyDto> createCompany(@PathVariable Long userId, @RequestBody @Valid CompanyForm form,
			UriComponentsBuilder uriBuilder, @AuthenticationPrincipal User authUser) {
		Company company = form.convert(userId, userRepository);
		companyRepository.save(company);
		
		URI uri = uriBuilder.path("/users/" + userId + "/companies/{id}").buildAndExpand(company.getId()).toUri();
		return ResponseEntity.created(uri).body(new CompanyDto(company));
	}
	
	@PreAuthorize("hasRole('ADMIN') or #authUser.getId() == #userId")
	@GetMapping("/{userId}/companies")
	public Page<CompanyDto> getUserCompanies(@PathVariable Long userId, @PageableDefault(sort = "id", direction = Direction.ASC) Pageable page,
			@AuthenticationPrincipal User authUser) {
		Page<Company> companies = companyRepository.findByUserId(page, userId);
		return CompanyDto.convert(companies);
	}
	
	@PreAuthorize("hasRole('ADMIN') or #authUser.getId() == #userId")
	@GetMapping("/{userId}/jobs")
	public List<JobDto> getJobApplications(@PathVariable Long userId, @PageableDefault(sort = "id", direction = Direction.ASC) Pageable page,
			@AuthenticationPrincipal User authUser) {
		Optional<User> user = userRepository.findById(userId);
		
		if (user.isPresent()) {
			return user.get().getJobApplications().stream().map(JobDto::new).collect(Collectors.toList());
		}
		
		return new ArrayList<>();
	}
}
