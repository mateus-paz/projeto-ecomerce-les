package controle.web.viewhelper.exclusao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.EnderecoEntregaPersistenciaDTO;
import controle.web.viewhelper.IViewHelper;

public class EnderecoEntregaExclusaoViewHelper implements IViewHelper {

    @Override
    public EnderecoEntregaPersistenciaDTO getDTO(HttpServletRequest request) {
        EnderecoEntregaPersistenciaDTO enderecoEntregaPersistenciaDTO = new EnderecoEntregaPersistenciaDTO();
        String idParam = request.getParameter("id"); // Assuming ID is passed for removal
        if (idParam != null && !idParam.isEmpty()) {
            enderecoEntregaPersistenciaDTO.setId(Long.parseLong(idParam));
        }
        return enderecoEntregaPersistenciaDTO;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // Lógica para definir a view após a exclusão do endereço de entrega
    }
}
