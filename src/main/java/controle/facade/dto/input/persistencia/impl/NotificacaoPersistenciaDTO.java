package controle.facade.dto.input.persistencia.impl;

import controle.facade.dto.input.persistencia.InputPersistenciaDTO;
import dominio.EntidadeDominio;
import dominio.cliente.Notificacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class NotificacaoPersistenciaDTO implements InputPersistenciaDTO {
    private Integer id;
    private String mensagem;
    private UsuarioPersistenciaDTO usuario;

    @Override
    public Class<? extends EntidadeDominio> getDominio() {
        return Notificacao.class;
    }
}