package dominio.venda;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Bandeira {
    VISA("Visa", 1), MASTERCARD("MasterCard", 2);

    private String descricao;
    private int valor;

}