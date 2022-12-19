package dominio.cliente;

public enum TipoResidencia {
	CASA("Casa",1), APARTAMENTO("Apartamento", 2), OUTRO("Outro", 3);
	
    private String descricao;
    private int valor;
    
    TipoResidencia(String descricao, int valor){
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
