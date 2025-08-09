package controle.facade.dto.input.persistencia.impl;

import controle.facade.dto.input.persistencia.InputPersistenciaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import dominio.EntidadeDominio;
import dominio.venda.CartaoCompra;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartaoCompraPersistenciaDTO implements InputPersistenciaDTO {
    private Integer id;
    private BigDecimal valor;
    private boolean isRegistrar;
    private PedidoPersistenciaDTO pedido;
    private CartaoCreditoPersistenciaDTO cartao;

    @Override
    public Class<? extends EntidadeDominio> getDominio() {
        return CartaoCompra.class;
    }
}