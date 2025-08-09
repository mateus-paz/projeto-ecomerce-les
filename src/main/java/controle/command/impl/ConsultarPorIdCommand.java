package controle.command.impl;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.output.OutputDTO;

public class ConsultarPorIdCommand extends AbstractCommand {

    @Override
    public OutputDTO executar(InputDTO entidade) {
        Object entity = fachada.consultarPorId(null);
        return null;
    }

}
