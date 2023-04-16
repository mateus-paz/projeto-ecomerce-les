package dominio.venda;

import java.math.BigDecimal;

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
public class CartaoCompra extends EntidadeDominio {
    private BigDecimal valor;
    private boolean isRegistrar;

    private Pedido pedido;
    private CartaoCredito cartao;

}