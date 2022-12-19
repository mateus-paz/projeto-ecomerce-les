package controle.web;

import dominio.EntidadeDominio;

public interface ICommand {
	public Object executar(EntidadeDominio entidade); 

}
