package strategy.impl;

import java.math.BigDecimal;

import dao.impl.ItemEstoqueDAO;
import dominio.EntidadeDominio;
import dominio.venda.ItemEstoque;

public class DefinirValorItem extends AbstractValidador {

    @Override
    public String processar(EntidadeDominio entidade) {
        ItemEstoque item = (ItemEstoque) entidade;

        ItemEstoqueDAO itDao = new ItemEstoqueDAO();

        ItemEstoque itemEstoque = (ItemEstoque) itDao.consultarPorId(item);

        if (item.getPrecoCusto().doubleValue() < itemEstoque.getPrecoCusto().doubleValue()) {
            item.setPrecoCusto(itemEstoque.getPrecoCusto());
        }

        item.setValorVenda(item.getPrecoCusto().multiply(new BigDecimal(
                item.getLivro().getGpPrecificacao().getValor())));

        return null;
    }

}
