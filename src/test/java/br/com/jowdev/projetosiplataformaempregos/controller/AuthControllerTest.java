package br.com.jowdev.projetosiplataformaempregos.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	void DeveFazerLogin() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders
					.post(new URI("/auth/login"))
					.content("{\r\n" + 
							"	\"email\": \"admin@admin.com\",\r\n" + 
							"	\"password\": \"123456\"\r\n" + 
							"}")
					.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.user", is(notNullValue())))
				.andExpect(jsonPath("$.user.name", is(equalTo("Ademir"))))
				.andExpect(jsonPath("$.user.email", is(equalTo("admin@admin.com"))));
	}
	
	@Test
	void DeveTomarBadCredentialsLogin() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders
				.post(new URI("/auth/login"))
				.content("{\r\n" + 
						"	\"email\": \"admin@admin.com\",\r\n" + 
						"	\"password\": \"naosei\"\r\n" + 
						"}")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isForbidden());
	}
	
	@Test
	void DeveTomarBadRequestNoJsonInválido() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders
				.post(new URI("/auth/login"))
				.content("{\r\n" + 
						"	\"email\": \"admin@admin.com\",\r\n" + 
						"	\"password: \"naosei\"\r\n" + 
						"}")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	void DeveRegistrarUsuarioNovo() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders
					.post(new URI("/auth/signup"))
					.content("{\r\n" + 
							"  \"firstName\": \"Testeiro\",\r\n" + 
							"  \"lastName\": \"Testador\",\r\n" + 
							"  \"email\": \"teste@teste.com\",\r\n" + 
							"  \"password\": \"123456\",\r\n" + 
							"  \"gender\": \"Masculino\",\r\n" + 
							"  \"cpf\": \"11111111111\",\r\n" + 
							"  \"phone\": \"11987654321\",\r\n" + 
							"  \"recruiter\": true\r\n" + 
							"}")
					.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.name", is(equalTo("Testeiro Testador"))))
				.andExpect(jsonPath("$.cpf", is(equalTo("11111111111"))))
				.andExpect(jsonPath("$.phone", is(equalTo("11987654321"))));
	}
	
	@Test
	void DeveTomarEmailRepetido() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders
					.post(new URI("/auth/signup"))
					.content("{\r\n" + 
							"  \"firstName\": \"Testeiro\",\r\n" + 
							"  \"lastName\": \"Testador\",\r\n" + 
							"  \"email\": \"admin@admin.com\",\r\n" + 
							"  \"password\": \"123456\",\r\n" + 
							"  \"gender\": \"Masculino\",\r\n" + 
							"  \"cpf\": \"11111111111\",\r\n" + 
							"  \"phone\": \"11987654321\",\r\n" + 
							"  \"recruiter\": true\r\n" + 
							"}")
					.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isConflict())
				.andExpect(content().string("Já existe um usuário cadastrado com este e-mail"));
	}
	
	@Test
	void DeveTomarCpfRepetido() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders
					.post(new URI("/auth/signup"))
					.content("{\r\n" + 
							"  \"firstName\": \"Testeiro\",\r\n" + 
							"  \"lastName\": \"Testador\",\r\n" + 
							"  \"email\": \"teste@testador.com\",\r\n" + 
							"  \"password\": \"123456\",\r\n" + 
							"  \"gender\": \"Masculino\",\r\n" + 
							"  \"cpf\": \"00000000191\",\r\n" + 
							"  \"phone\": \"11987654321\",\r\n" + 
							"  \"recruiter\": true\r\n" + 
							"}")
					.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isConflict())
				.andExpect(content().string("Já existe um usuário cadastrado com este CPF"));
	}
	
	@Test
	void DeveResetarSenhaComEmailValido() throws URISyntaxException, Exception {
		mockMvc.perform(
				MockMvcRequestBuilders
					.post(new URI("/auth/reset-password"))
					.queryParam("email", "admin@admin.com"))
				.andExpect(status().isOk())
				.andExpect(content().string("Email para troca de senha enviado!"));
	}
	
	@Test
	void DeveTomar404ComEmailInvalido() throws URISyntaxException, Exception {
		mockMvc.perform(
				MockMvcRequestBuilders
					.post(new URI("/auth/reset-password"))
					.queryParam("email", "admina@admina.com"))
				.andExpect(status().isNotFound());
	}
	
		

}
