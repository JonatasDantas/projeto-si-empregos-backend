package br.com.jowdev.projetosiplataformaempregos.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.jowdev.projetosiplataformaempregos.controller.dto.TokenDto;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private TokenDto userLoginDto;
	
	@BeforeEach
	void setUp() throws URISyntaxException, Exception  {
		
		ResultActions resultActions = mockMvc.perform(
				MockMvcRequestBuilders
					.post(new URI("/auth/login"))
					.content("{\r\n" + 
							"	\"email\": \"admin@admin.com\",\r\n" + 
							"	\"password\": \"123456\"\r\n" + 
							"}")
					.contentType(MediaType.APPLICATION_JSON));
		
		MvcResult result = resultActions.andReturn();
		String contentAsString = result.getResponse().getContentAsString();

		userLoginDto = objectMapper.readValue(contentAsString, TokenDto.class);		
		
	}

	@Test
	void asInformacoesDevemBaterComOUsuarioLogado() throws URISyntaxException, Exception {
		mockMvc.perform(
				MockMvcRequestBuilders
					.get(new URI("/users/" + userLoginDto.getUser().getId()))
					.header("Authorization", "Bearer " + userLoginDto.getToken())
				)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id", is(equalTo(userLoginDto.getUser().getId().intValue()))))
		.andExpect(jsonPath("$.name", is(equalTo(userLoginDto.getUser().getName()))))
		.andExpect(jsonPath("$.email", is(equalTo(userLoginDto.getUser().getEmail()))))
		.andExpect(jsonPath("$.phone", is(equalTo(userLoginDto.getUser().getPhone()))))
		.andExpect(jsonPath("$.cpf", is(equalTo(userLoginDto.getUser().getCpf()))));
					
	}
	
	@Test
	void deveListarTodosOsUsuarios() throws URISyntaxException, Exception {
		mockMvc.perform(
				MockMvcRequestBuilders
				.get(new URI("/users"))
				.header("Authorization", "Bearer " + userLoginDto.getToken())
				)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.content[:1]").exists())
		.andExpect(jsonPath("$.content[:1].name").exists())
		.andExpect(jsonPath("$.content[:1].email").exists())
		.andExpect(jsonPath("$.content[:1].phone").exists())		
		.andExpect(jsonPath("$.content[:1].cpf").exists());		
	}
	
	@Test
	void deveRetornar404EmIdAleatorio() throws URISyntaxException, Exception {
		mockMvc.perform(
				MockMvcRequestBuilders
				.get(new URI("/users/" + 420))
				.header("Authorization", "Bearer " + userLoginDto.getToken())
				)
		.andExpect(status().isNotFound());		
	}
	
	@Test
	void deveAtualizarInformacoesDoUsuario() throws URISyntaxException, Exception {
		
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
					.contentType(MediaType.APPLICATION_JSON));
		
		mockMvc.perform(
				MockMvcRequestBuilders
					.put(new URI("/users/2"))
					.content("{\r\n" + 
							"  \"firstName\": \"Springuete\",\r\n" + 
							"  \"lastName\": \"Springueiro\",\r\n" + 
							"  \"email\": \"admin@spring.com\",\r\n" + 
							"  \"gender\": \"Feminino\",\r\n" + 
							"  \"cpf\": \"22222222222\",\r\n" + 
							"  \"phone\": \"11940028922\"\r\n" + 
							"}")
					.contentType(MediaType.APPLICATION_JSON)
					.header("Authorization", "Bearer " + userLoginDto.getToken()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is(equalTo("Springuete Springueiro"))))
				.andExpect(jsonPath("$.email", is(equalTo("admin@spring.com"))))
				.andExpect(jsonPath("$.cpf", is(equalTo("22222222222"))))
				.andExpect(jsonPath("$.phone", is(equalTo("11940028922"))));
	}
	
	@Test
	void deveAdicionarCompanhia() throws URISyntaxException, Exception {
		mockMvc.perform(
				MockMvcRequestBuilders
					.post(new URI("/users/" + userLoginDto.getUser().getId() + "/companies"))
					.content("{\r\n" + 
							"  \"cnpj\": \"00000000000191\",\r\n" + 
							"  \"name\": \"Banco do Brasil\",\r\n" + 
							"  \"street\": \"Av. Campanella\",\r\n" + 
							"  \"number\": 118,\r\n" + 
							"  \"complement\": \"perto do Itau\",\r\n" + 
							"  \"cep\": \"08220830\",\r\n" + 
							"  \"city\": \"São Paulo\",\r\n" + 
							"  \"state\": \"SP\"\r\n" + 
							"}")
					.contentType(MediaType.APPLICATION_JSON)
					.header("Authorization", "Bearer " + userLoginDto.getToken()))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.complement", is(equalTo("perto do Itau"))))
				.andExpect(jsonPath("$.id").isNumber())
				.andExpect(jsonPath("$.name", is(equalTo("Banco do Brasil"))))
				.andExpect(jsonPath("$.street", is(equalTo("Av. Campanella"))))
				.andExpect(jsonPath("$.city", is(equalTo("São Paulo"))));
	}
	
	@Test
	void deveListarCompanhias() throws URISyntaxException, Exception {
		mockMvc.perform(
				MockMvcRequestBuilders
					.post(new URI("/users/" + userLoginDto.getUser().getId() + "/companies"))
					.content("{\r\n" + 
							"  \"cnpj\": \"00000000000191\",\r\n" + 
							"  \"name\": \"Banco do Brasil\",\r\n" + 
							"  \"street\": \"Av. Campanella\",\r\n" + 
							"  \"number\": 118,\r\n" + 
							"  \"complement\": \"perto do Itau\",\r\n" + 
							"  \"cep\": \"08220830\",\r\n" + 
							"  \"city\": \"São Paulo\",\r\n" + 
							"  \"state\": \"SP\"\r\n" + 
							"}")
					.contentType(MediaType.APPLICATION_JSON)
					.header("Authorization", "Bearer " + userLoginDto.getToken()));
		
		mockMvc.perform(
				MockMvcRequestBuilders
					.get(new URI("/users/" + userLoginDto.getUser().getId() + "/companies"))
					.contentType(MediaType.APPLICATION_JSON)
					.header("Authorization", "Bearer " + userLoginDto.getToken()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.content[:1].complement").exists())
				.andExpect(jsonPath("$.content[:1].id").exists())
				.andExpect(jsonPath("$.content[:1].name").exists())
				.andExpect(jsonPath("$.content[:1].street").exists())
				.andExpect(jsonPath("$.content[:1].city").exists());
	}

	
}
