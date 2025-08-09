package controle.facade.mapper.impl;

import controle.facade.dto.input.persistencia.InputPersistenciaDTO;
import controle.facade.dto.input.persistencia.impl.ItemTrocaPersistenciaDTO;
import controle.facade.dto.output.OutputDTO;
import controle.facade.dto.output.impl.ItemTrocaOutputDTO;
import controle.facade.mapper.IMapper;
import dominio.EntidadeDominio;
import dominio.venda.ItemTroca;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ItemTrocaMapper implements IMapper {

    private final LivroMapper livroMapper;

    @Override
    public EntidadeDominio toEntidade(InputPersistenciaDTO dto) {
        ItemTrocaPersistenciaDTO itemTrocaDTO = (ItemTrocaPersistenciaDTO) dto;
        ItemTroca itemTroca = ItemTroca.builder()
                // .quantidade(itemTrocaDTO.getQuantidade())
                // .valorVenda(itemTrocaDTO.getValorVenda())
                .build();
        // Livro and PedidoTroca are not in DTO, so not mapped here
        return itemTroca;
    }

    @Override
    public OutputDTO toDTO(EntidadeDominio entidade) {
        ItemTroca itemTroca = (ItemTroca) entidade;
        ItemTrocaOutputDTO itemTrocaDTO = new ItemTrocaOutputDTO();
        itemTrocaDTO.setId(itemTroca.getId());
        itemTrocaDTO.setQuantidade(itemTroca.getQuantidade());
        itemTrocaDTO.setValorVenda(itemTroca.getValorVenda());
        // Livro and PedidoTroca are not in DTO, so not mapped here
        return itemTrocaDTO;
    }

}
