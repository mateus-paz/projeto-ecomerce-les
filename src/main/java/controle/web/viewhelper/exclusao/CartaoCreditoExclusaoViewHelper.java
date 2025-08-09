package controle.web.viewhelper.exclusao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.CartaoCreditoPersistenciaDTO;
import controle.web.viewhelper.IViewHelper;

public class CartaoCreditoExclusaoViewHelper implements IViewHelper {

    @Override
    public CartaoCreditoPersistenciaDTO getDTO(HttpServletRequest request) {
        CartaoCreditoPersistenciaDTO cartaoDTO = new CartaoCreditoPersistenciaDTO();
        String txtCartaoId = request.getParameter("txtIdCartao");
        if (txtCartaoId != null && !txtCartaoId.isEmpty()) {
            cartaoDTO.setId(Long.parseLong(txtCartaoId));
        }
        Object obj = new Object();
        if (obj instanceof CartaoCreditoPersistenciaDTO) {
            cartaoDTO = (CartaoCreditoPersistenciaDTO) obj;
        }
        return cartaoDTO;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // Lógica para definir a view após a exclusão do cartão de crédito
    }
}
