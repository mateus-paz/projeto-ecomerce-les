package dominio.venda;

import java.math.BigDecimal;

import dominio.EntidadeDominio;
import dominio.livro.Livro;

public abstract class Item extends EntidadeDominio {
	private Livro livro;
	private int quantidade;
	private BigDecimal valorVenda;
	
	public Item(Livro livro, int quantidade, BigDecimal valorVenda) {
		super();
		this.livro = livro;
		this.quantidade = quantidade;
		this.valorVenda = valorVenda;
	}
	
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
    public BigDecimal getValorVenda() {
        return valorVenda;
    }
    public void setValorVenda(BigDecimal valorVenda) {
        this.valorVenda = valorVenda;
    }
   	
}
