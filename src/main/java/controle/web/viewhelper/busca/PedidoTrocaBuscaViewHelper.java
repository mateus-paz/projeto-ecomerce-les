package controle.web.viewhelper.busca;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.PedidoTrocaPersistenciaDTO;
import controle.web.viewhelper.IViewHelper;
import dominio.cliente.Cliente;
import dominio.cliente.Usuario;
import dominio.venda.Pedido;

public class PedidoTrocaBuscaViewHelper implements IViewHelper {

    @Override
    public PedidoTrocaPersistenciaDTO getDTO(HttpServletRequest request) {
        PedidoTrocaPersistenciaDTO pedidoTrocaPersistenciaDTO = new PedidoTrocaPersistenciaDTO();
        String operacao = request.getParameter("operacao");

        if (operacao != null) {
            if (operacao.equals("ConsultarPorId")) {
                String txtIdPedido = request.getParameter("idPedido");
                if (txtIdPedido != null && !txtIdPedido.isEmpty()) {
                    pedidoTrocaPersistenciaDTO.setId(Long.parseLong(txtIdPedido));
                }
            } else if (operacao.equals("Consultar")) {
                Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
                if (usuario != null && !usuario.isAdmin()) {
                    Cliente cliente = usuario.getCliente();
                    Pedido pedido = new Pedido();
                    pedido.setCliente(cliente);
                    // pedidoTrocaPersistenciaDTO.setPedido(pedido);
                }
            } else if (operacao.equals("ConsultarPorPedido")) {
                Pedido pedido = (Pedido) request.getSession().getAttribute("pedido");
                // pedidoTrocaPersistenciaDTO.setPedido(pedido);
            }
        }
        return pedidoTrocaPersistenciaDTO;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // Lógica para definir a view após a busca do pedido de troca
    }
}
