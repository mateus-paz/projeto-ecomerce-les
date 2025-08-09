package controle.web.viewhelper.exclusao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.ItemEstoquePersistenciaDTO;
import controle.web.viewhelper.IViewHelper;

public class ItemEstoqueExclusaoViewHelper implements IViewHelper {

    @Override
    public ItemEstoquePersistenciaDTO getDTO(HttpServletRequest request) {
        ItemEstoquePersistenciaDTO itemDTO = new ItemEstoquePersistenciaDTO();
        String idItemParam = request.getParameter("idItem");
        if (idItemParam != null && !idItemParam.isEmpty()) {
            itemDTO.setId(Long.parseLong(idItemParam));
        }
        return itemDTO;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // Lógica para definir a view após a exclusão do item de estoque
    }
}
