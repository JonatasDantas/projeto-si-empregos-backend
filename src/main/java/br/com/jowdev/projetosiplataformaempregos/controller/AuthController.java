package br.com.jowdev.projetosiplataformaempregos.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jowdev.projetosiplataformaempregos.config.security.TokenService;
import br.com.jowdev.projetosiplataformaempregos.controller.dto.TokenDto;
import br.com.jowdev.projetosiplataformaempregos.controller.dto.UserDetailsDto;
import br.com.jowdev.projetosiplataformaempregos.controller.form.LoginForm;
import br.com.jowdev.projetosiplataformaempregos.controller.form.SignupForm;
import br.com.jowdev.projetosiplataformaempregos.models.User;
import br.com.jowdev.projetosiplataformaempregos.repository.RoleRepository;
import br.com.jowdev.projetosiplataformaempregos.repository.UserRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@PostMapping("/login")
	public ResponseEntity<TokenDto> login(@RequestBody @Valid LoginForm form) {
		System.out.println(form.getEmail());
		UsernamePasswordAuthenticationToken loginData = new UsernamePasswordAuthenticationToken(form.getEmail(), form.getPassword());

		try {
			Authentication authentication = authManager.authenticate(loginData);
			String token = tokenService.generateToken(authentication);
			User user = userRepository.getOne(tokenService.getUserId(token));
			return ResponseEntity.ok(new TokenDto(token, "Bearer ", user));
		} catch (Exception e) {
			System.out.println(e + "exception");
			return ResponseEntity.badRequest().build();
		}
	}

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
		return ResponseEntity.created(uri).body(new UserDetailsDto(user));
	}
}
