package controle.web.viewhelper.persistencia;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.CartaoCreditoPersistenciaDTO;
import controle.web.viewhelper.AbstractViewHelper;
import dominio.cliente.Cliente;

public class CartaoCreditoPersistenciaVH extends AbstractViewHelper {

    @Override
    public InputDTO getDTO(HttpServletRequest request) {
        CartaoCreditoPersistenciaDTO dto = new CartaoCreditoPersistenciaDTO();
        dto.setId(super.getId(request));
        dto.setNomeIdentificacao(getParameter(request, "txtNomeIdentificacao"));
        dto.setDataValidade(getParameter(request, "txtDataValidade"));
        dto.setTitular(getParameter(request, "txtTitular"));
        dto.setNumero(getParameter(request, "txtNumero"));
        dto.setCvv(getParameter(request, "txtCvv"));

        String bandeiraId = getParameter(request, "bandeiraId");
        if (bandeiraId != null) {
            // Bandeira bandeira = new Bandeira();
            // bandeira.setId(Long.parseLong(bandeiraId));
            dto.setBandeira(bandeiraId);
        }

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
        // Lógica para definir a view após a persistência do cartão de crédito
    }
}
