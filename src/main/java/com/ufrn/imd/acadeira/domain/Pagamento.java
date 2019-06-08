package com.ufrn.imd.acadeira.domain;

public class Pagamento{

	private int id;
	
	private String status;
	
	private double valor;
	
	private String tipo;
	
	private int quant;

	public Pagamento() {

	}

	public Pagamento(String status, double valor, String tipo) {
		this.status = status;
		this.valor = valor;
		this.tipo = tipo;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getQuant() {
		return quant;
	}

	public void setQuant(int quant) {
		this.quant = quant;
	}
}
