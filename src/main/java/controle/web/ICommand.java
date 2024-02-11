package controle.web;

import dominio.EntidadeDominio;
import dominio.Resultado;

public interface ICommand {
	public Resultado executar(EntidadeDominio entidade); 

}
