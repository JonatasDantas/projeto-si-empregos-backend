package br.com.jowdev.projetosiplataformaempregos.models;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class JobTest {

	@Test
	void DeveSetarParametros() {
		Job job = new Job();
		job.setTitle("Title");
		List<User> candidates = new ArrayList<>();
		job.setCandidates(candidates);
		Company companhia = new Company();
		job.setCompany(companhia);
		job.setDescription("Description");
		job.setId(1L);
		job.setOccupation(Occupation.Administração);
		job.setSalary(1000f);
		
		assertThat(job.getTitle()).isEqualTo("Title");
		assertThat(job.getCandidates()).isEqualTo(candidates);
		assertThat(job.getCompany()).isEqualTo(companhia);
		assertThat(job.getDescription()).isEqualTo("Description");
		assertThat(job.getId()).isEqualTo(1L);
		assertThat(job.getOccupation()).isEqualTo(Occupation.Administração);
		assertThat(job.getSalary()).isEqualTo(1000f);
	}

}
