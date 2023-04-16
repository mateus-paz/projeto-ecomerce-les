package controle.web.vh.impl;

import java.util.List;

import controle.web.vh.IViewHelper;
import dominio.EntidadeDominio;
import dominio.cliente.Cliente;
import dominio.cliente.Usuario;
import dominio.venda.Carrinho;
import dominio.venda.CartaoCompra;
import dominio.venda.Cupom;
import dominio.venda.EnderecoEntrega;
import dominio.venda.Pagamento;
import dominio.venda.Pedido;
import dominio.venda.StatusPedido;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PedidoVH implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        Pedido pedido = new Pedido();

        String operacao = request.getParameter("operacao");

        if (operacao != null) {
            if (operacao.equals("Salvar")) {
                Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
                EnderecoEntrega endereco = (EnderecoEntrega) request.getSession().getAttribute("enderecoSelecionado");
                Carrinho carrinho = (Carrinho) request.getSession().getAttribute("carrinho");
                Pagamento pagamento = (Pagamento) request.getSession().getAttribute("pagamento");

                List<CartaoCompra> cartoes = pagamento.getCartoes();
                List<Cupom> cupons = pagamento.getCupons();

                pedido = Pedido.builder()
                        .cliente(cliente)
                        .carrinho(carrinho)
                        .endereco(endereco)
                        .cartoes(cartoes)
                        .cupons(cupons).build();

                pedido.setStatus(StatusPedido.PROCESSAMENTO);

            } else if (operacao.equals("Alterar")) {
                pedido = (Pedido) request.getSession().getAttribute("pedido");

                int numStatus = Integer.valueOf(request.getParameter("txtStatus"));
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

                pedido.setStatus(status);

            } else if (operacao.equals("Consultar")) {
                Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

                if (!usuario.isAdmin()) {
                    Cliente cliente = usuario.getCliente();
                    pedido.setCliente(cliente);

                }

            } else if (operacao.equals("ConsultarPorId")) {
                String txtIdPedido = request.getParameter("idPedido");
                int idPedido = Integer.valueOf(txtIdPedido);

                pedido.setId(idPedido);

            }

        }

        return pedido;
    }

    @Override
    public void setEntidade(HttpServletResponse response, HttpServletRequest request, Object msg) {
        request.getSession().setAttribute("pedido", (Pedido) msg);

    }

}
