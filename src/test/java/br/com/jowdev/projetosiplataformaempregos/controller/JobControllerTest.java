package br.com.jowdev.projetosiplataformaempregos.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.BeforeAll;
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
class JobControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private TokenDto userLoginDto;
	
	@BeforeEach
	void setUpEmpresa() throws URISyntaxException, Exception {
		
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
	}

	@Test
	void deveCadastrarNovoEmprego() throws URISyntaxException, Exception {
		mockMvc.perform(
				MockMvcRequestBuilders
					.post(new URI("/jobs/"))
					.content("{\r\n" + 
							"  \"title\": \"Domador de Onça Braba\",\r\n" + 
							"  \"description\": \"Doma as onça mais braba do sertão\",\r\n" + 
							"  \"salary\": 1000,\r\n" + 
							"  \"occupation\": \"Artes\",\r\n" + 
							"  \"companyId\": 1\r\n" + 
							"}")
					.contentType(MediaType.APPLICATION_JSON)
					.header("Authorization", "Bearer " + userLoginDto.getToken()))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.title", is(equalTo("Domador de Onça Braba"))))
		.andExpect(jsonPath("$.description", is(equalTo("Doma as onça mais braba do sertão"))));
	}

}
