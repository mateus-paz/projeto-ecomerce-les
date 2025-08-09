package controle.web.viewhelper.persistencia;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.web.viewhelper.AbstractViewHelper;

public class LoginPersistenciaVH extends AbstractViewHelper {

    @Override
    public InputDTO getDTO(HttpServletRequest request) {
        // LoginPersistenciaDTO dto = new LoginPersistenciaDTO();
        // dto.setEmail(getParameter(request, "txtEmail"));
        // dto.setSenha(getParameter(request, "txtSenha"));
        return null;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // Lógica para definir a view após a tentativa de login
    }
}
