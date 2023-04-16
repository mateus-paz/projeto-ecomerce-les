package dominio.venda;

import dominio.EntidadeDominio;
import dominio.cliente.Endereco;
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
public class EnderecoEntrega extends EntidadeDominio {
    private boolean salvar;

    private Endereco endereco;
    private Frete frete;

}