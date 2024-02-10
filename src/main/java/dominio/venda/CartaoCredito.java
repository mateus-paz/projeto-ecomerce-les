package dominio.venda;

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
public class CartaoCredito extends EntidadeDominio {
    private String nomeIdentificacao;
    private String dataValidade;
    private String titular;
    private String numero;
    private String cvv;

    private Bandeira bandeira;
    private Cliente cliente;

}