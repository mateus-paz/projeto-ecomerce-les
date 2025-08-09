package controle.web.viewhelper.persistencia;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.ItemCarrinhoPersistenciaDTO;
import controle.web.viewhelper.IViewHelper;
import dominio.venda.Carrinho;
import dominio.venda.ItemEstoque;

public class ItemCarrinhoPersistenciaViewHelper implements IViewHelper {

    @Override
    public ItemCarrinhoPersistenciaDTO getDTO(HttpServletRequest request) {
        ItemCarrinhoPersistenciaDTO itemDTO = new ItemCarrinhoPersistenciaDTO();
        String operacao = request.getParameter("operacao");

        Carrinho carrinho = (Carrinho) request.getSession().getAttribute("carrinho");
        // itemDTO.setCarrinho(carrinho);

        if (operacao != null) {
            if (operacao.equals("Adicionar")) {
                ItemEstoque itEstoque = (ItemEstoque) request.getSession().getAttribute("item");
                if (itEstoque != null) {
                    // itemDTO.setLivro(itEstoque.getLivro());
                    // itemDTO.setValorVenda(itEstoque.getValorVenda());
                }
                int qnt = 1;
                String txtQnt = request.getParameter("txtQnt");
                if (txtQnt != null && !txtQnt.equals("") && !txtQnt.equals("0")) {
                    qnt = Integer.valueOf(txtQnt);
                }
                itemDTO.setQuantidade(qnt);

            } else if (operacao.equals("Editar")) {
                int qnt = 1;
                int idItem = Integer.valueOf(request.getParameter("idItem"));
                itemDTO.setId(idItem);

                String txtQnt = request.getParameter("txtQnt");
                if (txtQnt != null && !txtQnt.equals("") && !txtQnt.equals("0")) {
                    qnt = Integer.valueOf(txtQnt);
                }
                itemDTO.setQuantidade(qnt);
            }
        }
        return itemDTO;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // Lógica para definir a view após a persistência do item do carrinho
    }
}
