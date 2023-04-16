package dominio.cliente;

import dominio.EntidadeDominio;
import dominio.venda.Pedido;
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
public class Endereco extends EntidadeDominio {
    private String logradouro;
    private String numero;
    private String bairro;
    private String cep;
    private String complemento;
    private String nomeIdentificacao;

    private TipoResidencia tpResidencia;
    private TipoLogradouro tpLogradouro;

    private Cidade cidade;
    private Cliente cliente;
    private Pedido pedido;

}