package controle.web.viewhelper.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.PedidoTrocaPersistenciaDTO;
import controle.web.viewhelper.IViewHelper;
import dominio.venda.ItemPedido;
import dominio.venda.ItemTroca;
import dominio.venda.Pedido;
import dominio.venda.StatusPedido;

public class PedidoTrocaPersistenciaViewHelper implements IViewHelper {

    @Override
    public PedidoTrocaPersistenciaDTO getDTO(HttpServletRequest request) {
        PedidoTrocaPersistenciaDTO pedidoTrocaPersistenciaDTO = new PedidoTrocaPersistenciaDTO();
        String operacao = request.getParameter("operacao");

        if (operacao != null) {
            if (operacao.equals("Alterar")) {
                // Assuming the existing PedidoTroca object is in session for alteration
                // In a DTO-based approach, we'd typically pass the ID and new status
                // For now, we'll simulate by setting the status directly
                String txtValStatus = request.getParameter("txtValStatus");
                if (txtValStatus != null && !txtValStatus.isEmpty()) {
                    int valorStatus = Integer.valueOf(txtValStatus);
                    StatusPedido status = null;
                    if (valorStatus == 4) {
                        status = StatusPedido.TROCA;
                    } else if (valorStatus == 5) {
                        status = StatusPedido.AUTORIZADA;
                    } else if (valorStatus == 6) {
                        status = StatusPedido.RECUSADA;
                    } else if (valorStatus == 7) {
                        status = StatusPedido.TROCADO;
                    }
                    // pedidoTrocaPersistenciaDTO.setStatus(status);
                }
            } else if (operacao.equals("Salvar")) {
                Pedido pedido = (Pedido) request.getSession().getAttribute("pedido");
                if (pedido != null) {
                    // pedidoTrocaPersistenciaDTO.setPedido(pedido);
                    // pedidoTrocaPersistenciaDTO.getPedido().setStatus(StatusPedido.TROCA);

                    List<ItemTroca> itens = new ArrayList<>();
                    for (ItemPedido item : pedido.getItens()) {
                        String idItemParam = request.getParameter("idItem" + item.getId());
                        if (idItemParam != null) {
                            String quantidadeParam = request.getParameter("txtQnt" + item.getId());
                            if (quantidadeParam != null && !quantidadeParam.isEmpty()) {
                                int quantidade = Integer.valueOf(quantidadeParam);
                                if (quantidade != 0) {
                                    ItemTroca itemTroca = new ItemTroca();
                                    itemTroca.setLivro(item.getLivro());
                                    itemTroca.setQuantidade(quantidade);
                                    itemTroca.setValorVenda(item.getValorVenda());
                                    itens.add(itemTroca);
                                }
                            }
                        }
                    }
                    // pedidoTrocaPersistenciaDTO.setItens(itens);
                }
            }
        }
        return pedidoTrocaPersistenciaDTO;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // Lógica para definir a view após a persistência do pedido de troca
    }
}
