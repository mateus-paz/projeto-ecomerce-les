package dominio.venda;

import java.math.BigDecimal;
import java.util.List;

import dominio.EntidadeDominio;
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
public class PedidoTroca extends EntidadeDominio {
    private BigDecimal valorTotal;

    private Pedido pedido;
    private Cupom cupom;

    private List<ItemTroca> itens;

    public BigDecimal getValorTotal() {
        valorTotal = new BigDecimal("0");

        for (ItemTroca item : itens) {
            valorTotal = valorTotal.add(item.getValorVenda().multiply(new BigDecimal(item.getQuantidade())));
        }

        return valorTotal;
    }

}