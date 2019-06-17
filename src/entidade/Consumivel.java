package entidade;

public class Consumivel{
	private int id_consumivel;
	
	private String preco;
	
	private String nome;
		
	public Consumivel() {
		
	}
	
	public Consumivel(String preco, String nome) {
		this.preco = preco;
		this.nome = nome;
	}
	
	public int getId_consumivel() {
		return id_consumivel;
	}

	public void setId_consumivel(int id_consumivel) {
		this.id_consumivel = id_consumivel;
	}

	public String getPreco() {
		return preco;
	}
	
	public void setPreco(String preco) {
		this.preco = preco;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
