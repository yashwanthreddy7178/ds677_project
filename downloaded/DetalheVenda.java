package senairs.poo.aps.padaria.modelo;

public class DetalheVenda {
	
	private int codVenda;
	private int codProduto;
	private double quantidade;
	private double desconto;
	private Produto produto;
	private Venda venda;
	
	public DetalheVenda(int codVenda, int codProduto, double quantidade, double desconto, Produto produto,
			Venda venda) {
		super();
		this.codVenda = codVenda;
		this.codProduto = codProduto;
		this.quantidade = quantidade;
		this.desconto = desconto;
		this.produto = produto;
		this.venda = venda;
	}

	public int getCodVenda() {
		return codVenda;
	}

	public void setCodVenda(int codVenda) {
		this.codVenda = codVenda;
	}

	public int getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(int codProduto) {
		this.codProduto = codProduto;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
	
	
	

}
