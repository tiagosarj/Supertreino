package br.ufba.matc89.model;

import java.util.Date;

public class Medida {
	
	private Integer peso;
	private Integer altura;
	private Integer cintura;
	private Integer quadril;
	private Date dataAfericao;
	
	public Medida(Integer peso, Integer altura){
		setPeso(peso);
		setAltura(altura);		
	}
	
	public Integer getPeso() {
		return peso;
	}
	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	public Integer getAltura() {
		return altura;
	}
	public void setAltura(Integer altura) {
		this.altura = altura;
	}
	public Integer getCintura() {
		return cintura;
	}
	public void setCintura(Integer cintura) {
		this.cintura = cintura;
	}
	public Integer getQuadril() {
		return quadril;
	}
	public void setQuadril(Integer quadril) {
		this.quadril = quadril;
	}
	public Date getDataAfericao() {
		return dataAfericao;
	}
	public void setDataAfericao(Date dataAfericao) {
		this.dataAfericao = dataAfericao;
	}
}
