package br.com.jowdev.projetosiplataformaempregos.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jowdev.projetosiplataformaempregos.controller.dto.UserDto;
import br.com.jowdev.projetosiplataformaempregos.controller.form.SignupForm;
import br.com.jowdev.projetosiplataformaempregos.models.User;
import br.com.jowdev.projetosiplataformaempregos.repository.RoleRepository;
import br.com.jowdev.projetosiplataformaempregos.repository.UserRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody @Valid SignupForm form, UriComponentsBuilder uriBuilder) {
		if (userRepository.existsByEmail(form.getEmail())) {
			return ResponseEntity.badRequest().body("Já existe um usuário cadastrado com este e-mail");
		}
		
		if (userRepository.existsByCpf(form.getCpf())) {
			return ResponseEntity.badRequest().body("Já existe um usuário cadastrado com este CPF");
		}
		
		User user = form.convert(roleRepository);
		userRepository.save(user);
		
		URI uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(new UserDto(user));
	}
}
