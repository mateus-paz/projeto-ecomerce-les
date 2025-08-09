package controle.web.viewhelper.busca;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.UsuarioPersistenciaDTO;
import controle.web.viewhelper.IViewHelper;

public class UsuarioBuscaViewHelper implements IViewHelper {

    @Override
    public UsuarioPersistenciaDTO getDTO(HttpServletRequest request) {
        UsuarioPersistenciaDTO usuarioPersistenciaDTO = new UsuarioPersistenciaDTO();
        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            usuarioPersistenciaDTO.setId(Long.parseLong(idParam));
        }
        // usuarioPersistenciaDTO.setNome(request.getParameter("nome"));
        usuarioPersistenciaDTO.setEmail(request.getParameter("email"));
        return usuarioPersistenciaDTO;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // Lógica para definir a view após a busca (exibir resultados em uma tabela,
        // etc.)
        // Exemplo: request.setAttribute("usuario", dto);
        // request.getRequestDispatcher("/algumaPaginaDeBusca.jsp").forward(request,
        // response);
        try {
            request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
