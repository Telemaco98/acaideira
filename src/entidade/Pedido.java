package entidade;

public class Pedido{

	private int id_pedido;
	
	private String status;
	
	public Pedido() {
		
	}
	
	public Pedido(int id_cliente,String status) {
		this.id_pedido = id_cliente;
		this.status = status;
	}


	public int getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(int id_pedido) {
		this.id_pedido = id_pedido;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
