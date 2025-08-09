package controle.web.viewhelper.persistencia;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.ItemEstoquePersistenciaDTO;
import controle.web.viewhelper.AbstractViewHelper;
import dominio.livro.Livro;

public class ItemEstoquePersistenciaVH extends AbstractViewHelper {

    @Override
    public InputDTO getDTO(HttpServletRequest request) {
        ItemEstoquePersistenciaDTO dto = new ItemEstoquePersistenciaDTO();
        dto.setId(super.getId(request));

        String quantidadeStr = getParameter(request, "txtQuantidade");
        if (quantidadeStr != null) {
            dto.setQuantidade(Integer.parseInt(quantidadeStr));
        }

        String ativoStr = getParameter(request, "chkAtivo");
        if (ativoStr != null) {
            dto.setAtivo(Boolean.parseBoolean(ativoStr));
        }

        dto.setJustificativaStatus(getParameter(request, "txtJustificativa"));

        String livroId = getParameter(request, "livroId");
        if (livroId != null) {
            // Livro livro = new Livro();
            // livro.setId(Long.parseLong(livroId));
            // dto.setLivro(livro);
        }

        return dto;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // Lógica para definir a view após a persistência do item de estoque
    }
}
