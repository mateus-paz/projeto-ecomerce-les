package controle.facade.mapper.impl;

import controle.facade.dto.input.persistencia.InputPersistenciaDTO;
import controle.facade.dto.input.persistencia.impl.UsuarioPersistenciaDTO;
import controle.facade.dto.output.OutputDTO;
import controle.facade.dto.output.impl.UsuarioOutputDTO;
import controle.facade.mapper.IMapper;
import dominio.EntidadeDominio;
import dominio.cliente.Usuario;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsuarioMapper implements IMapper {

    @Override
    public EntidadeDominio toEntidade(InputPersistenciaDTO dto) {
        UsuarioPersistenciaDTO usuarioDTO = (UsuarioPersistenciaDTO) dto;
        return Usuario.builder()
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .confirmarSenha(usuarioDTO.getConfirmarSenha())
                .build();
    }

    @Override
    public OutputDTO toDTO(EntidadeDominio entidade) {
        Usuario usuario = (Usuario) entidade;
        UsuarioOutputDTO usuarioDTO = new UsuarioOutputDTO();
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setSenha(usuario.getSenha());
        usuarioDTO.setConfirmarSenha(usuario.getConfirmarSenha());
        return usuarioDTO;
    }

}