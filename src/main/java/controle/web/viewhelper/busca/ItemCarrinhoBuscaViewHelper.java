package controle.web.viewhelper.busca;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.ItemCarrinhoPersistenciaDTO;
import controle.web.viewhelper.IViewHelper;

public class ItemCarrinhoBuscaViewHelper implements IViewHelper {

    @Override
    public ItemCarrinhoPersistenciaDTO getDTO(HttpServletRequest request) {
        // ItemCarrinhoVH does not have a direct search operation for a specific entity.
        // It operates on the current session's cart.
        return new ItemCarrinhoPersistenciaDTO();
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // No specific view setting for search of a cart item.
    }
}
