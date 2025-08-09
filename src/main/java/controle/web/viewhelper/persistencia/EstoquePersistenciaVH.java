package controle.web.viewhelper.persistencia;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.EstoquePersistenciaDTO;
import controle.web.viewhelper.AbstractViewHelper;

public class EstoquePersistenciaVH extends AbstractViewHelper {

    @Override
    public InputDTO getDTO(HttpServletRequest request) {
        EstoquePersistenciaDTO dto = new EstoquePersistenciaDTO();
        dto.setId(super.getId(request));
        dto.setValorBusca(getParameter(request, "txtValorBusca"));

        String[] parametros = request.getParameterValues("txtParametros");
        if (parametros != null) {
            dto.setParametros(Arrays.asList(parametros));
        }

        return dto;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // Lógica para definir a view após a persistência do estoque
    }
}
