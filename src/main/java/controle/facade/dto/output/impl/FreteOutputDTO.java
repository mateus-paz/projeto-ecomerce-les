package controle.facade.dto.output.impl;

import java.math.BigDecimal;

import controle.facade.dto.output.OutputDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FreteOutputDTO implements OutputDTO {
    private Integer id;
    private int prazo;
    private BigDecimal valor;
    private EnderecoEntregaOutputDTO endereco;
}
