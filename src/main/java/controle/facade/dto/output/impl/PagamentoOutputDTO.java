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
public class PagamentoOutputDTO implements OutputDTO {
    private Integer id;
    private List<CartaoCompraOutputDTO> cartoes;
    private List<CupomOutputDTO> cupons;
    private BigDecimal totalCompra;
    private BigDecimal totalAlocado;
    private CartaoCompraOutputDTO novoCartao;
    private CupomOutputDTO novoCupom;
    private CarrinhoOutputDTO carrinho;
    private EnderecoEntregaOutputDTO endereco;
}
