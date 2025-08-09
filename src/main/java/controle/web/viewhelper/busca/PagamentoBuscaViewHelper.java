package controle.web.viewhelper.busca;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.PagamentoPersistenciaDTO;
import controle.web.viewhelper.IViewHelper;

public class PagamentoBuscaViewHelper implements IViewHelper {

    @Override
    public PagamentoPersistenciaDTO getDTO(HttpServletRequest request) {
        PagamentoPersistenciaDTO pagamentoDTO = new PagamentoPersistenciaDTO();
        // For search, typically you'd get an ID or other search parameters
        // The original VH uses session attributes for the payment object, not direct
        // request params for search.
        // Assuming a simple ID-based lookup for 'Consultar' operation if needed.
        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            pagamentoDTO.setId(Long.parseLong(idParam));
        }
        return pagamentoDTO;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // Lógica para definir a view após a busca do pagamento
    }
}
