package br.com.jowdev.projetosiplataformaempregos.models;

public enum SalaryRange {
	Ate1000("Até R$ 1.000,00", 0.0, 1000.0),
	Entre1000e2000("Entre R$ 1.000,00 e R$ 2.000,00", 1000.0, 2000.0),
	Entre2000e3000("Entre R$ 2.000,00 e R$ 3.000,00", 2000.0, 3000.0),
	Entre3000e4000("Entre R$ 3.000,00 e R$ 4.000,00", 3000.0, 4000.0),
	Entre4000e6000("Entre R$ 4.000,00 e R$ 6.000,00", 4000.0, 6000.0),
	AcimaDe6000("Acima de R$ 6.000,00", 6000.0, Double.POSITIVE_INFINITY);

	public String displayName;
	public Double minValue;
	public Double maxValue;

	SalaryRange(String displayName, Double min, Double max) {
		this.displayName = displayName;
		this.minValue = min;
		this.maxValue = max;
	}

	@Override
	public String toString() {
		return displayName;
	}
}
