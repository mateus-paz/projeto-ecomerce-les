package dominio.livro;

import dominio.EntidadeDominio;

public class Autor extends EntidadeDominio{
	private String nome;

	public Autor(String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
