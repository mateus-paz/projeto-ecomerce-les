package controle.web.viewhelper.exclusao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.EnderecoPersistenciaDTO;
import controle.web.viewhelper.IViewHelper;

public class EnderecoExclusaoViewHelper implements IViewHelper {

    @Override
    public EnderecoPersistenciaDTO getDTO(HttpServletRequest request) {
        EnderecoPersistenciaDTO enderecoPersistenciaDTO = new EnderecoPersistenciaDTO();
        String idEndereco = request.getParameter("txtEnderecoId");
        if (idEndereco != null && !idEndereco.isEmpty()) {
            enderecoPersistenciaDTO.setId(Long.parseLong(idEndereco));
        }
        return enderecoPersistenciaDTO;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // Lógica para definir a view após a exclusão do endereço
    }
}
