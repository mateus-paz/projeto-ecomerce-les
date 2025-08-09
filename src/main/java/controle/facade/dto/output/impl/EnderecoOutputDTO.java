package controle.facade.dto.output.impl;

import controle.facade.dto.output.OutputDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoOutputDTO implements OutputDTO {
    private Integer id;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cep;
    private String complemento;
    private String nomeIdentificacao;
    private String tpResidencia;
    private String tpLogradouro;
    private CidadeOutputDTO cidade;
    private ClienteOutputDTO cliente;
    private PedidoOutputDTO pedido;
}
