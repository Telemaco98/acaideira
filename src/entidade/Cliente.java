package entidade;

import java.util.List;

public class Cliente {	
	private int id_cliente;
	
	private String nome;
	
	private String cpf;
	
    private List<String> enderecos;

    private List<String> telefones;

    private List<String> cartoes;

	public Cliente() {

	}

	public Cliente(int id, String nome, String cpf) {
		this.id_cliente = id;
		this.nome = nome;
		this.cpf = cpf;
	}

	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<String> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<String> enderecos) {
		this.enderecos = enderecos;
	}

	public List<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<String> telefones) {
		this.telefones = telefones;
	}

	public List<String> getCartoes() {
		return cartoes;
	}

	public void setCartoes(List<String> cartoes) {
		this.cartoes = cartoes;
	}
			
}
