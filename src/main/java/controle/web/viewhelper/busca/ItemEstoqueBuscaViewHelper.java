package controle.web.viewhelper.busca;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.ItemEstoquePersistenciaDTO;
import controle.web.viewhelper.IViewHelper;

public class ItemEstoqueBuscaViewHelper implements IViewHelper {

    @Override
    public ItemEstoquePersistenciaDTO getDTO(HttpServletRequest request) {
        ItemEstoquePersistenciaDTO itemDTO = new ItemEstoquePersistenciaDTO();
        String operacao = request.getParameter("operacao");

        if (operacao != null && operacao.equals("ConsultarPorId")) {
            String idItemParam = request.getParameter("idItem");
            if (idItemParam != null && !idItemParam.isEmpty()) {
                itemDTO.setId(Long.parseLong(idItemParam));
            }
        } else if (operacao == null) { // Default case if no operation is specified, assume lookup by ID
            String idItemParam = request.getParameter("idItem");
            if (idItemParam != null && !idItemParam.isEmpty()) {
                itemDTO.setId(Long.parseLong(idItemParam));
            }
        }
        return itemDTO;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // Lógica para definir a view após a busca do item de estoque
    }
}
