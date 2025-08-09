package controle.web.viewhelper.persistencia;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.TelefonePersistenciaDTO;
import controle.web.viewhelper.AbstractViewHelper;

public class TelefonePersistenciaVH extends AbstractViewHelper {

    @Override
    public InputDTO getDTO(HttpServletRequest request) {
        TelefonePersistenciaDTO telefone = new TelefonePersistenciaDTO();
        telefone.setId(super.getId(request));
        // telefone.setTipo(super.getParameter(request, "tipoTelefone"));
        telefone.setDdd(super.getParameter(request, "txtDDD"));
        telefone.setNumero(super.getParameter(request, "txtTelefone"));
        return telefone;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // Lógica para definir a view após a persistência (redirecionamento, mensagens,
        // etc.)
        // Exemplo: request.setAttribute("mensagem", "Usuário salvo com sucesso!");
        // request.getRequestDispatcher("/algumaPagina.jsp").forward(request, response);
    }
}
