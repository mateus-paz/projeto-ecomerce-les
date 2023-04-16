package dominio.cliente;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoTelefone {
    FIXO("Fixo", 1), CELULAR("Celular", 2);

    private String descricao;
    private int valor;

}