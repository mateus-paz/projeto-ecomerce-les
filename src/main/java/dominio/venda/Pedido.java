package dominio.venda;

import java.math.BigDecimal;
import java.util.List;

import dominio.EntidadeDominio;
import dominio.cliente.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pedido extends EntidadeDominio {
    private BigDecimal valorTotal;

    private Cliente cliente;
    private Carrinho carrinho;
    private EnderecoEntrega endereco;
    private PedidoTroca troca;
    private StatusPedido status;

    private List<CartaoCompra> cartoes;
    private List<Cupom> cupons;
    private List<ItemPedido> itens;

    public BigDecimal getValorTotal() {
        if (valorTotal.doubleValue() != 0)
            return valorTotal;

        for (ItemPedido item : itens)
            valorTotal = valorTotal.add(item.getValorVenda().multiply(new BigDecimal(item.getQuantidade())));

        return valorTotal;
    }
}