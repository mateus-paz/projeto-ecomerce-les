package controle.facade.dto.output.impl;

import controle.facade.dto.output.OutputDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoEntregaOutputDTO implements OutputDTO {
    private Integer id;
    private boolean salvar;
    private EnderecoOutputDTO endereco;
    private FreteOutputDTO frete;
}
