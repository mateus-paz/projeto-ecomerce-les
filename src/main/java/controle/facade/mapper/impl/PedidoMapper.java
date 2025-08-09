package controle.facade.mapper.impl;

import java.util.stream.Collectors;

import controle.facade.dto.input.persistencia.InputPersistenciaDTO;
import controle.facade.dto.input.persistencia.impl.PedidoPersistenciaDTO;
import controle.facade.dto.output.OutputDTO;
import controle.facade.dto.output.impl.CarrinhoOutputDTO;
import controle.facade.dto.output.impl.CartaoCompraOutputDTO;
import controle.facade.dto.output.impl.ClienteOutputDTO;
import controle.facade.dto.output.impl.CupomOutputDTO;
import controle.facade.dto.output.impl.EnderecoEntregaOutputDTO;
import controle.facade.dto.output.impl.PedidoOutputDTO;
import controle.facade.mapper.IMapper;
import dominio.EntidadeDominio;
import dominio.venda.Pedido;
import dominio.venda.StatusPedido;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PedidoMapper implements IMapper {

    private final ClienteMapper clienteMapper;
    private final CarrinhoMapper carrinhoMapper;
    private final EnderecoEntregaMapper enderecoEntregaMapper;
    private final CartaoCompraMapper cartaoCompraMapper;
    private final CupomMapper cupomMapper;
    // private final ItemPedidoMapper itemPedidoMapper;

    @Override
    public EntidadeDominio toEntidade(InputPersistenciaDTO dto) {
        PedidoPersistenciaDTO pedidoDTO = (PedidoPersistenciaDTO) dto;
        Pedido pedido = Pedido.builder()
                .cliente(pedidoDTO.getCliente() != null
                        ? (dominio.cliente.Cliente) clienteMapper.toEntidade(pedidoDTO.getCliente())
                        : null)
                .carrinho(pedidoDTO.getCarrinho() != null
                        ? (dominio.venda.Carrinho) carrinhoMapper.toEntidade(pedidoDTO.getCarrinho())
                        : null)
                .endereco(pedidoDTO.getEndereco() != null
                        ? (dominio.venda.EnderecoEntrega) enderecoEntregaMapper.toEntidade(pedidoDTO.getEndereco())
                        : null)
                .status(StatusPedido.obterPorDescricao(pedidoDTO.getStatus()))
                .build();

        if (pedidoDTO.getCartoes() != null) {
            pedido.setCartoes(pedidoDTO.getCartoes().stream()
                    .map(cartaoCompraMapper::toEntidade)
                    .map(e -> (dominio.venda.CartaoCompra) e)
                    .collect(Collectors.toList()));
        }
        if (pedidoDTO.getCupons() != null) {
            pedido.setCupons(pedidoDTO.getCupons().stream()
                    .map(cupomMapper::toEntidade)
                    .map(e -> (dominio.venda.Cupom) e)
                    .collect(Collectors.toList()));
        }
        // ItemPedido is not directly in PedidoPersistenciaDTO, assuming it's handled
        // elsewhere or not needed for this DTO

        return pedido;
    }

    @Override
    public OutputDTO toDTO(EntidadeDominio entidade) {
        Pedido pedido = (Pedido) entidade;
        PedidoOutputDTO pedidoDTO = new PedidoOutputDTO();
        pedidoDTO.setId(pedido.getId());
        pedidoDTO.setCliente(pedido.getCliente() != null
                ? (ClienteOutputDTO) clienteMapper.toDTO(pedido.getCliente())
                : null);
        pedidoDTO.setCarrinho(pedido.getCarrinho() != null
                ? (CarrinhoOutputDTO) carrinhoMapper.toDTO(pedido.getCarrinho())
                : null);
        pedidoDTO.setEndereco(pedido.getEndereco() != null
                ? (EnderecoEntregaOutputDTO) enderecoEntregaMapper
                        .toDTO(pedido.getEndereco())
                : null);
        pedidoDTO.setStatus(pedido.getStatus().getDescricao());

        if (pedido.getCartoes() != null) {
            pedidoDTO.setCartoes(pedido.getCartoes().stream()
                    .map(cartaoCompraMapper::toDTO)
                    .map(e -> (CartaoCompraOutputDTO) e)
                    .collect(Collectors.toList()));
        }
        if (pedido.getCupons() != null) {
            pedidoDTO.setCupons(pedido.getCupons().stream()
                    .map(cupomMapper::toDTO)
                    .map(e -> (CupomOutputDTO) e)
                    .collect(Collectors.toList()));
        }

        return pedidoDTO;
    }

}
