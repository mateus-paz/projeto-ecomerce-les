package strategy.impl;

import dominio.EntidadeDominio;
import dominio.venda.CartaoCompra;
import dominio.venda.CartaoCredito;

public class ValidarDadosCartao extends AbstractValidador {
    @Override
    public String processar(EntidadeDominio entidade) {
        CartaoCredito cartao = null;
        
        if(entidade.getClass().getName().equals(CartaoCompra.class.getName())) {
            CartaoCompra cc = (CartaoCompra) entidade;
            cartao = cc.getCartao();
        }else {
            cartao = (CartaoCredito) entidade;
        }
        
        if(isNull(cartao.getNumero())) {
            sb.append("E obrigatorio o cadastro de um numero ao cartao \n");
        }
        if(isNull(cartao.getTitular())) {
            sb.append("E obrigatorio o cadastro do nome do titular cartao \n");
        } 
        
        if(isNull(cartao.getBandeira())) {
            sb.append("E obrigatorio a selecao de uma bandeira para o cartao \n");
        } else if(cartao.getBandeira().getValor()!= 1 && cartao.getBandeira().getValor()!= 2) {
            sb.append("E obrigatorio a selecao de uma bandeira para o cartao \n");
        }
        
        if(isNull(cartao.getCvv())) {
            sb.append("E obrigatorio o cadastro do CVV do cartao \n");
        }

        if(cartao.getNumero().length()!=19) {
            sb.append("O numero do Cartao deve ter 16 digitos \n");
        }
        
        if(cartao.getCvv().length()!=3) {
            sb.append("O cvv do Cartao deve ter 3 digitos \n");
        }
        
        if(sb.length()>0) {
            return sb.toString();
        }
        
        return null;
    }

}
