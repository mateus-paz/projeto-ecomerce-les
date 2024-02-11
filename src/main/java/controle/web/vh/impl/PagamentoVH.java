package controle.web.vh.impl;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.web.vh.IViewHelper;
import dominio.EntidadeDominio;
import dominio.Resultado;
import dominio.cliente.Cliente;
import dominio.venda.Carrinho;
import dominio.venda.CartaoCompra;
import dominio.venda.CartaoCredito;
import dominio.venda.Cupom;
import dominio.venda.EnderecoEntrega;
import dominio.venda.Pagamento;

public class PagamentoVH implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        Pagamento pagamento = null;
        CartaoCompra cardCompra = null;

        String operacao = request.getParameter("operacao");
        String valor = request.getParameter("txtValor");

        if (operacao != null) {
            if (operacao.equals("Alterar")) {
                pagamento = (Pagamento) request.getSession().getAttribute("pagamento");

                CartaoCompra cartao = new CartaoCompra();

                String idCartao = request.getParameter("idCartao");

                cartao.setId(Integer.valueOf(idCartao));
                cartao.setValor(new BigDecimal(valor));

                pagamento.setNovoCartao(cartao);

            } else if (operacao.equals("Remover")) {
                pagamento = (Pagamento) request.getSession().getAttribute("pagamento");
                String idCartao = request.getParameter("idCartao");

                if (idCartao != null) {

                    CartaoCompra cartao = new CartaoCompra();

                    cartao.setId(Integer.valueOf(idCartao));

                    pagamento.setNovoCartao(cartao);
                } else {
                    String idCupom = request.getParameter("idCupom");
                    Cupom cupom = new Cupom();

                    cupom.setId(Integer.valueOf(idCupom));

                    pagamento.setNovoCupom(cupom);

                }

            } else if (operacao.equals("Adicionar") || operacao.equals("Selecionar")) {
                pagamento = (Pagamento) request.getSession().getAttribute("pagamento");

                if (valor != null) {
                    CartaoCreditoVH cardVh = new CartaoCreditoVH();
                    CartaoCredito cartao = (CartaoCredito) cardVh.getEntidade(request);

                    cardCompra = CartaoCompra.builder()
                            .cartao(cartao)
                            .valor(new BigDecimal(valor)).build();

                    String isSalvar = request.getParameter("swtSalvarCartao");
                    if (isSalvar != null)
                        cardCompra.setRegistrar(true);

                    pagamento.setNovoCartao(cardCompra);

                } else {
                    Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
                    String codigo = request.getParameter("txtCodigo");

                    Cupom cupom = null;
                    if (codigo != null)
                        cupom = Cupom.builder()
                                .codigo(codigo)
                                .cliente(cliente).build();

                    pagamento.setNovoCupom(cupom);
                }

            }

        } else {

            CarrinhoVH cartVh = new CarrinhoVH();
            Carrinho carrinho = (Carrinho) cartVh.getEntidade(request);

            EnderecoEntregaVH endVh = new EnderecoEntregaVH();
            EnderecoEntrega endereco = (EnderecoEntrega) endVh.getEntidade(request);

            pagamento = new Pagamento();

            pagamento.setCarrinho(carrinho);
            pagamento.setEndereco(endereco);

        }

        return pagamento;
    }

    @Override
    public void setEntidade(HttpServletResponse response, HttpServletRequest request, Resultado resultado) {
        Pagamento pagamento = (Pagamento) resultado.getEntidades().get(0);
        String operacao = request.getParameter("operacao");

        String mensagemErro = (String) request.getAttribute("mensagemErro");

        if (mensagemErro == null)
            if (operacao != null)
                if (operacao.equals("Adicionar") || operacao.equals("Selecionar")) {
                    if (pagamento.getNovoCartao() != null)
                        pagamento.getCartoes().add(pagamento.getNovoCartao());
                    if (pagamento.getNovoCupom() != null)
                        pagamento.getCupons().add(pagamento.getNovoCupom());
                } else if (operacao.equals("Remover")) {
                    if (pagamento.getNovoCartao() != null) {
                        for (CartaoCompra cartao : pagamento.getCartoes()) {
                            if (cartao.getId() == pagamento.getNovoCartao().getId()) {
                                pagamento.getCartoes().remove(cartao);
                                break;
                            }
                        }

                    }
                    if (pagamento.getNovoCupom() != null)
                        for (Cupom cupom : pagamento.getCupons()) {
                            if (cupom.getId() == pagamento.getNovoCupom().getId()) {
                                pagamento.getCupons().remove(cupom);
                                break;
                            }
                        }

                } else if (operacao.equals("Alterar")) {
                    if (pagamento.getNovoCartao() != null)
                        for (CartaoCompra cartao : pagamento.getCartoes()) {
                            if (cartao.getId() == pagamento.getNovoCartao().getId()) {
                                cartao.setValor(pagamento.getNovoCartao().getValor());

                            }
                        }
                }

        pagamento.setNovoCartao(null);
        pagamento.setNovoCupom(null);

        request.getSession().setAttribute("pagamento", pagamento);

    }

}
