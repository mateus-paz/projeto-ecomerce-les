package controle.facade.dto.output.impl;

import controle.facade.dto.output.OutputDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartaoCreditoOutputDTO implements OutputDTO {
    private Integer id;
    private String nomeIdentificacao;
    private String dataValidade;
    private String titular;
    private String numero;
    private String cvv;
    private String bandeira;
    private ClienteOutputDTO cliente;
}
