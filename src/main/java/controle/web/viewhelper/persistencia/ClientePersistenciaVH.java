package controle.web.viewhelper.persistencia;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.ClientePersistenciaDTO;
import controle.facade.dto.input.persistencia.impl.EnderecoPersistenciaDTO;
import controle.facade.dto.input.persistencia.impl.TelefonePersistenciaDTO;
import controle.facade.dto.input.persistencia.impl.UsuarioPersistenciaDTO;
import controle.web.viewhelper.AbstractViewHelper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ClientePersistenciaVH extends AbstractViewHelper {
    private final UsuarioPersistenciaVH usuarioVH;
    private final TelefonePersistenciaVH telefoneVH;
    private final EnderecoPersistenciaVH enderecoVH;

    @Override
    public InputDTO getDTO(HttpServletRequest request) {
        UsuarioPersistenciaDTO usuario = (UsuarioPersistenciaDTO) usuarioVH.getDTO(request);
        TelefonePersistenciaDTO telefone = (TelefonePersistenciaDTO) telefoneVH.getDTO(request);
        EnderecoPersistenciaDTO endereco = (EnderecoPersistenciaDTO) enderecoVH.getDTO(request);
        ClientePersistenciaDTO cliente = new ClientePersistenciaDTO();
        cliente.setId(super.getId(request));
        cliente.setNome(super.getParameter(request, "txtNome"));
        cliente.setDtNascimento(super.getParameter(request, "txtDtNasc"));
        cliente.setCpf(super.getParameter(request, "txtCPF"));
        cliente.setGenero(super.getParameter(request, "cbbGenero"));
        cliente.setUsuario(usuario);
        cliente.setTelefone(telefone);
        // cliente.setEnderecoResidencial(endereco);
        // Boolean salvarComoEnderecoCobranca = Boolean.valueOf(null);
        // if (salvarComoEnderecoCobranca) {
        // cliente.setEnderecoCobranca(endereco);
        // }
        // Boolean salvarComoEnderecoEntrega = Boolean.valueOf(null);
        // if (salvarComoEnderecoEntrega) {
        // cliente.setEnderecoEntrega(endereco);
        // }
        return cliente;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // Lógica para definir a view após a persistência do cliente
    }
}
