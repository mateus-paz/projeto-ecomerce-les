package dominio.venda;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Bandeira {
    VISA("Visa", 1), MASTERCARD("MasterCard", 2);

    private String descricao;
    private int valor;

    public static Bandeira obterPorDescricao(String descricao) {
        for (Bandeira bandeira : Bandeira.values()) {
            if (bandeira.getDescricao().equalsIgnoreCase(descricao)) {
                return bandeira;
            }
        }
        throw new IllegalArgumentException("Bandeira inválida: " + descricao);
    }
}