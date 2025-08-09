package dominio.venda;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CategoriaStatus {
    EM_ESTOQUE("Em estoque", 1), FORA_DE_MERCADO("Fora de Mercado", 2), SEM_ESTOQUE("Sem estoque", 3);

    private String descricao;
    private int valor;

    public static CategoriaStatus obterPorDescricao(String descricao) {
        for (CategoriaStatus status : CategoriaStatus.values()) {
            if (status.getDescricao().equalsIgnoreCase(descricao)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Categoria de status inválida: " + descricao);
    }
}