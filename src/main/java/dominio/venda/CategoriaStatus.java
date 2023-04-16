package dominio.venda;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CategoriaStatus {
    EM_ESTOQUE("Em estoque", 1), FORA_DE_MERCADO("Fora de Mercado", 2), SEM_ESTOQUE("Sem estoque", 3);

    private String descricao;
    private int valor;

}