package br.ufba.matc89.model;

public class Alimento {
	protected String nome;
	protected String fonte;
	protected Double carboidrado;
	protected Double proteina;
	protected Double gordura;
	protected Double calorias;

	public Alimento(String nome, String fonte){
		this.nome = nome;
		this.fonte = fonte;
		this.carboidrado = 0.0;
		this.proteina = 0.0;
		this.gordura = 0.0;
		this.calorias = 0.0;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFonte() {
		return this.fonte;
	}

	public void setFonte(String fonte) {
		this.fonte = fonte;
	}

	public Double getCarboidrado() {
		return this.carboidrado;
	}

	public void setCarboidrado(Double carboidrado) {
		this.carboidrado = carboidrado;
	}

	public Double getProteina() {
		return this.proteina;
	}

	public void setProteina(Double proteina) {
		this.proteina = proteina;
	}

	public Double getGordura() {
		return this.gordura;
	}

	public void setGordura(Double gordura) {
		this.gordura = gordura;
	}

	public Double getCalorias() {
		return this.calorias;
	}

	public void setCalorias(Double calorias) {
		this.calorias = calorias;
	}
}
