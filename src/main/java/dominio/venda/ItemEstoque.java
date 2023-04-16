package dominio.venda;

import java.math.BigDecimal;

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
public class ItemEstoque extends Item {
    private BigDecimal precoCusto;
    private boolean ativo;
    private String justificativaStatus;

    private CategoriaStatus catStatus;

}