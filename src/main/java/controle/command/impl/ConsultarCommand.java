package controle.command.impl;

import java.util.List;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.output.OutputDTO;

public class ConsultarCommand extends AbstractCommand {

    @Override
    public OutputDTO executar(InputDTO entidade) {
        List<?> entidades = fachada.consultar(null);
        return null;
    }

}
