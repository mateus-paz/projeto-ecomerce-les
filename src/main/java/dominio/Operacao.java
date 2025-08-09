package dominio;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Operacao {
    SALVAR("salvar"),
    ALTERAR("alterar"),
    EXCLUIR("excluir"),
    CONSULTAR("consultar"),
    CONSULTAR_POR_ID("consultarPorId");

    private final String valor;

    public static Operacao obterPorValor(String valor) {
        for (Operacao operacao : Operacao.values()) {
            if (operacao.getValor().equalsIgnoreCase(valor)) {
                return operacao;
            }
        }
        throw new IllegalArgumentException("Operação inválida: " + valor);
    }
}
