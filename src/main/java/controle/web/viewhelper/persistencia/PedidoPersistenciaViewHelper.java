package controle.web.viewhelper.persistencia;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.PedidoPersistenciaDTO;
import controle.web.viewhelper.IViewHelper;
import dominio.venda.Carrinho;
import dominio.venda.CartaoCompra;
import dominio.venda.Cupom;
import dominio.venda.EnderecoEntrega;
import dominio.venda.Pagamento;
import dominio.venda.StatusPedido;

public class PedidoPersistenciaViewHelper implements IViewHelper {

    @Override
    public PedidoPersistenciaDTO getDTO(HttpServletRequest request) {
        PedidoPersistenciaDTO pedidoPersistenciaDTO = new PedidoPersistenciaDTO();
        String operacao = request.getParameter("operacao");

        if (operacao != null) {
            if (operacao.equals("Salvar")) {
                // Assuming these objects are already in session or passed as IDs
                Carrinho carrinho = (Carrinho) request.getSession().getAttribute("carrinho");
                EnderecoEntrega endereco = (EnderecoEntrega) request.getSession().getAttribute("enderecoSelecionado");
                Pagamento pagamento = (Pagamento) request.getSession().getAttribute("pagamento");

                if (pagamento != null) {
                    List<CartaoCompra> cartoes = pagamento.getCartoes();
                    List<Cupom> cupons = pagamento.getCupons();
                    // pedidoPersistenciaDTO.setCartoes(cartoes);
                    // pedidoPersistenciaDTO.setCupons(cupons);
                }
                // pedidoPersistenciaDTO.setCarrinho(carrinho);
                // pedidoPersistenciaDTO.setEndereco(endereco);
                // pedidoPersistenciaDTO.setStatus(StatusPedido.PROCESSAMENTO);

            } else if (operacao.equals("Alterar")) {
                // Assuming the existing Pedido object is in session for alteration
                // In a DTO-based approach, we'd typically pass the ID and new status
                String numStatusParam = request.getParameter("txtStatus");
                if (numStatusParam != null && !numStatusParam.isEmpty()) {
                    int numStatus = Integer.valueOf(numStatusParam);
                    StatusPedido status = null;
                    if (numStatus == 1) {
                        status = StatusPedido.PROCESSAMENTO;
                    } else if (numStatus == 2) {
                        status = StatusPedido.TRANSITO;
                    } else if (numStatus == 3) {
                        status = StatusPedido.ENTREGUE;
                    } else if (numStatus == 4) {
                        status = StatusPedido.TROCA;
                    } else if (numStatus == 5) {
                        status = StatusPedido.AUTORIZADA;
                    } else if (numStatus == 6) {
                        status = StatusPedido.RECUSADA;
                    } else if (numStatus == 7) {
                        status = StatusPedido.TROCADO;
                    }
                    // pedidoPersistenciaDTO.setStatus(status);
                }
            }
        }
        return pedidoPersistenciaDTO;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // Lógica para definir a view após a persistência do pedido
    }
}
