package controle.command.impl;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.ClientePersistenciaDTO;
import controle.facade.dto.output.OutputDTO;

public class SalvarCommand extends AbstractCommand {

    @Override
    public OutputDTO executar(InputDTO input) {
        if (!(input instanceof ClientePersistenciaDTO)) {
            throw new IllegalArgumentException("Input must be an instance of ClientePersistenciaDTO");
        }
        // OutputDTO OutputDTO = new OutputDTO();
        fachada.salvar(null);
        return null;
    }

}
