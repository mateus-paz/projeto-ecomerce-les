package controle.web.viewhelper.busca;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.ClientePersistenciaDTO;
import controle.web.viewhelper.IViewHelper;
import dominio.cliente.Usuario;

public class ClienteBuscaViewHelper implements IViewHelper {

    @Override
    public ClientePersistenciaDTO getDTO(HttpServletRequest request) {
        ClientePersistenciaDTO clientePersistenciaDTO = new ClientePersistenciaDTO();
        String nmOperacao = request.getParameter("operacao");

        if (nmOperacao != null) {
            if (nmOperacao.equals("ConsultarPorId") || nmOperacao.equals("Consultar")) {
                Usuario usr = (Usuario) request.getSession().getAttribute("usuario");
                if (usr != null && !usr.isAdmin()) {
                    // clientePersistenciaDTO.setId(usr.getCliente().getId());
                    // clientePersistenciaDTO.setUsuario(usr);
                } else {
                    String txtIdCliente = request.getParameter("idCliente");
                    if (txtIdCliente != null && !txtIdCliente.isEmpty()) {
                        clientePersistenciaDTO.setId(Long.parseLong(txtIdCliente));
                    }
                }
            }
        }
        return clientePersistenciaDTO;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // Lógica para definir a view após a busca do cliente
    }
}
