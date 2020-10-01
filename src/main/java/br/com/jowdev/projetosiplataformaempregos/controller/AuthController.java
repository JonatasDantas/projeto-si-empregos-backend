package br.com.jowdev.projetosiplataformaempregos.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jowdev.projetosiplataformaempregos.config.security.TokenService;
import br.com.jowdev.projetosiplataformaempregos.controller.dto.TokenDto;
import br.com.jowdev.projetosiplataformaempregos.controller.dto.UserDetailsDto;
import br.com.jowdev.projetosiplataformaempregos.controller.form.LoginForm;
import br.com.jowdev.projetosiplataformaempregos.controller.form.ResetPasswordForm;
import br.com.jowdev.projetosiplataformaempregos.controller.form.SignupForm;
import br.com.jowdev.projetosiplataformaempregos.models.PasswordResetToken;
import br.com.jowdev.projetosiplataformaempregos.models.User;
import br.com.jowdev.projetosiplataformaempregos.repository.PasswordResetTokenRepository;
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
	
	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;
	
    @Autowired
    private JavaMailSender emailSender;
    
	@Value("${spring.mail.username}")
	private String mailSender;
	
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
	
	@PostMapping("/reset-password")
	public ResponseEntity<?> resetPassword(@RequestParam("email") String email) {
		Optional<User> user = userRepository.findByEmail(email);
		
		if (user.isPresent()) {
			String token = UUID.randomUUID().toString();
			PasswordResetToken entity = new PasswordResetToken(token, user.get());
			
			passwordResetTokenRepository.save(entity);

			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(mailSender);
			message.setTo(email);
			message.setSubject("Troca de senha - Plataforma Empregos");
			
			String url = "http://localhost/reset-password?token=" + entity.getToken();

			message.setText("Olá " + user.get().getName() + ", \n\n" +
					"Você requisitou uma troca de senha. Clique no link abaixo para reseta-lá. \n" +
					url + " \n\n Obrigado.");
			
			emailSender.send(message);
			
			return ResponseEntity.ok("Email para troca de senha enviado!");
		}
		
		return ResponseEntity.notFound().build();	
	}
	
	@GetMapping("/reset-password/enabled")
	public ResponseEntity<?> canResetPassword(@RequestParam("token") String token) {
		Optional<PasswordResetToken> optional = passwordResetTokenRepository.findByToken(token);
		
		if (optional.isPresent()) {
			boolean isExpired = optional.get().getExpiryDate().isBefore(LocalDateTime.now());
			
			return ResponseEntity.ok(!isExpired);
		}
		
		return ResponseEntity.ok(false);
	}
	
	@PostMapping("/reset-password/handle")
	@Transactional
	public ResponseEntity<?> handleResetPassword(@RequestBody @Valid ResetPasswordForm form) {
		Optional<PasswordResetToken> token = passwordResetTokenRepository.findByToken(form.getToken());

		if (token.isPresent() && !(token.get().getExpiryDate().isBefore(LocalDateTime.now()))) {
			if (form.getPassword().contentEquals(form.getPasswordConfirmation())) {
				form.updateUser(token.get());
				
				return ResponseEntity.ok("Senha alterada com sucesso!");
			}

			return ResponseEntity.badRequest().body("Senha e Confirmação de senha não são iguais!");
		}

		return ResponseEntity.badRequest().body("Token inválido!");
	}
}
