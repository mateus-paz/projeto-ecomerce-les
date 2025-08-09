package dominio.cliente;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Genero {
    MASCULINO("Masculino", 1), FEMININO("Feminino", 2), NAOBINARIO("Não Binário", 3);

    private String descricao;
    private int valor;

    public static Genero obterPorDescricao(String descricao) {
        for (Genero genero : Genero.values()) {
            if (genero.getDescricao().equalsIgnoreCase(descricao)) {
                return genero;
            }
        }
        throw new IllegalArgumentException("Gênero inválido: " + descricao);
    }
}