package controle.facade.dto.output.impl;

import java.math.BigDecimal;
import java.util.List;

import controle.facade.dto.output.OutputDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoTrocaOutputDTO implements OutputDTO {
    private Integer id;
    private BigDecimal valorTotal;
    private PedidoOutputDTO pedido;
    private CupomOutputDTO cupom;
    private List<ItemTrocaOutputDTO> itens;
}
