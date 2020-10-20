package br.com.jowdev.projetosiplataformaempregos.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jowdev.projetosiplataformaempregos.controller.dto.QualificationDto;
import br.com.jowdev.projetosiplataformaempregos.repository.QualificationRepository;

@RestController
@RequestMapping("/qualifications")
public class QualificationController {
	
	@Autowired
	private QualificationRepository qualificationRepository;
	
	@GetMapping
	private List<QualificationDto> getQualifications() {
		return qualificationRepository
				.findAll()
				.stream()
				.map(QualificationDto::new)
				.collect(Collectors.toList());
	}
	
}
