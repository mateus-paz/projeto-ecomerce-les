package dominio.cliente;

public enum TipoLogradouro {
	RUA("Rua", 1), AVENIDA("Avenida", 2), OUTRO("Outro", 3);

    private String descricao;
    private int valor;
    
    TipoLogradouro(String descricao, int valor){
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getValor() {
        return valor;
    }
    
}
