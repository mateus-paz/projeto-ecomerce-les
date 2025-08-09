package controle.web.viewhelper.persistencia;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.CupomPersistenciaDTO;
import controle.web.viewhelper.AbstractViewHelper;

public class CupomPersistenciaVH extends AbstractViewHelper {

    @Override
    public InputDTO getDTO(HttpServletRequest request) {
        CupomPersistenciaDTO dto = new CupomPersistenciaDTO();
        dto.setId(super.getId(request));
        dto.setCodigo(getParameter(request, "txtCodigo"));

        String valorStr = getParameter(request, "txtValor");
        if (valorStr != null) {
            // dto.setValor(Double.parseDouble(valorStr));
        }

        // dto.setTipo(getParameter(request, "cbbTipo"));

        String ativoStr = getParameter(request, "chkAtivo");
        if (ativoStr != null) {
            // dto.setAtivo(Boolean.parseBoolean(ativoStr));
        }

        return dto;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // Lógica para definir a view após a persistência do cupom
    }
}
