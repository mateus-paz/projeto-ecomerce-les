package controle.web.viewhelper.busca;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.EstoquePersistenciaDTO;
import controle.web.viewhelper.IViewHelper;

public class EstoqueBuscaViewHelper implements IViewHelper {

    @Override
    public EstoquePersistenciaDTO getDTO(HttpServletRequest request) {
        EstoquePersistenciaDTO estoquePersistenciaDTO = new EstoquePersistenciaDTO();
        String operacao = request.getParameter("operacao");

        if (operacao != null && operacao.equals("Consultar")) {
            String valorBusca = request.getParameter("txtPesquisa");
            List<String> parametros = new ArrayList<>();

            if (request.getParameter("swtTitulo") != null)
                parametros.add("lvr_titulo");

            if (request.getParameter("swtAutores") != null)
                parametros.add("atr_nome");

            if (request.getParameter("swtCategorias") != null)
                parametros.add("cat_descricao");

            if (request.getParameter("swtIsbn") != null)
                parametros.add("lvr_isbn");

            if (request.getParameter("swtEditora") != null)
                parametros.add("lvr_editora");

            estoquePersistenciaDTO.setParametros(parametros);
            estoquePersistenciaDTO.setValorBusca(valorBusca);
        }
        return estoquePersistenciaDTO;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // Lógica para definir a view após a busca do estoque
    }
}
