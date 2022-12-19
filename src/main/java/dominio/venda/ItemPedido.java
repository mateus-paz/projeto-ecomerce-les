package dominio.venda;

import java.math.BigDecimal;

import dominio.livro.Livro;

public class ItemPedido extends Item {
    private Pedido pedido;
    
    public ItemPedido() {
        super(null, 0, null);
    }
    
    public ItemPedido(Livro livro, int quantidade, BigDecimal valorVenda, Pedido pedido) {
        super(livro, quantidade, valorVenda);
        this.pedido = pedido;
    }
    
    public ItemPedido(Livro livro, int quantidade, BigDecimal valorVenda) {
        super(livro, quantidade, valorVenda);
    }
    
    public Pedido getPedido() {
        return pedido;
    }
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

}
