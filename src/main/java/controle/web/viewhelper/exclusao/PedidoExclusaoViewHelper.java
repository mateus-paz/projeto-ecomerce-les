package controle.web.viewhelper.exclusao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.PedidoPersistenciaDTO;
import controle.web.viewhelper.IViewHelper;

public class PedidoExclusaoViewHelper implements IViewHelper {

    @Override
    public PedidoPersistenciaDTO getDTO(HttpServletRequest request) {
        PedidoPersistenciaDTO pedidoPersistenciaDTO = new PedidoPersistenciaDTO();
        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            pedidoPersistenciaDTO.setId(Long.parseLong(idParam));
        }
        return pedidoPersistenciaDTO;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // Lógica para definir a view após a exclusão do pedido
    }
}
