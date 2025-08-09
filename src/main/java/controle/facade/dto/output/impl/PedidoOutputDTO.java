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
public class PedidoOutputDTO implements OutputDTO {
    private Integer id;
    private BigDecimal valorTotal;
    private ClienteOutputDTO cliente;
    private CarrinhoOutputDTO carrinho;
    private EnderecoEntregaOutputDTO endereco;
    private PedidoTrocaOutputDTO troca;
    private String status;
    private List<CartaoCompraOutputDTO> cartoes;
    private List<CupomOutputDTO> cupons;
    private List<ItemPedidoOutputDTO> itens;
}
