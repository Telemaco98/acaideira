package com.ufrn.imd.acadeira.domain;

public class Pagamento{

	private int id_pagamento;
	
	private String endereco;
	
	private double valor;
	
	private String tipo;

	public Pagamento() {

	}

	public Pagamento(String endereco, double valor, String tipo) {

		this.endereco = endereco;
		this.valor = valor;
		this.tipo = tipo;
	}
	
	
	public int getId_pagamento() {
		return id_pagamento;
	}

	public void setId_pagamento(int id_pagamento) {
		this.id_pagamento = id_pagamento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
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
		
}
