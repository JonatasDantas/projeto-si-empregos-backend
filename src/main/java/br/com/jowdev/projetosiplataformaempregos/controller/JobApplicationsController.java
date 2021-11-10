package br.com.jowdev.projetosiplataformaempregos.controller;

import br.com.jowdev.projetosiplataformaempregos.controller.dto.job.JobApplicationDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SecurityRequirement(name = "Bearer Token")
@CrossOrigin(origins = "*")
@RequestMapping("/job_applications")
public class JobApplicationsController {


}
