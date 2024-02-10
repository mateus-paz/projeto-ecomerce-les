package strategy.impl;

import java.time.LocalDate;

import dominio.EntidadeDominio;
import dominio.venda.Cupom;
import dominio.venda.PedidoTroca;
import dominio.venda.TipoCupom;

public class GerarCupomTroca extends AbstractValidador {

    @Override
    public String processar(EntidadeDominio entidade) {
        PedidoTroca pedido = (PedidoTroca) entidade;

        if (pedido.getPedido().getStatus().getValor() == 7) {
            pedido.getValorTotal();

            String codigo = "TRC";
            if (pedido.getId() < 10) {
                codigo += "0000" + pedido.getId();
            } else if (pedido.getId() < 100) {
                codigo += "000" + pedido.getId();
            } else if (pedido.getId() < 1000) {
                codigo += "00" + pedido.getId();
            } else {
                codigo += "0" + pedido.getId();
            }

            Cupom cupom = Cupom.builder()
                    .codigo(codigo)
                    .tpCupom(TipoCupom.TROCA)
                    .valor(pedido.getValorTotal())
                    .validade(LocalDate.now().plusMonths(1)).build();

            pedido.setCupom(cupom);
        }

        return null;
    }

}
