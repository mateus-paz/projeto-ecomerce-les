package strategy.impl;

import dao.impl.ItemEstoqueDAO;
import dominio.EntidadeDominio;
import dominio.livro.Livro;
import dominio.venda.ItemCarrinho;
import dominio.venda.ItemEstoque;

public class ValidarDisponibilidadeEstoque extends AbstractValidador {

    @Override
    public String processar(EntidadeDominio entidade) {
        
        ItemCarrinho item = (ItemCarrinho) entidade;
        Livro livro = item.getLivro();
                
        ItemEstoqueDAO estoqueDao = new ItemEstoqueDAO();
        ItemEstoque itRet = estoqueDao.consultarPorLivro(livro);
        
        int qnt = item.getQuantidade();
        
        if(item.getCarrinho().getItens() != null)
            for(ItemCarrinho it : item.getCarrinho().getItens())
                if(item.getLivro().getId() == it.getLivro().getId()) {
                    qnt = item.getQuantidade() - it.getQuantidade();
                    break;
                }
        
        if(qnt > itRet.getQuantidade()) {
            sb.append("Nao ha estoque disponivel");
        }
        
        if(!itRet.isAtivo()) {
            sb.append("O produto nao esta disponivel para venda. Motivo: "+itRet.getCatStatus());
        }
        
        if(sb.length()>0) {
            return sb.toString();
        }
        
        return null;
    }

}
