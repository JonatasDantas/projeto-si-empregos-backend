package br.com.jowdev.projetosiplataformaempregos.models;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SalaryRangeTest {

	@Test
	void testaValoresERanges() {
		assertThat(SalaryRange.Ate1000.minValue).isEqualTo(0.0);
		assertThat(SalaryRange.Ate1000.maxValue).isEqualTo(1000.0);
		assertThat(SalaryRange.Entre1000e2000.minValue).isEqualTo(1000.0);
		assertThat(SalaryRange.Entre1000e2000.maxValue).isEqualTo(2000.0);
		assertThat(SalaryRange.Entre2000e3000.minValue).isEqualTo(2000.0);
		assertThat(SalaryRange.Entre2000e3000.maxValue).isEqualTo(3000.0);
		assertThat(SalaryRange.Entre3000e4000.minValue).isEqualTo(3000.0);
		assertThat(SalaryRange.Entre3000e4000.maxValue).isEqualTo(4000.0);
		assertThat(SalaryRange.Entre4000e6000.minValue).isEqualTo(4000.0);
		assertThat(SalaryRange.Entre4000e6000.maxValue).isEqualTo(6000.0);
		assertThat(SalaryRange.AcimaDe6000.minValue).isEqualTo(6000.0);
		assertThat(SalaryRange.AcimaDe6000.maxValue).isEqualTo(1000000000.0);
		
		assertThat(SalaryRange.AcimaDe6000.toString()).isEqualTo(SalaryRange.AcimaDe6000.displayName);
	}

}
