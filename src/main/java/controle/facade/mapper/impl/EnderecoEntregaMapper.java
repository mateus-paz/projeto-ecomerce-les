package controle.facade.mapper.impl;

import controle.facade.dto.input.persistencia.InputPersistenciaDTO;
import controle.facade.dto.input.persistencia.impl.EnderecoEntregaPersistenciaDTO;
import controle.facade.dto.output.OutputDTO;
import controle.facade.dto.output.impl.EnderecoEntregaOutputDTO;
import controle.facade.dto.output.impl.EnderecoOutputDTO;
import controle.facade.mapper.IMapper;
import dominio.EntidadeDominio;
import dominio.venda.EnderecoEntrega;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EnderecoEntregaMapper implements IMapper {

    private final EnderecoMapper enderecoMapper;

    @Override
    public EntidadeDominio toEntidade(InputPersistenciaDTO dto) {
        EnderecoEntregaPersistenciaDTO enderecoEntregaDTO = (EnderecoEntregaPersistenciaDTO) dto;
        return EnderecoEntrega.builder()
                .salvar(enderecoEntregaDTO.isSalvar())
                .endereco(enderecoEntregaDTO.getEndereco() != null
                        ? (dominio.cliente.Endereco) enderecoMapper.toEntidade(enderecoEntregaDTO.getEndereco())
                        : null)
                .build();
    }

    @Override
    public OutputDTO toDTO(EntidadeDominio entidade) {
        EnderecoEntrega enderecoEntrega = (EnderecoEntrega) entidade;
        EnderecoEntregaOutputDTO enderecoEntregaDTO = new EnderecoEntregaOutputDTO();
        enderecoEntregaDTO.setId(enderecoEntrega.getId());
        enderecoEntregaDTO.setSalvar(enderecoEntrega.isSalvar());
        enderecoEntregaDTO.setEndereco(enderecoEntrega.getEndereco() != null
                ? (EnderecoOutputDTO) enderecoMapper
                        .toDTO(enderecoEntrega.getEndereco())
                : null);
        return enderecoEntregaDTO;
    }
}
