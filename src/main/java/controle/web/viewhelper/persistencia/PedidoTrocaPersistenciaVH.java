package controle.web.viewhelper.persistencia;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.PedidoTrocaPersistenciaDTO;
import controle.web.viewhelper.AbstractViewHelper;
import dominio.venda.ItemTroca;
import dominio.venda.Pedido;

public class PedidoTrocaPersistenciaVH extends AbstractViewHelper {

    @Override
    public InputDTO getDTO(HttpServletRequest request) {
        PedidoTrocaPersistenciaDTO dto = new PedidoTrocaPersistenciaDTO();
        dto.setId(super.getId(request));

        String pedidoId = getParameter(request, "pedidoId");
        if (pedidoId != null) {
            Pedido pedido = new Pedido();
            // pedido.setId(Long.parseLong(pedidoId));
            // dto.setPedido(pedido);
        }

        String[] itensId = request.getParameterValues("itensTrocaId");
        if (itensId != null) {
            dto.setItens(new ArrayList<>());
            for (String id : itensId) {
                ItemTroca item = new ItemTroca();
                // item.setId(Long.parseLong(id));
                // dto.getItens().add(item);
            }
        }

        String statusId = getParameter(request, "statusId");
        if (statusId != null) {
            // StatusPedido status = new StatusPedido();
            // status.setId(Long.parseLong(statusId));
            // dto.setStatus(status);
        }

        return dto;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // Lógica para definir a view após a persistência do pedido de troca
    }
}
