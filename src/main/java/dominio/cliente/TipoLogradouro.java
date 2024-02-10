package dominio.cliente;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoLogradouro {
    RUA("Rua", 1), AVENIDA("Avenida", 2), OUTRO("Outro", 3);

    private String descricao;
    private int valor;

}