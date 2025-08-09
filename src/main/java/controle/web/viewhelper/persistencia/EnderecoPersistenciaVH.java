package controle.web.viewhelper.persistencia;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.EnderecoPersistenciaDTO;
import controle.web.viewhelper.AbstractViewHelper;

public class EnderecoPersistenciaVH extends AbstractViewHelper {

    @Override
    public InputDTO getDTO(HttpServletRequest request) {
        EnderecoPersistenciaDTO enderecoPersistenciaDTO = new EnderecoPersistenciaDTO();
        enderecoPersistenciaDTO.setId(super.getId(request));
        enderecoPersistenciaDTO.setNomeIdentificacao(super.getParameter(request, "txtNomeIdentificacao"));
        enderecoPersistenciaDTO.setCep(super.getParameter(request, "txtCep"));
        enderecoPersistenciaDTO.setBairro(super.getParameter(request, "txtBairro"));
        enderecoPersistenciaDTO.setNumero(super.getParameter(request, "txtNumero"));
        enderecoPersistenciaDTO.setLogradouro(super.getParameter(request, "txtLogradouro"));
        enderecoPersistenciaDTO.setComplemento(super.getParameter(request, "txtComplemento"));
        // enderecoPersistenciaDTO.setNomeCidade(super.getParameter(request,
        // "txtCidade"));
        // enderecoPersistenciaDTO.setNomeEstado(request.getParameter("txtEstado"));
        // enderecoPersistenciaDTO.setNomePais(request.getParameter("txtPais"));
        // enderecoPersistenciaDTO.setTipoResidencia(request.getParameter("cbbTpResidencia"));
        // enderecoPersistenciaDTO.setTipoLogradouro(request.getParameter("cbbTpLogradouro"));
        return enderecoPersistenciaDTO;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // Lógica para definir a view após a persistência do endereço
    }
}
