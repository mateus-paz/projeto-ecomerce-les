package dominio.cliente;

import dominio.EntidadeDominio;

public class Pais extends EntidadeDominio{
	private String nome;

	public Pais(String nome) {
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
