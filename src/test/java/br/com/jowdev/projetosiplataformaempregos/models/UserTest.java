package br.com.jowdev.projetosiplataformaempregos.models;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import br.com.jowdev.projetosiplataformaempregos.models.user.User;
import br.com.jowdev.projetosiplataformaempregos.models.user.UserGender;
import org.junit.jupiter.api.Test;

import lombok.val;

class UserTest {

	@Test
	public void deveSetarPropriedadesViaCOnstrutorCorretamente() {
		List<Role> roles = new ArrayList<>();
		List<Company> companies = new ArrayList<>();
		
		val user = new User(
					"nome",
					"admin@admin.com",
					"123456",
					UserGender.Masculino,
					"12345678900",
					"1140028922",
					true,
					roles,
					companies,
				""
				);
		
		assertThat(user.getName()).isEqualTo("nome");
		assertThat(user.getEmail()).isEqualTo("admin@admin.com");
		assertThat(user.getPassword()).isEqualTo("123456");
		assertThat(user.getGender()).isEqualTo(UserGender.Masculino);
		assertThat(user.getCpf()).isEqualTo("12345678900");
		assertThat(user.getPhone()).isEqualTo("1140028922");
		assertThat(user.isEmailVerified()).isEqualTo(true);
		assertThat(user.getRoles()).isEqualTo(roles);
		assertThat(user.getCompanies()).isEqualTo(companies);
		
	}
	
	@Test
	public void deveSetarOsCamposCorretamente() {
		List<Role> roles = new ArrayList<>();
		List<Company> companies = new ArrayList<>();
		
		val user = new User();
		
		user.setName("nome");
		user.setEmail("admin@admin.com");
		user.setPassword("123456");
		user.setGender(UserGender.Masculino);
		user.setCpf("12345678900");
		user.setPhone("1140028922");
		user.setEmailVerified(true);
		user.setRoles(roles);
		user.setCompanies(companies);	
		user.setId(123L);
		
		assertThat(user.getName()).isEqualTo("nome");
		assertThat(user.getEmail()).isEqualTo("admin@admin.com");
		assertThat(user.getPassword()).isEqualTo("123456");
		assertThat(user.getGender()).isEqualTo(UserGender.Masculino);
		assertThat(user.getCpf()).isEqualTo("12345678900");
		assertThat(user.getPhone()).isEqualTo("1140028922");
		assertThat(user.isEmailVerified()).isEqualTo(true);
		assertThat(user.getRoles()).isEqualTo(roles);
		assertThat(user.getCompanies()).isEqualTo(companies);
		assertThat(user.getId()).isEqualTo(123L);
		assertThat(user.getUsername()).isEqualTo("admin@admin.com");
		assertThat(user.isAccountNonExpired()).isEqualTo(true);
		assertThat(user.isAccountNonLocked()).isEqualTo(true);
		assertThat(user.isCredentialsNonExpired()).isEqualTo(true);
		assertThat(user.isEnabled()).isEqualTo(true);
		
	}

}
