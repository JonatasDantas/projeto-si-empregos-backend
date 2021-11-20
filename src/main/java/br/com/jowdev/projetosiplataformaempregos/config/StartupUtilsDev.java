package br.com.jowdev.projetosiplataformaempregos.config;

import br.com.jowdev.projetosiplataformaempregos.controller.form.SignupForm;
import br.com.jowdev.projetosiplataformaempregos.models.Company;
import br.com.jowdev.projetosiplataformaempregos.models.ContentType;
import br.com.jowdev.projetosiplataformaempregos.models.Contents;
import br.com.jowdev.projetosiplataformaempregos.models.Experience;
import br.com.jowdev.projetosiplataformaempregos.models.Job.Job;
import br.com.jowdev.projetosiplataformaempregos.models.Knowledge;
import br.com.jowdev.projetosiplataformaempregos.models.user.KnowledgeLevel;
import br.com.jowdev.projetosiplataformaempregos.models.user.UserGender;
import br.com.jowdev.projetosiplataformaempregos.models.user.UserKnowledge;
import br.com.jowdev.projetosiplataformaempregos.repository.*;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Profile("dev")
public class StartupUtilsDev{

	Logger LOGGER = LoggerFactory.getLogger(StartupUtilsDev.class);

	@Autowired
	KnowledgeRepository knowledgeRepository;

	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	ExperienceRepository experienceRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserKnowledgeRepository userKnowledgeRepository;

	@Autowired
	JobRepository jobRepository;

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	ContentsRepository contentsRepository;

