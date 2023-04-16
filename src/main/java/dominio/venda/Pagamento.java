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
public class Pagamento extends EntidadeDominio {
    private List<CartaoCompra> cartoes;
    private List<Cupom> cupons;
    private BigDecimal totalCompra;
    private BigDecimal totalAlocado;
    private CartaoCompra novoCartao;
    private Cupom novoCupom;
    private Carrinho carrinho;
    private EnderecoEntrega endereco;

    public BigDecimal getTotalCompra() {
        totalCompra = new BigDecimal("0");

        totalCompra = carrinho.getValorTotal().add(endereco.getFrete().getValor());

        return totalCompra;
    }

    public BigDecimal getTotalAlocado() {

        totalAlocado = new BigDecimal("0");

        for (CartaoCompra cartao : cartoes) {
            totalAlocado = totalAlocado.add(cartao.getValor());
        }

        for (Cupom cupom : cupons) {
            totalAlocado = totalAlocado.add(cupom.getValor());
        }

        return totalAlocado;
    }
}