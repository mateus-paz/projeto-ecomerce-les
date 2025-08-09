package controle.facade.mapper.impl;

import controle.facade.dto.input.persistencia.InputPersistenciaDTO;
import controle.facade.dto.input.persistencia.impl.NotificacaoPersistenciaDTO;
import controle.facade.dto.output.OutputDTO;
import controle.facade.dto.output.impl.NotificacaoOutputDTO;
import controle.facade.mapper.IMapper;
import dominio.EntidadeDominio;
import dominio.cliente.Notificacao;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NotificacaoMapper implements IMapper {

    private final UsuarioMapper usuarioMapper;

    @Override
    public EntidadeDominio toEntidade(InputPersistenciaDTO dto) {
        NotificacaoPersistenciaDTO notificacaoDTO = (NotificacaoPersistenciaDTO) dto;
        return Notificacao.builder()
                .mensagem(notificacaoDTO.getMensagem())
                .usuario(null) // Avoid circular dependency
                .build();
    }

    @Override
    public OutputDTO toDTO(EntidadeDominio entidade) {
        Notificacao notificacao = (Notificacao) entidade;
        NotificacaoOutputDTO notificacaoDTO = new NotificacaoOutputDTO();
        notificacaoDTO.setMensagem(notificacao.getMensagem());
        // notificacaoDTO.setLida(false); // Assuming 'lida' is not directly in entity,
        // setting default
        return notificacaoDTO;
    }

}
