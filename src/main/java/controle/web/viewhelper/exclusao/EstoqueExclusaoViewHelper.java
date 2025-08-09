package controle.web.viewhelper.exclusao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.EstoquePersistenciaDTO;
import controle.web.viewhelper.IViewHelper;

public class EstoqueExclusaoViewHelper implements IViewHelper {

    @Override
    public EstoquePersistenciaDTO getDTO(HttpServletRequest request) {
        EstoquePersistenciaDTO estoquePersistenciaDTO = new EstoquePersistenciaDTO();
        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            estoquePersistenciaDTO.setId(Long.parseLong(idParam));
        }
        return estoquePersistenciaDTO;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // Lógica para definir a view após a exclusão do estoque
    }
}
