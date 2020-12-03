package br.com.jowdev.projetosiplataformaempregos.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartupUtilsDev{

	Logger LOGGER = LoggerFactory.getLogger(StartupUtilsDev.class);


	@EventListener(ContextRefreshedEvent.class)
	public void doSomething() {
		LOGGER.info("Aplicação iniciada");
	}

}
