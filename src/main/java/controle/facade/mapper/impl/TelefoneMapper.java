package controle.facade.mapper.impl;

import controle.facade.dto.input.persistencia.InputPersistenciaDTO;
import controle.facade.dto.input.persistencia.impl.TelefonePersistenciaDTO;
import controle.facade.dto.output.OutputDTO;
import controle.facade.dto.output.impl.TelefoneOutputDTO;
import controle.facade.mapper.IMapper;
import dominio.EntidadeDominio;
import dominio.cliente.Telefone;
import dominio.cliente.TipoTelefone;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TelefoneMapper implements IMapper {

    @Override
    public EntidadeDominio toEntidade(InputPersistenciaDTO dto) {
        TelefonePersistenciaDTO telefoneDTO = (TelefonePersistenciaDTO) dto;
        return Telefone.builder()
                .ddd(telefoneDTO.getDdd())
                .numero(telefoneDTO.getNumero())
                .tpTelefone(TipoTelefone.obterPorDescricao(telefoneDTO.getTpTelefone()))
                .build();
    }

    @Override
    public OutputDTO toDTO(EntidadeDominio entidade) {
        Telefone telefone = (Telefone) entidade;
        TelefoneOutputDTO telefoneDTO = new TelefoneOutputDTO();
        telefoneDTO.setDdd(telefone.getDdd());
        telefoneDTO.setNumero(telefone.getNumero());
        telefoneDTO.setTpTelefone(telefone.getTpTelefone().getDescricao());
        return telefoneDTO;
    }

}
