package controle.facade.dto.output.impl;

import java.math.BigDecimal;

import controle.facade.dto.output.OutputDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartaoCompraOutputDTO implements OutputDTO {
    private Integer id;
    private BigDecimal valor;
    private boolean isRegistrar;
    private PedidoOutputDTO pedido;
    private CartaoCreditoOutputDTO cartao;
}
