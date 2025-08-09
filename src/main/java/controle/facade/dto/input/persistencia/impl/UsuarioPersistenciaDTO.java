package controle.facade.dto.input.persistencia.impl;

import controle.facade.dto.input.persistencia.InputPersistenciaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import dominio.EntidadeDominio;
import dominio.cliente.Usuario;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioPersistenciaDTO implements InputPersistenciaDTO {
    private Integer id;
    private boolean isAdmin;
    private String email;
    private String senha;
    private String novaSenha;
    private String confirmarSenha;
    private ClientePersistenciaDTO cliente;
    private List<NotificacaoPersistenciaDTO> notificacoes;

    @Override
    public Class<? extends EntidadeDominio> getDominio() {
        return Usuario.class;
    }
}