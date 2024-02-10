package controle.web.vh.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.web.vh.IViewHelper;
import dominio.EntidadeDominio;
import dominio.cliente.Cliente;
import dominio.cliente.Usuario;
import dominio.venda.ItemPedido;
import dominio.venda.ItemTroca;
import dominio.venda.Pedido;
import dominio.venda.PedidoTroca;
import dominio.venda.StatusPedido;

public class PedidoTrocaVH implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        PedidoTroca pedidoTroca = new PedidoTroca();

        String operacao = request.getParameter("operacao");

        if (operacao != null) {
            if (operacao.equals("Alterar")) {
                pedidoTroca = (PedidoTroca) request.getSession().getAttribute("pedidoTroca");

                int valorStatus = Integer.valueOf(request.getParameter("txtValStatus"));

                if (valorStatus == 4) {
                    pedidoTroca.getPedido().setStatus(StatusPedido.TROCA);
                } else if (valorStatus == 5) {
                    pedidoTroca.getPedido().setStatus(StatusPedido.AUTORIZADA);
                } else if (valorStatus == 6) {
                    pedidoTroca.getPedido().setStatus(StatusPedido.RECUSADA);
                } else if (valorStatus == 7) {
                    pedidoTroca.getPedido().setStatus(StatusPedido.TROCADO);
                }

            } else if (operacao.equals("ConsultarPorId")) {

                String txtIdPedido = request.getParameter("idPedido");
                int idPedido = Integer.valueOf(txtIdPedido);

                pedidoTroca.setId(idPedido);

            } else if (operacao.equals("Consultar")) {

                Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

                if (!usuario.isAdmin()) {

                    Cliente cliente = usuario.getCliente();
                    Pedido pedido = new Pedido();

                    pedido.setCliente(cliente);

                    pedidoTroca.setPedido(pedido);
                }

            } else if (operacao.equals("Salvar")) {
                Pedido pedido = (Pedido) request.getSession().getAttribute("pedido");
                pedido.setStatus(StatusPedido.TROCA);

                List<ItemTroca> itens = new ArrayList<>();

                String idItem = null;
                int quantidade = 0;
                int i = 1;

                for (ItemPedido item : pedido.getItens()) {
                    idItem = request.getParameter("idItem" + i);

                    if (idItem == null) {
                        continue;
                    } else if (item.getId() == Integer.valueOf(idItem)) {

                        quantidade = Integer.valueOf(request.getParameter("txtQnt" + item.getId()));

                        if (quantidade != 0) {
                            ItemTroca itemTroca = new ItemTroca();

                            itemTroca.setLivro(item.getLivro());
                            itemTroca.setQuantidade(quantidade);
                            itemTroca.setValorVenda(item.getValorVenda());

                            itens.add(itemTroca);
                        }
                        quantidade = 0;
                        idItem = null;
                    }

                    i++;
                }

                pedidoTroca = PedidoTroca.builder()
                        .pedido(pedido)
                        .itens(itens).build();

            } else if (operacao.equals("ConsultarPorPedido")) {
                Pedido pedido = (Pedido) request.getSession().getAttribute("pedido");
                pedidoTroca.setPedido(pedido);

            }
        }

        return pedidoTroca;
    }

    @Override
    public void setEntidade(HttpServletResponse response, HttpServletRequest request, Object msg) {

        request.getSession().setAttribute("pedidoTroca", (PedidoTroca) msg);

    }

}
