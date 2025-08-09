package controle.web.viewhelper.persistencia;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.CarrinhoPersistenciaDTO;
import controle.web.viewhelper.AbstractViewHelper;
import dominio.cliente.Cliente;

public class CarrinhoPersistenciaVH extends AbstractViewHelper {

    @Override
    public InputDTO getDTO(HttpServletRequest request) {
        CarrinhoPersistenciaDTO dto = new CarrinhoPersistenciaDTO();
        dto.setId(super.getId(request));

        String clienteId = getParameter(request, "clienteId");
        if (clienteId != null) {
            Cliente cliente = new Cliente();
            // cliente.setId(Long.parseLong(clienteId));
            // dto.setCliente(cliente);
        }

        return dto;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // Lógica para definir a view após a persistência do carrinho
    }
}
