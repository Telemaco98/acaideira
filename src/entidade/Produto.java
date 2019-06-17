package entidade;

public class Produto{

	private int id_produto;
	
	private String quantidade;
	
	private String nome;
		
	public Produto() {
		
	}
	
	public Produto(String quantidade, String nome) {
		this.quantidade = quantidade;
		this.nome = nome;
	}

	public int getId_produto() {
		return id_produto;
	}

	public void setId_produto(int id_produto) {
		this.id_produto = id_produto;
	}

	public String getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
