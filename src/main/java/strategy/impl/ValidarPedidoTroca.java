package strategy.impl;

import dominio.EntidadeDominio;
import dominio.venda.PedidoTroca;

public class ValidarPedidoTroca extends AbstractValidador {

    @Override
    public String processar(EntidadeDominio entidade) {
        PedidoTroca pedido = (PedidoTroca) entidade;
        
        if(pedido.getId() == 0)
            if(pedido.getItens().size() == 0) {
                sb.append("É preciso selecionar ao menos um item para solicitar a troca; ");
            }
            
        if(sb.length()>0){
            return sb.toString();
        }

        return null;
    }

}
