package dominio.cliente;

import java.util.List;

import dominio.EntidadeDominio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario extends EntidadeDominio {
    private boolean isAdmin;
    private String email;
    private String senha;
    private String novaSenha;
    private String confirmarSenha;

    private Cliente cliente;

    private List<Notificacao> notificacoes;

}