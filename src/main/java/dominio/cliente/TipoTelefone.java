package dominio.cliente;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoTelefone {
    FIXO("Fixo", 1), CELULAR("Celular", 2);

    private String descricao;
    private int valor;

    public static TipoTelefone obterPorDescricao(String descricao) {
        for (TipoTelefone tipo : TipoTelefone.values()) {
            if (tipo.getDescricao().equalsIgnoreCase(descricao)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo de telefone inválido: " + descricao);
    }
}