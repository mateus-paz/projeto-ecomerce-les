package strategy.impl;

import dominio.EntidadeDominio;
import dominio.venda.ItemEstoque;

public class ValidarDadosDeEstoque extends AbstractValidador {

    @Override
    public String processar(EntidadeDominio entidade) {

        ItemEstoque item = (ItemEstoque) entidade;
        
        if(isBlank(item.getPrecoCusto())) {
            sb.append("Para toda entrada em estoque, e obrigatorio informar o preco de custo");
        }
        
        if(isBlank(item.getLivro())){
            sb.append("Para toda entrada em estoque, e obrigatorio informar o livro ");
        }
        
        if(isBlank(item.getQuantidade())) {
            sb.append("Para toda entrada em estoque, e obrigatorio informar a quantidade");
        }
        
        if(item.getQuantidade() <= 0) {
            sb.append("O valor minimo para adicao de livros em estoque e 1 ");
        }
        
        DefinirValorItem dvI = new DefinirValorItem();  
        dvI.processar(item);
        //Add Fornecedor
//        if(isNull(item.getQuantidade())) {
//            
//        }
        
        if(sb.length() > 0)
            return sb.toString();
        
        
        return null;
    }

}
