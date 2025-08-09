package controle.web.viewhelper.exclusao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.UsuarioPersistenciaDTO;
import controle.web.viewhelper.IViewHelper;

public class UsuarioExclusaoViewHelper implements IViewHelper {

    @Override
    public UsuarioPersistenciaDTO getDTO(HttpServletRequest request) {
        UsuarioPersistenciaDTO usuarioPersistenciaDTO = new UsuarioPersistenciaDTO();
        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            usuarioPersistenciaDTO.setId(Long.parseLong(idParam));
        }
        return usuarioPersistenciaDTO;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // Lógica para definir a view após a exclusão (redirecionamento, mensagens,
        // etc.)
        // Exemplo: request.setAttribute("mensagem", "Usuário excluído com sucesso!");
        // request.getRequestDispatcher("/algumaPagina.jsp").forward(request, response);
    }
}
