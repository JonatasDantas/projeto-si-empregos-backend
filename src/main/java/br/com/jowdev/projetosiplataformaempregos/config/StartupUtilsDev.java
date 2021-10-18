package br.com.jowdev.projetosiplataformaempregos.config;

import br.com.jowdev.projetosiplataformaempregos.models.Company;
import br.com.jowdev.projetosiplataformaempregos.models.Job;
import br.com.jowdev.projetosiplataformaempregos.models.Knowledge;
import br.com.jowdev.projetosiplataformaempregos.repository.CompanyRepository;
import br.com.jowdev.projetosiplataformaempregos.repository.JobRepository;
import br.com.jowdev.projetosiplataformaempregos.repository.KnowledgeRepository;
import br.com.jowdev.projetosiplataformaempregos.repository.UserRepository;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Profile("dev")
public class StartupUtilsDev{

	Logger LOGGER = LoggerFactory.getLogger(StartupUtilsDev.class);

	@Autowired
	KnowledgeRepository knowledgeRepository;

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	JobRepository jobRepository;

	@EventListener(ContextRefreshedEvent.class)
	public void doSomething() {
		LOGGER.info("Aplicação iniciada");
		val knowledge = new Knowledge();
		knowledge.setName("Angular");
		knowledgeRepository.save(
				knowledge
		);

		val company = new Company();
		company.setName("Banco basculhao");
		company.setCity("São Paulo");
		company.setCep("08220270");
		company.setComplement("");
		company.setCnpj("00000000191");
		company.setNumber(12);
		company.setState("SP");
		company.setUser(userRepository.findAll().get(0));

		companyRepository.save(company);

		val newJob = new Job();
		newJob.setKnowledges(Arrays.asList(knowledge));
		newJob.setTitle("Desenvolvedor Angular JR");
		newJob.setCompany(company);
		newJob.setDescription("Analista desenvolvedor de React focado em aprender novas experiências e se dedicar à equipe de forma que trabalhar seja a única coisa que deseja em sua vida");
		newJob.setSalary(5000f);

		jobRepository.save(newJob);

	}

}
