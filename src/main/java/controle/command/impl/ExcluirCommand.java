package controle.command.impl;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.output.OutputDTO;

public class ExcluirCommand extends AbstractCommand {

    @Override
    public OutputDTO executar(InputDTO entidade) {
        fachada.excluir(null);
        return null;
    }

}
