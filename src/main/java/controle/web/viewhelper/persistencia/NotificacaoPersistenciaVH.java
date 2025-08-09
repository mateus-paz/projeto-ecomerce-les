package controle.web.viewhelper.persistencia;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.NotificacaoPersistenciaDTO;
import controle.web.viewhelper.AbstractViewHelper;

public class NotificacaoPersistenciaVH extends AbstractViewHelper {

    @Override
    public InputDTO getDTO(HttpServletRequest request) {
        NotificacaoPersistenciaDTO dto = new NotificacaoPersistenciaDTO();
        dto.setId(super.getId(request));
        dto.setMensagem(getParameter(request, "txtMensagem"));

        String lidaStr = getParameter(request, "chkLida");
        if (lidaStr != null) {
            // dto.setLida(Boolean.parseBoolean(lidaStr));
        }

        return dto;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // Lógica para definir a view após a persistência da notificação
    }
}
