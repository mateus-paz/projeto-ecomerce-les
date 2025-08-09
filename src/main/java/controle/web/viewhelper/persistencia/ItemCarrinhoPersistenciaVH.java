package controle.web.viewhelper.persistencia;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.ItemCarrinhoPersistenciaDTO;
import controle.web.viewhelper.AbstractViewHelper;
import dominio.venda.Carrinho;

public class ItemCarrinhoPersistenciaVH extends AbstractViewHelper {

    @Override
    public InputDTO getDTO(HttpServletRequest request) {
        ItemCarrinhoPersistenciaDTO dto = new ItemCarrinhoPersistenciaDTO();
        dto.setId(super.getId(request));

        String quantidadeStr = getParameter(request, "txtQuantidade");
        if (quantidadeStr != null) {
            dto.setQuantidade(Integer.parseInt(quantidadeStr));
        }

        String livroId = getParameter(request, "livroId");
        if (livroId != null) {
            // Livro livro = new Livro();
            // livro.setId(Long.parseLong(livroId));
            // dto.setLivro(livro);
        }

        String carrinhoId = getParameter(request, "carrinhoId");
        if (carrinhoId != null) {
            Carrinho carrinho = new Carrinho();
            // carrinho.setId(Long.parseLong(carrinhoId));
            // dto.setCarrinho(carrinho);
        }

        return dto;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // Lógica para definir a view após a persistência do item do carrinho
    }
}
