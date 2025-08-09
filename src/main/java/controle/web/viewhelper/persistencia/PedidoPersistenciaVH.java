package controle.web.viewhelper.persistencia;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.PedidoPersistenciaDTO;
import controle.web.viewhelper.AbstractViewHelper;
import dominio.cliente.Cliente;
import dominio.venda.Carrinho;
import dominio.venda.CartaoCompra;
import dominio.venda.Cupom;
import dominio.venda.EnderecoEntrega;

public class PedidoPersistenciaVH extends AbstractViewHelper {

    @Override
    public InputDTO getDTO(HttpServletRequest request) {
        PedidoPersistenciaDTO dto = new PedidoPersistenciaDTO();
        dto.setId(super.getId(request));

        String clienteId = getParameter(request, "clienteId");
        if (clienteId != null) {
            Cliente cliente = new Cliente();
            // cliente.setId(Long.parseLong(clienteId));
            // dto.setCliente(cliente);
        }

        String carrinhoId = getParameter(request, "carrinhoId");
        if (carrinhoId != null) {
            Carrinho carrinho = new Carrinho();
            // carrinho.setId(Long.parseLong(carrinhoId));
            // dto.setCarrinho(carrinho);
        }

        String enderecoId = getParameter(request, "enderecoId");
        if (enderecoId != null) {
            EnderecoEntrega endereco = new EnderecoEntrega();
            // endereco.setId(Long.parseLong(enderecoId));
            // dto.setEndereco(endereco);
        }

        String[] cartoesId = request.getParameterValues("cartoesId");
        if (cartoesId != null) {
            dto.setCartoes(new ArrayList<>());
            for (String id : cartoesId) {
                CartaoCompra cc = new CartaoCompra();
                // cc.setId(Long.parseLong(id));
                // dto.getCartoes().add(cc);
            }
        }

        String[] cuponsId = request.getParameterValues("cuponsId");
        if (cuponsId != null) {
            dto.setCupons(new ArrayList<>());
            for (String id : cuponsId) {
                Cupom cupom = new Cupom();
                // cupom.setId(Long.parseLong(id));
                // dto.getCupons().add(cupom);
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
        // Lógica para definir a view após a persistência do pedido
    }
}
