package controle.facade.dto.output.impl;

import java.util.List;

import controle.facade.dto.output.OutputDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstoqueOutputDTO implements OutputDTO {
    private Integer id;
    private String valorBusca;
    private List<ItemEstoqueOutputDTO> itens;
    private List<String> parametros;
}