	@EventListener(ContextRefreshedEvent.class)
	@Transactional
	public void doSomething() {

		LOGGER.info("Aplicação iniciada");
		val angularKnowledge = new Knowledge();
		val htmlCssKnowledge = new Knowledge();
		val springKnowledge = new Knowledge();

		springKnowledge.setName("Spring Boot");
		springKnowledge.setDescription("Spring Boot é uma framework de Desenvolvimento backend feito para a Java Virtual Machine, ele provê ferramentas para a construção de aplicações robustas que seguem boas práticas e padrões de projeto como MVC e IoC. ");

		knowledgeRepository.saveAll(
				Arrays.asList(
						angularKnowledge,
						springKnowledge,
						htmlCssKnowledge
				)
		);

		configureAngularKnowledge(angularKnowledge);
		configureHtmlCssKnowledge(htmlCssKnowledge);

		configureFirstUser(angularKnowledge);
		configureSecondUser(angularKnowledge);

		val company = new Company();
		company.setName("Nubank");
		company.setCity("São Paulo");
		company.setCep("08220270");
		company.setComplement("");
		company.setCnpj("00000000191");
		company.setNumber(12);
		company.setState("SP");
		company.setUser(userRepository.findAll().get(0));
		company.setLogoUrl("https://pbs.twimg.com/profile_images/1435308291756802049/5aNaRJtl_400x400.jpg");

		val company2 = new Company();
		company2.setName("Itaú Unibanco");
		company2.setCity("São Paulo");
		company2.setCep("08220270");
		company2.setComplement("");
		company2.setCnpj("00000000191");
		company2.setNumber(12);
		company2.setState("SP");
		company2.setUser(userRepository.findAll().get(0));
		company2.setLogoUrl("https://www.itau.com.br/content/dam/itau/varejo/logo-itau-varejo-desktop.png");

		companyRepository.saveAll(Arrays.asList(company, company2));

		val newJob = new Job();
		newJob.setKnowledges(Arrays.asList(angularKnowledge));
		newJob.setTitle("Desenvolvedor Angular JR");
		newJob.setCompany(company);
		newJob.setDescription("Analista desenvolvedor de Angular focado em aprender novas experiências e se dedicar à equipe de forma que trabalhar seja a única coisa que deseja em sua vida");
		newJob.setFullDescription("    # Somos movidos por histórias ❤\n" +
				"\n" +
				"    ## A bank, an universe\n" +
				"\n" +
				"    Nubank was born from a simple idea: everyone should have control over their own money.\n" +
				"    We enable millions of Latin Americans to live better by freeing our customers from a\n" +
				"    bureaucratic, slow, and inefficient traditional banking system. With operations in Brazil,\n" +
				"    [Mexico](https://www.visitmexico.com/), Colombia, and offices in Argentina, Germany, and the United States, Nubank is the\n" +
				"    world's largest independent digital bank, reinventing over 40 million customers' financial\n" +
				"    lives.\n" +
				"\n" +
				"    ## Engineering at Nubank\n" +
				"\n" +
				"    We strive for state-of-the-art software development practices,\n" +
				"    that currently includes a variety of technologies. While we value\n" +
				"    candidates that are familiar with them, we are also confident that\n" +
				"    software engineers who are interested in joining Nubank will be\n" +
				"    able to learn from our team.\n" +
				"\n" +
				"    > Horizontally scalable microservices written mostly in Clojure, using Finagle and leveraging upon functional programming techniques and hexagonal architecture\n" +
				"    > High throughput jobs and inter-service communication using Kafka\n" +
				"    > Continuous Integration and Deployment into AWS\n" +
				"    > Storing data in Datomic and DynamoDB\n" +
				"    > Monitoring and observability with Prometheus\n" +
				"    > Running as much as possible in Kubernetes\n" +
				"\n" +
				"    ### Benefits\n" +
				"    - Health, dental and life insurance\n" +
				"    - Meal allowance\n" +
				"    - Transportation assistance\n" +
				"    - 30 days of paid vacation\n" +
				"    - Equity at Nubank\n" +
				"    - Parking partnership - discounted parking in our office\n" +
				"    - Free bike parking with showers available\n" +
				"    - NuCare - Our mental health and wellness assistance program\n" +
				"    - NuLanguage - Our language learning program\n" +
				"    - Gympass partnership\n" +
				"    - Extended maternity and paternity Leaves\n" +
				"    - Child care allowance\n" +
				"    - ‘Espaço Feijão’- Private nursing and breastfeeding spaces in our buildings\n" +
				"    - Onsite Health Center - Medical support for every Nubanker in our office\n" +
				"\n" +
				"    ");
		newJob.setSalary(5000f);

		val newJob2 = new Job();
		newJob2.setKnowledges(Arrays.asList(springKnowledge, angularKnowledge, htmlCssKnowledge));
		newJob2.setTitle("Engenheiro TI Spring PL");
		newJob2.setCompany(company2);
		newJob2.setDescription("Analista desenvolvedor de Spring experiente em esteiras AWS code pipeline e Terraform");
		newJob2.setFullDescription("  Estamos buscando pessoas que queiram se desenvolver e aprender constantemente, compartilhando, colaborando, inovando e entregando valor para todos os nossos clientes.\n" +
				"\n" +
				" \n" +
				"\n" +
				"Para que isso aconteça, temos um time de peso que busca as melhores tecnologias e garante que os processos sejam os mais eficientes. Quer fazer parte da transformação? Venha conhecer essa e outras oportunidades!\n" +
				"\n" +
				" \n" +
				"\n" +
				"# Sobre nós\n" +
				"\n" +
				"Queremos ser a empresa que pessoas de todos os gêneros, raça, idade, classe, orientação sexual e com deficiência escolham como lugar onde vão se desenvolver e contribuir para transformar as experiências dos nossos clientes.\n" +
				"\n" +
				" \n" +
				"\n" +
				"## Esses são os valores que nos guiam:\n" +
				"\n" +
				"- Buscamos aprendizado constante\n" +
				"- Focamos na melhor experiência para o cliente\n" +
				"- Somos colaborativos e sabemos oferecer e pedir ajuda\n" +
				"- Somos proativos! Pensamos e agimos como dono\n" +
				"- Temos resiliência e não nos limitamos\n" +
				"- Ética para nós é inegociável\n" +
				" \n" +
				"\n" +
				"# E a área de Canais? \n" +
				"\n" +
				"Somos um time multidisciplinar, responsável pela evolução das ferramentas comerciais do Itaú-Unibanco\n" +
				"\n" +
				"Trabalhamos para entregar as melhores experiências para a rede de agências, através da digitalização de processos e construção de soluções simples, seguras e encantadoras. \n" +
				"\n" +
				"\n" +
				"## O que esperamos de você:\n" +
				"\n" +
				"- [x] Protagonismo, esteja onde estiver, independente da posição ou situação, lidere as soluções em que estiver envolvido\n" +
				"- [x] Foco na melhor experiência para o cliente\n" +
				"- [x] Buscar aprendizado constantemente\n" +
				"- [x] Colaboração, saber oferecer e pedir ajuda\n" +
				"- [x] Proatividade, nós pensamos e agimos como dono\n" +
				"- [x] Resiliência, não se limite\n" +
				"- [x] Ética, para nós é inegociável");
		newJob2.setSalary(5000f);

		jobRepository.saveAll(Arrays.asList(newJob, newJob2));
		
		
		val experience = new Experience();
		experience.setCompanyName("Hefest S/A");
		experience.setOffice("Analista de RH");
		experience.setInitialDate("05/19");
		experience.setEndDate("02/21");
		experience.setUser(userRepository.findAll().get(0));
		
		experienceRepository.saveAll(Arrays.asList(experience));

	}

