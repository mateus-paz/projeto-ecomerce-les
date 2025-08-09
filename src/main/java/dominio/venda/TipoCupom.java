package dominio.venda;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoCupom {
    TROCA("Troca", 1), PROMOCIONAL("Promocional", 2);

    private String descricao;
    private int valor;

    public static TipoCupom obterPorDescricao(String descricao) {
        for (TipoCupom tipo : TipoCupom.values()) {
            if (tipo.getDescricao().equalsIgnoreCase(descricao)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo de cupom inválido: " + descricao);
    }
}