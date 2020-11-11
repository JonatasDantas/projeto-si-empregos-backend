package br.com.jowdev.projetosiplataformaempregos.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.jowdev.projetosiplataformaempregos.models.User;
import br.com.jowdev.projetosiplataformaempregos.models.UserGender;
import br.com.jowdev.projetosiplataformaempregos.repository.RoleRepository;
import br.com.jowdev.projetosiplataformaempregos.repository.UserRepository;

@Component
public class StartupUtilsDev{
	
	Logger LOGGER = LoggerFactory.getLogger(StartupUtilsDev.class);

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;	

	@Value("${first.user.email}") 
	String email;
	
	@Value("${first.user.password}") 
	String password;
	
	@EventListener(ContextRefreshedEvent.class)
	public void feedAdmin() {
		
		User user = new User();
		user.setCpf("00000000191");
		user.setEmail(email);
		user.setGender(UserGender.Masculino);
		user.setName("Ademir");
		user.setPassword(new BCryptPasswordEncoder().encode(password));
		user.setPhone("11987641234");
		
//		Role roleUser = new Role();
//		roleUser.setName("ROLE_USER");
//		roleUser.setId(1L);
//		
//		Role roleRecruiter = new Role();
//		roleRecruiter.setName("ROLE_RECRUITER");
//		roleRecruiter.setId(2L);
//		
//		user.setRoles(Arrays.asList(roleUser, roleRecruiter));
		
		userRepository.save(user);
		LOGGER.info("Salvando usuário admin@admin.com, senha 123456");
	}

}