	private void configureHtmlCssKnowledge(Knowledge htmlCssKnowledge) {
		htmlCssKnowledge.setName("HTML e CSS");
		htmlCssKnowledge.setDescription("HTML e CSS são a base da Web atualmente, este conhecimento é extremamente necessário para desenvolvedores que desejam entrar no mundo do Desenvolvimento Web");

		List<Contents> htmlCssKnowledgeContents = new ArrayList<>();


		val hefestCursoHtmlContent = new Contents(
				ContentType.CURSO,
				"Curso HTML e CSS - Oficial Hefest",
				"https://www.youtube.com/watch?v=QEGkO3it09w&list=PLzDroLqiUeBgz5-e9d2FBEBVrk6NzuXqJ",
				htmlCssKnowledge,
				"Curso completo de HTML e CSS moderno oferecido gratuitamente pela sua plataforma de empregos e aprendizados Hefest!",
				""
		);

		htmlCssKnowledgeContents.addAll(Arrays.asList(
				hefestCursoHtmlContent
				));

		htmlCssKnowledgeContents = contentsRepository.saveAll(htmlCssKnowledgeContents);

		htmlCssKnowledge.setContents(htmlCssKnowledgeContents);
	}

	private void configureAngularKnowledge(Knowledge angularKnowledge) {
		angularKnowledge.setName("Angular");
		angularKnowledge.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/c/cf/Angular_full_color_logo.svg/250px-Angular_full_color_logo.svg.png");
		angularKnowledge.setDescription("Angular é um framework Javascript criado pela google, com o objetivo de gerar Single Page Applications modernas de forma confiável usando a linguagem Typescript");

		List<Contents> angularContents = new ArrayList<>();

		angularContents.add(new Contents(
				ContentType.CURSO,
				"Curso Angular 9",
				"https://www.youtube.com/playlist?list=PLdPPE0hUkt0rPyAkdhHIIquKbwrGUkvw3",
				angularKnowledge,
				"Curso completo de Angular 100% gratuito presente no youtube, oferecido por Cod3r Cursos",
				""));

		angularContents.add(new Contents(
				ContentType.PODCAST,
				"Angular vs React – Hipsters #142",
				"https://hipsters.tech/angular-vs-react-hipsters-142/",
				angularKnowledge,
				null,
				""));

		angularContents = contentsRepository.saveAll(angularContents);

		angularKnowledge.setContents(angularContents);
	}

	private void configureFirstUser(Knowledge angularKnowledge) {
		// Adiciona o Usuário Inicial
		val userForm = SignupForm.builder()
				.cpf("00000000191")
				.email("admin@admin.com")
				.firstName("Ademir")
				.lastName("Ademilson")
				.password("123456")
				.phone("11987566523")
				.city("Rio de Janeiro")
				.state("RJ")
				.gender(UserGender.Masculino)
				.recruiter(true)
				.build();

		var user = userForm.convert(roleRepository);
		user.setRoles(new ArrayList<>());

		user = userRepository.save(user);
		user.setRoles(roleRepository.findAll());


		val userKnowledge = new UserKnowledge();
		userKnowledge.setUser(user);
		userKnowledge.setKnowledge(angularKnowledge);
		userKnowledge.setKnowledgeLevel(KnowledgeLevel.EXPERT);
		userKnowledge.setValidated(true);

		userKnowledgeRepository.save(userKnowledge);
	}
	
	private void configureSecondUser(Knowledge angularKnowledge) {
		// Adiciona o Usuário Inicial
		val userForm = SignupForm.builder()
				.cpf("00000000222")
				.email("usuario@usuario.com")
				.firstName("Joelson")
				.lastName("Joenilson")
				.password("123456")
				.phone("11987566599")
				.city("Rio de Janeiro")
				.state("RJ")
				.gender(UserGender.Masculino)
				.recruiter(false)
				.build();

		var user = userForm.convert(roleRepository);
		user.setRoles(new ArrayList<>());

		user = userRepository.save(user);
		user.setRoles(roleRepository.findAll());


		val userKnowledge = new UserKnowledge();
		userKnowledge.setUser(user);
		userKnowledge.setKnowledge(angularKnowledge);
		userKnowledge.setKnowledgeLevel(KnowledgeLevel.EXPERT);
		userKnowledge.setValidated(true);

		userKnowledgeRepository.save(userKnowledge);
	}

}
