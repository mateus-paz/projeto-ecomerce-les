package controle.facade;

import java.util.List;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.output.OutputDTO;

public interface IFachada {
    public void salvar(InputDTO input);

    public void alterar(InputDTO input);

    public void excluir(InputDTO input);

    public List<OutputDTO> consultar(InputDTO input);

    public OutputDTO consultarPorId(InputDTO input);
}
