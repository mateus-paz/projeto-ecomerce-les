package controle.web.viewhelper.persistencia;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.CarrinhoPersistenciaDTO;
import controle.web.viewhelper.IViewHelper;
import dao.impl.ClienteDAO;
import dominio.cliente.Cliente;
import dominio.cliente.Usuario;

public class CarrinhoPersistenciaViewHelper implements IViewHelper {

    @Override
    public InputDTO getDTO(HttpServletRequest request) {
        CarrinhoPersistenciaDTO CarrinhoPersistenciaDTO = new CarrinhoPersistenciaDTO();
        Usuario usr = (Usuario) request.getSession().getAttribute("usuario");
        if (usr != null) {
            ClienteDAO clienteDao = new ClienteDAO();
            Cliente cliente = clienteDao.consultarPorUser(usr);
            // CarrinhoPersistenciaDTO.setCliente(cliente);
        }
        return CarrinhoPersistenciaDTO;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // Lógica para definir a view após a persistência do carrinho
    }
}
