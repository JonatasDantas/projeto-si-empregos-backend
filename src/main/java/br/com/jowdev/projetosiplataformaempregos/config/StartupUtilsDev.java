package br.com.jowdev.projetosiplataformaempregos.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.jowdev.projetosiplataformaempregos.models.Role;
import br.com.jowdev.projetosiplataformaempregos.models.User;
import br.com.jowdev.projetosiplataformaempregos.models.UserGender;
import br.com.jowdev.projetosiplataformaempregos.repository.RoleRepository;
import br.com.jowdev.projetosiplataformaempregos.repository.UserRepository;

@Component
public class StartupUtilsDev{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@EventListener(ContextRefreshedEvent.class)
	public void feedAdmin() {
		User user = new User();
		user.setCpf("00000000191");
		user.setEmail("admin@admin.com");
		user.setGender(UserGender.Masculino);
		user.setName("Ademir");
		user.setPassword(new BCryptPasswordEncoder().encode("123456"));
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
		System.out.println("Salvando usuário admin@admin.com");
	}

}
