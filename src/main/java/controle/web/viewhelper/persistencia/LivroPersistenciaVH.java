package controle.web.viewhelper.persistencia;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.LivroPersistenciaDTO;
import controle.web.viewhelper.AbstractViewHelper;

public class LivroPersistenciaVH extends AbstractViewHelper {

    @Override
    public InputDTO getDTO(HttpServletRequest request) {
        LivroPersistenciaDTO dto = new LivroPersistenciaDTO();
        dto.setId(super.getId(request));
        dto.setTitulo(getParameter(request, "txtTitulo"));
        // dto.setAutor(getParameter(request, "txtAutor"));
        dto.setEditora(getParameter(request, "txtEditora"));
        // dto.setEdicao(getParameter(request, "txtEdicao"));
        // dto.setIsbn(getParameter(request, "txtIsbn"));
        dto.setSinopse(getParameter(request, "txtSinopse"));
        dto.setCodigoBarras(getParameter(request, "txtCodigoBarras"));
        // dto.setCategoria(getParameter(request, "cbbCategoria"));
        // dto.setGrupoPrecificacao(getParameter(request, "cbbGrupoPrecificacao"));
        // dto.setStatus(getParameter(request, "cbbStatus"));

        try {
            // dto.setNumeroPaginas(Integer.parseInt(getParameter(request,
            // "txtNumeroPaginas")));
            // dto.setAltura(Double.parseDouble(getParameter(request, "txtAltura")));
            // dto.setLargura(Double.parseDouble(getParameter(request, "txtLargura")));
            // dto.setProfundidade(Double.parseDouble(getParameter(request,
            // "txtProfundidade")));
            // dto.setPeso(Double.parseDouble(getParameter(request, "txtPeso")));
        } catch (NumberFormatException e) {
            // Tratar erro de conversão se necessário
        }

        return dto;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // Lógica para definir a view após a persistência do livro
    }
}
