package dominio.venda;

import java.math.BigDecimal;
import java.time.LocalDate;

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
public class Cupom extends EntidadeDominio {
    private String codigo;
    private TipoCupom tpCupom;
    private BigDecimal valor;
    private LocalDate validade;
    private Pedido pedido;
    private Cliente cliente;

}