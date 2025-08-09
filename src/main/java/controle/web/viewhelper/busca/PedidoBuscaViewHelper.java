package controle.web.viewhelper.busca;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.PedidoPersistenciaDTO;
import controle.web.viewhelper.IViewHelper;
import dominio.cliente.Cliente;
import dominio.cliente.Usuario;

public class PedidoBuscaViewHelper implements IViewHelper {

    @Override
    public PedidoPersistenciaDTO getDTO(HttpServletRequest request) {
        PedidoPersistenciaDTO pedidoPersistenciaDTO = new PedidoPersistenciaDTO();
        String operacao = request.getParameter("operacao");

        if (operacao != null) {
            if (operacao.equals("Consultar")) {
                Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
                if (usuario != null && !usuario.isAdmin()) {
                    Cliente cliente = usuario.getCliente();
                    // pedidoPersistenciaDTO.setCliente(cliente);
                }
            } else if (operacao.equals("ConsultarPorId")) {
                String txtIdPedido = request.getParameter("idPedido");
                if (txtIdPedido != null && !txtIdPedido.isEmpty()) {
                    pedidoPersistenciaDTO.setId(Long.parseLong(txtIdPedido));
                }
            }
        }
        return pedidoPersistenciaDTO;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // Lógica para definir a view após a busca do pedido
    }
}
