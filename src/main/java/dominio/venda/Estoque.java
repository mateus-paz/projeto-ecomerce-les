package dominio.venda;

import java.util.ArrayList;
import java.util.List;

import dominio.EntidadeDominio;

public class Estoque extends EntidadeDominio {
    private String valorBusca;
    
    private List<ItemEstoque> itens = new ArrayList<>();
    private List<String> parametros = new ArrayList<>();
    
    public Estoque() {}

    public Estoque(List<String> parametros, String valorBusca) {
        super();
        this.parametros = parametros;
        this.valorBusca = valorBusca;
    }

    public List<String> getParametros() {
        return parametros;
    }

    public void setParametros(List<String> parametros) {
        this.parametros = parametros;
    }

    public String getValorBusca() {
        return valorBusca;
    }

    public void setValorBusca(String valorBusca) {
        this.valorBusca = valorBusca;
    }

    public List<ItemEstoque> getItens() {
        return itens;
    }

    public void setItens(List<ItemEstoque> itens) {
        this.itens = itens;
    }
    
}
