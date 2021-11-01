package br.com.jowdev.projetosiplataformaempregos.controller.form;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.jowdev.projetosiplataformaempregos.models.Company;
import br.com.jowdev.projetosiplataformaempregos.models.Job.Job;
import br.com.jowdev.projetosiplataformaempregos.models.Knowledge;
import br.com.jowdev.projetosiplataformaempregos.repository.CompanyRepository;
import br.com.jowdev.projetosiplataformaempregos.repository.KnowledgeRepository;
import lombok.Data;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Data
public class JobForm {

    @NotNull
    @NotEmpty
    private String title;

    @NotNull
    @NotEmpty
    private String description;

    private Float salary;

    @NotNull
    private List<Knowledge> knowledges;

    @NotNull
    private Long companyId;


    public Job convert(CompanyRepository companyRepository, KnowledgeRepository knowledgeRepository) {
        // Encontra as Entities
        Optional<Company> company = companyRepository.findById(companyId);
        val knowledges = knowledgeRepository.findAllById(
                getKnowledges().stream().map(e -> e.getId()).collect(Collectors.toList())
        );


        // Seta as propriedades
        val job = new Job();
        job.setDescription(description);
        job.setSalary(salary);
        job.setTitle(title);
        job.setKnowledges(knowledges);
        job.setCompany(company.orElseGet(() -> {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "companhia não encontrada com id " + companyId
            );
        }));

        return job;


    }
}
