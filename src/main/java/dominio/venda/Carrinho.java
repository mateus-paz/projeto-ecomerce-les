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
public class Carrinho extends EntidadeDominio {
    private List<ItemCarrinho> itens;
    private BigDecimal valorTotal;

    public BigDecimal getValorTotal() {
        valorTotal = new BigDecimal("0");

        for (ItemCarrinho i : itens) {
            valorTotal = valorTotal.add(
                    i.getValorVenda().multiply(
                            new BigDecimal(i.getQuantidade())));

        }

        return valorTotal;
    }

}