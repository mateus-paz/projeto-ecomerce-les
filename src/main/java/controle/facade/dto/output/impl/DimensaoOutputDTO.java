package controle.facade.dto.output.impl;

import controle.facade.dto.output.OutputDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DimensaoOutputDTO implements OutputDTO {
    private Integer id;
    private double altura;
    private double largura;
    private double peso;
    private double profundidade;
}
