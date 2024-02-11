package controle.web;

import dominio.EntidadeDominio;
import dominio.Resultado;

public class SalvarCommand extends AbstractCommand {

	@Override
	public Resultado executar(EntidadeDominio entidade) {
		String mensagemErro = fachada.salvar(entidade);

		Resultado resultado = new Resultado();
		resultado.setMensagemErro(mensagemErro);

		return resultado;
	}

}
