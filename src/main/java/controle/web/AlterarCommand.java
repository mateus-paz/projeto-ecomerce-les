package controle.web;

import dominio.EntidadeDominio;
import dominio.Resultado;

public class AlterarCommand extends AbstractCommand {

	@Override
	public Resultado executar(EntidadeDominio entidade) {
		String mensagemErro = fachada.alterar(entidade);

		Resultado resultado = new Resultado();
		resultado.setMensagemErro(mensagemErro);

		return resultado;
	}

}
