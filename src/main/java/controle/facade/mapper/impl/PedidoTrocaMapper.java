package controle.facade.mapper.impl;

import java.util.stream.Collectors;

import controle.facade.dto.input.persistencia.InputPersistenciaDTO;
import controle.facade.dto.input.persistencia.impl.PedidoTrocaPersistenciaDTO;
import controle.facade.dto.output.OutputDTO;
import controle.facade.dto.output.impl.ItemTrocaOutputDTO;
import controle.facade.dto.output.impl.PedidoOutputDTO;
import controle.facade.dto.output.impl.PedidoTrocaOutputDTO;
import controle.facade.mapper.IMapper;
import dominio.EntidadeDominio;
import dominio.venda.PedidoTroca;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PedidoTrocaMapper implements IMapper {

    private final PedidoMapper pedidoMapper;
    private final ItemTrocaMapper itemTrocaMapper;

    @Override
    public EntidadeDominio toEntidade(InputPersistenciaDTO dto) {
        PedidoTrocaPersistenciaDTO pedidoTrocaDTO = (PedidoTrocaPersistenciaDTO) dto;
        PedidoTroca pedidoTroca = PedidoTroca.builder()
                .pedido(pedidoTrocaDTO.getPedido() != null
                        ? (dominio.venda.Pedido) pedidoMapper.toEntidade(pedidoTrocaDTO.getPedido())
                        : null)
                .build();

        if (pedidoTrocaDTO.getItens() != null) {
            pedidoTroca.setItens(pedidoTrocaDTO.getItens().stream()
                    .map(itemTrocaMapper::toEntidade)
                    .map(e -> (dominio.venda.ItemTroca) e)
                    .collect(Collectors.toList()));
        }

        return pedidoTroca;
    }

    @Override
    public OutputDTO toDTO(EntidadeDominio entidade) {
        PedidoTroca pedidoTroca = (PedidoTroca) entidade;
        PedidoTrocaOutputDTO pedidoTrocaDTO = new PedidoTrocaOutputDTO();
        pedidoTrocaDTO.setId(pedidoTroca.getId());
        pedidoTrocaDTO.setPedido(pedidoTroca.getPedido() != null
                ? (PedidoOutputDTO) pedidoMapper.toDTO(pedidoTroca.getPedido())
                : null);
        // pedidoTrocaDTO
        // .setStatus(pedidoTroca.getCupom() != null ?
        // pedidoTroca.getCupom().getTpCupom().getDescricao() : null); // Assuming
        // status
        // is
        // derived
        // from
        // cupom
        // type

        if (pedidoTroca.getItens() != null) {
            pedidoTrocaDTO.setItens(pedidoTroca.getItens().stream()
                    .map(itemTrocaMapper::toDTO)
                    .map(e -> (ItemTrocaOutputDTO) e)
                    .collect(Collectors.toList()));
        }

        return pedidoTrocaDTO;
    }

}
