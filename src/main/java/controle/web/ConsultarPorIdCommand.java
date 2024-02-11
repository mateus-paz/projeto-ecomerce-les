package controle.web;

import dominio.EntidadeDominio;
import dominio.Resultado;

public class ConsultarPorIdCommand extends AbstractCommand {

    @Override
    public Resultado executar(EntidadeDominio entidade) {
    	EntidadeDominio entidadeBuscada = fachada.consultarPorId(entidade);

		Resultado resultado = new Resultado();
		resultado.setEntidade(entidadeBuscada);

		return resultado;
    }

}
