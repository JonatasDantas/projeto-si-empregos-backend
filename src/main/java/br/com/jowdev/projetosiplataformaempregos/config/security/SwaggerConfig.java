package br.com.jowdev.projetosiplataformaempregos.config.security;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;

@Profile("dev")
@Configuration
public class SwaggerConfig {
	
	
	@Bean 
	public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
	
		String endpointDocs = UriComponentsBuilder.newInstance().path("/v3/api-docs").build().toUri().toString();
		
		return new OpenAPI()
		        .components(new Components().addSecuritySchemes("Bearer Token",
						new SecurityScheme()
						.type(SecurityScheme.Type.HTTP)
						.in(In.HEADER)
						.scheme("bearer")
						.name("Authorization")
						.description("Use a URL <strong>/auth/login</strong> para obter um Token e ponha-o aqui (não necessita do Bearer)")
                        .bearerFormat("JWT")))
		        .info(new Info()
		        .title("Projeto Emprego API")
		        .version(appVersion)
		        .description("Interface interativa da API do Projeto Empregos, para obter o arquivo open API visite a url [/v3/api-docs/]("+ endpointDocs +")<br/>Para usar os endpoints protegidos é necessário realizar a autenticação no /auth/login e copiar o Token para o campo value dentro do Authorize")
		        .termsOfService("http://swagger.io/terms/")
		        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
		}
	
}
