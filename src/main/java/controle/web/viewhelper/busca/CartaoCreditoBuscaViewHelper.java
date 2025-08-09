package controle.web.viewhelper.busca;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.CartaoCreditoPersistenciaDTO;
import controle.web.viewhelper.IViewHelper;

public class CartaoCreditoBuscaViewHelper implements IViewHelper {

    @Override
    public CartaoCreditoPersistenciaDTO getDTO(HttpServletRequest request) {
        CartaoCreditoPersistenciaDTO cartaoDTO = new CartaoCreditoPersistenciaDTO();
        String txtCartaoId = request.getParameter("txtIdCartao");
        if (txtCartaoId != null && !txtCartaoId.isEmpty()) {
            cartaoDTO.setId(Long.parseLong(txtCartaoId));
        }
        return cartaoDTO;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // Lógica para definir a view após a busca do cartão de crédito
    }
}
