package controle.web.viewhelper.exclusao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.ItemCarrinhoPersistenciaDTO;
import controle.web.viewhelper.IViewHelper;

public class ItemCarrinhoExclusaoViewHelper implements IViewHelper {

    @Override
    public ItemCarrinhoPersistenciaDTO getDTO(HttpServletRequest request) {
        ItemCarrinhoPersistenciaDTO itemDTO = new ItemCarrinhoPersistenciaDTO();
        String operacao = request.getParameter("operacao");

        if (operacao != null && operacao.equals("Remover")) {
            String idItemParam = request.getParameter("idItem");
            if (idItemParam != null && !idItemParam.isEmpty()) {
                itemDTO.setId(Long.parseLong(idItemParam));
            }
        }
        return itemDTO;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // Lógica para definir a view após a exclusão do item do carrinho
    }
}
