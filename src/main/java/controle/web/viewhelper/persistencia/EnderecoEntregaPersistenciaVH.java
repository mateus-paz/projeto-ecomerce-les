package controle.web.viewhelper.persistencia;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.EnderecoEntregaPersistenciaDTO;
import controle.web.viewhelper.AbstractViewHelper;
import dominio.cliente.Endereco;

public class EnderecoEntregaPersistenciaVH extends AbstractViewHelper {

    @Override
    public InputDTO getDTO(HttpServletRequest request) {
        EnderecoEntregaPersistenciaDTO dto = new EnderecoEntregaPersistenciaDTO();
        dto.setId(super.getId(request));

        String enderecoId = getParameter(request, "enderecoId");
        if (enderecoId != null) {
            Endereco endereco = new Endereco();
            // endereco.setId(Long.parseLong(enderecoId));
            // dto.setEndereco(endereco);
        }

        String salvarStr = getParameter(request, "chkSalvarEndereco");
        if (salvarStr != null) {
            dto.setSalvar(Boolean.parseBoolean(salvarStr));
        }

        return dto;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // Lógica para definir a view após a persistência do endereço de entrega
    }
}
