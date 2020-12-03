package br.com.jowdev.projetosiplataformaempregos.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;

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

}
