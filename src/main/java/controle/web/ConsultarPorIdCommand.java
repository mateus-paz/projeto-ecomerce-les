package controle.web;

import dominio.EntidadeDominio;

public class ConsultarPorIdCommand extends AbstractCommand {

    @Override
    public Object executar(EntidadeDominio entidade) {

        return fachada.consultarPorId(entidade);

    }

}
