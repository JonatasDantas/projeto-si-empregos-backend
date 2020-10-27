package br.com.jowdev.projetosiplataformaempregos.controller;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jowdev.projetosiplataformaempregos.controller.dto.UserDetailsDto;
import br.com.jowdev.projetosiplataformaempregos.controller.dto.UserDto;
import br.com.jowdev.projetosiplataformaempregos.controller.form.UserUpdateForm;
import br.com.jowdev.projetosiplataformaempregos.models.User;
import br.com.jowdev.projetosiplataformaempregos.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping
	public Page<UserDto> findAll(@PageableDefault(sort = "id", direction = Direction.ASC) Pageable page) {
		Page<User> users = userRepository.findAll(page);
		return UserDto.convert(users);
	}

	@PreAuthorize("hasRole('ADMIN') or #authUser.getId() == #id")
	@GetMapping("/{id}")
	public ResponseEntity<UserDetailsDto> details(@PathVariable Long id, @AuthenticationPrincipal User authUser) {
		Optional<User> optional = userRepository.findById(id);

		if (optional.isPresent()) {
			return ResponseEntity.ok(new UserDetailsDto(optional.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@PreAuthorize("hasRole('ADMIN') or #authUser.getId() == #id")
	@PutMapping("/{id}")
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
}
