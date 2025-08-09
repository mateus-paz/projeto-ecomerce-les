package strategy;

import dominio.EntidadeDominio;

// TODO: Renomear para IRegraNegocio
public interface IStrategy {
	public String processar(EntidadeDominio entidade);
}
