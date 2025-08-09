package controle.web.viewhelper.busca;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.EnderecoEntregaPersistenciaDTO;
import controle.web.viewhelper.IViewHelper;

public class EnderecoEntregaBuscaViewHelper implements IViewHelper {

    @Override
    public EnderecoEntregaPersistenciaDTO getDTO(HttpServletRequest request) {
        EnderecoEntregaPersistenciaDTO enderecoEntregaPersistenciaDTO = new EnderecoEntregaPersistenciaDTO();
        // For search, typically you'd get an ID or other search parameters
        // The original VH uses session attribute 'enderecoSelecionado' for some
        // operations,
        // which is not directly a request parameter for a new search.
        // Assuming a simple ID-based lookup for 'Consultar' operation.
        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            enderecoEntregaPersistenciaDTO.setId(Long.parseLong(idParam));
        }
        return enderecoEntregaPersistenciaDTO;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // Lógica para definir a view após a busca do endereço de entrega
    }
}
