package controle.web.viewhelper.exclusao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.ClientePersistenciaDTO;
import controle.web.viewhelper.IViewHelper;

public class ClienteExclusaoViewHelper implements IViewHelper {

    @Override
    public ClientePersistenciaDTO getDTO(HttpServletRequest request) {
        ClientePersistenciaDTO clientePersistenciaDTO = new ClientePersistenciaDTO();
        String txtIdCliente = request.getParameter("idCliente");
        if (txtIdCliente != null && !txtIdCliente.isEmpty()) {
            clientePersistenciaDTO.setId(Long.parseLong(txtIdCliente));
        }
        return clientePersistenciaDTO;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // Lógica para definir a view após a exclusão do cliente
    }
}
