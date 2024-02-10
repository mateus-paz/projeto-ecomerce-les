package dominio.venda;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoCupom {
    TROCA("Troca", 1), PROMOCIONAL("Promocional", 2);

    private String descricao;
    private int valor;

}