package dominio.venda;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusPedido {
    PROCESSAMENTO("Em Processamento", 1), TRANSITO("Em Trânsito", 2), ENTREGUE("Entregue", 3),
    TROCA("Em Troca", 4), AUTORIZADA("Troca Autorizada", 5), RECUSADA("Troca Recusada", 6),
    TROCADO("Troca Finalizada", 7);

    private String descricao;
    private int valor;

}