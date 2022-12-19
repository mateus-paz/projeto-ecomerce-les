package dominio.venda;

import java.math.BigDecimal;

import dominio.livro.Livro;

public class ItemTroca extends Item {
    private PedidoTroca pedido;
    
    public ItemTroca(Livro livro, int quantidade) {
        super(livro, quantidade, null);
        
    }
    
    public ItemTroca(Livro livro, int quantidade, BigDecimal valorVenda) {
        super(livro, quantidade, valorVenda);
    }

    public PedidoTroca getPedido() {
        return pedido;
    }

    public void setPedido(PedidoTroca pedido) {
        this.pedido = pedido;
    }

}
