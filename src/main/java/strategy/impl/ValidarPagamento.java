package strategy.impl;

import java.math.BigDecimal;

import dao.impl.CupomDAO;
import dominio.EntidadeDominio;
import dominio.venda.CartaoCompra;
import dominio.venda.Cupom;
import dominio.venda.Pagamento;

public class ValidarPagamento extends AbstractValidador{

    @Override
    public String processar(EntidadeDominio entidade) {
        Pagamento pagamento = (Pagamento) entidade;
        
        CartaoCompra cartao = pagamento.getNovoCartao();
        
        BigDecimal restante = pagamento.getTotalCompra().subtract(pagamento.getTotalAlocado()) ;
          
        if(restante.toString().equals("0"))
            sb.append("O valor pendente para a compra ser efetuada ja foi totalmente alocado"+
                    "Para adicionar outras formas de pagamento, antes edite as atuais");

        if(cartao != null) {
            //Verificação se o Valor é menor do que 10 reais
            if(cartao.getValor().doubleValue()<10) {
                //Verifica se o restante a ser alocado é superior a 10
                if(restante.doubleValue()>=10) {
                    sb.append("O valor minimo para ser pago com cada cartao deve ser de RS 10,00.");
                }else {
                    BigDecimal totalCupons = new BigDecimal("0");
                    
                    for(Cupom c : pagamento.getCupons()) {
                        totalCupons = totalCupons.add(c.getValor());
                    }
                    
                    //Verifica se o Total Alocado é igual ao Valor total de cupons
                        //Unico cenario em que se pode ter um cartao com menos de 10 reais
                    if(!totalCupons.equals(pagamento.getTotalAlocado())) {
                        sb.append("O valor minimo para ser pago com cada cartao deve ser de RS 10,00.");
                    }else {
                        //Por fim, se o valor do cartao for diferente do restante, nada acontece
                                                        
                        if(!cartao.getValor().equals(restante))
                            sb.append("O valor a ser alocado deve ser equivalente ao Restante da compra");
                    }
                }
            }else {
                
                for(CartaoCompra c : pagamento.getCartoes())
                    if(c.getId() == cartao.getId()) {
                        restante = restante.add(c.getValor());
                        break;
                    }
                
                if(cartao.getValor().doubleValue() > pagamento.getTotalCompra().doubleValue()) {
                    sb.append("O valor digitado para ser alocado, é superior ao Total da compra");
                    
                }else if(cartao.getValor().doubleValue() > restante.doubleValue()) {
                    sb.append("O valor a ser alocado e superior ao Restante da compra");
                    
                }
               
            }
            
        } else {
            Cupom cupom = pagamento.getNovoCupom();

            CupomDAO cupDao = new CupomDAO();
            
            if(!cupDao.validarCupom(cupom)) {
                sb.append("Cupom inválido. Possiveis motivos: Código inálido, Cupom já foi resgatado, Cupom não pertence a esse cliente ou Cupom vencido;");
                sb.append("Verifique seus cupons em: 'sua conta > cupons '; e tente novamente.");
            }else {
                for(Cupom c : pagamento.getCupons()) {
                    if(c.getCodigo().equals(cupom.getCodigo())) {
                        sb.append("Cupom já foi resgatado nessa compra. ");
                        break;
                    }else if(c.getTpCupom().getValor() == 2 && cupom.getTpCupom().getValor() == 2) {
                        sb.append("Só é possivel resgatar um cupom promocional por compra. ");
                        break;
                    }
                    
                }
                
                if(restante.doubleValue() <= 0) {
                    sb.append("Não é possivel adicionar esse cupom porque o valor excede o total da compra de forma desnecessaria. ");
                    
                }
                
            }
            
        }
        
        if(sb.length()>0) {
            return sb.toString();
        }else {
            return null;
        }
    }

}
