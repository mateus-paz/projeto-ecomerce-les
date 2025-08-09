package controle.web.viewhelper.persistencia;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.UsuarioPersistenciaDTO;
import controle.web.viewhelper.AbstractViewHelper;

public class UsuarioPersistenciaVH extends AbstractViewHelper {

    @Override
    public InputDTO getDTO(HttpServletRequest request) {
        UsuarioPersistenciaDTO usuario = new UsuarioPersistenciaDTO();
        usuario.setId(super.getId(request));
        usuario.setEmail(super.getParameter(request, "txtEmail"));
        usuario.setSenha(super.getParameter(request, "txtSenha"));
        // usuario.setConfirmacaoSenha(super.getParameter(request,
        // "txtConfirmarSenha"));
        return usuario;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // Lógica para definir a view após a persistência (redirecionamento, mensagens,
        // etc.)
        // Exemplo: request.setAttribute("mensagem", "Usuário salvo com sucesso!");
        // request.getRequestDispatcher("/algumaPagina.jsp").forward(request, response);
    }
}
