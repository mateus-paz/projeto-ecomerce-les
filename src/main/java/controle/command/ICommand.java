package controle.command;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.output.OutputDTO;

public interface ICommand {
    public OutputDTO executar(InputDTO entidade);
}
