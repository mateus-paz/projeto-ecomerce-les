package controle.facade.mapper.impl;

import java.math.BigDecimal;

import controle.facade.dto.input.persistencia.InputPersistenciaDTO;
import controle.facade.dto.input.persistencia.impl.CartaoCompraPersistenciaDTO;
import controle.facade.dto.output.OutputDTO;
import controle.facade.dto.output.impl.CartaoCompraOutputDTO;
import controle.facade.mapper.IMapper;
import dominio.EntidadeDominio;
import dominio.venda.CartaoCompra;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CartaoCompraMapper implements IMapper {

    private final CartaoCreditoMapper cartaoCreditoMapper;

    @Override
    public EntidadeDominio toEntidade(InputPersistenciaDTO dto) {
        CartaoCompraPersistenciaDTO cartaoCompraDTO = (CartaoCompraPersistenciaDTO) dto;
        CartaoCompra cartaoCompra = CartaoCompra.builder()
                .valor(BigDecimal.ZERO) // Not available in DTO, setting default
                .isRegistrar(false) // Not available in DTO, setting default
                .cartao(cartaoCompraDTO != null
                        ? (dominio.venda.CartaoCredito) cartaoCreditoMapper.toEntidade(cartaoCompraDTO)
                        : null)
                .build();
        return cartaoCompra;
    }

    @Override
    public OutputDTO toDTO(EntidadeDominio entidade) {
        CartaoCompra cartaoCompra = (CartaoCompra) entidade;
        // Assuming CartaoCompraOutputDTO is essentially
        // CartaoCreditoOutputDTO
        // and we are mapping the nested CartaoCredito from CartaoCompra entity
        return cartaoCompra.getCartao() != null
                ? (CartaoCompraOutputDTO) cartaoCreditoMapper
                        .toDTO(cartaoCompra.getCartao())
                : null;
    }

}
