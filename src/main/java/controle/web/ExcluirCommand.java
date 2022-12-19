package controle.web;

import dominio.EntidadeDominio;

public class ExcluirCommand extends AbstractCommand {

	@Override
	public Object executar(EntidadeDominio entidade) {
		return fachada.excluir(entidade);
	}

}
