package dominio.livro;

import dominio.EntidadeDominio;

public class Categoria extends EntidadeDominio {
	private String descricao;
	
	public Categoria(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
