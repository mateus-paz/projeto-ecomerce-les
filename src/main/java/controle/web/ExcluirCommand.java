package controle.web;

import dominio.EntidadeDominio;
import dominio.Resultado;

public class ExcluirCommand extends AbstractCommand {

	@Override
	public Resultado executar(EntidadeDominio entidade) {
		String mensagemErro = fachada.excluir(entidade);

		Resultado resultado = new Resultado();
		resultado.setMensagemErro(mensagemErro);

		return resultado;
	}

}
