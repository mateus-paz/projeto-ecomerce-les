package controle.facade.dto.output.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemEstoqueOutputDTO extends ItemOutputDTO {
    private BigDecimal precoCusto;
    private boolean ativo;
    private String justificativaStatus;
    private String catStatus;
}
