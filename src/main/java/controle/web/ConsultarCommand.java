package controle.web;

import java.util.List;

import dominio.EntidadeDominio;
import dominio.Resultado;

public class ConsultarCommand extends AbstractCommand {

	@Override
	public Resultado executar(EntidadeDominio entidade) {
		List<EntidadeDominio> entidades = fachada.consultar(entidade);

		Resultado resultado = new Resultado();
		resultado.setEntidades(entidades);

		return resultado;
	}

}
