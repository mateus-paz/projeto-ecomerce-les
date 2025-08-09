package dominio.livro;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GrupoPrecificacao {
    Grupo1("Grupo 1", 1), Grupo2("Grupo 2", 2), Grupo3("Grupo 3", 3);

    private String descricao;
    private int valor;

    public static GrupoPrecificacao obterPorDescricao(String descricao) {
        for (GrupoPrecificacao grupo : GrupoPrecificacao.values()) {
            if (grupo.getDescricao().equalsIgnoreCase(descricao)) {
                return grupo;
            }
        }
        throw new IllegalArgumentException("Grupo de precificação inválido: " + descricao);
    }
}