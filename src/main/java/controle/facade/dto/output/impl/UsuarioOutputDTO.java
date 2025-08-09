package controle.facade.dto.output.impl;

import java.util.List;

import controle.facade.dto.output.OutputDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioOutputDTO implements OutputDTO {
    private Integer id;
    private boolean isAdmin;
    private String email;
    private String senha;
    private String novaSenha;
    private String confirmarSenha;
    private ClienteOutputDTO cliente;
    private List<NotificacaoOutputDTO> notificacoes;
}
