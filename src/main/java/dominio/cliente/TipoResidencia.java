package dominio.cliente;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoResidencia {
    CASA("Casa", 1), APARTAMENTO("Apartamento", 2), OUTRO("Outro", 3);

    private String descricao;
    private int valor;

}