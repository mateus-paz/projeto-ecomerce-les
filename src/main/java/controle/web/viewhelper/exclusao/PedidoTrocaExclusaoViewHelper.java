package controle.web.viewhelper.exclusao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.PedidoTrocaPersistenciaDTO;
import controle.web.viewhelper.IViewHelper;

public class PedidoTrocaExclusaoViewHelper implements IViewHelper {

    @Override
    public PedidoTrocaPersistenciaDTO getDTO(HttpServletRequest request) {
        PedidoTrocaPersistenciaDTO pedidoTrocaPersistenciaDTO = new PedidoTrocaPersistenciaDTO();
        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            pedidoTrocaPersistenciaDTO.setId(Long.parseLong(idParam));
        }
        return pedidoTrocaPersistenciaDTO;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // Lógica para definir a view após a exclusão do pedido de troca
    }
}
