package dominio.venda;

import java.math.BigDecimal;

import dominio.EntidadeDominio;

public class CartaoCompra extends EntidadeDominio {
    private BigDecimal valor;
    private boolean isRegistrar = false;
    
    private Pedido pedido;
    private CartaoCredito cartao;
    
    public CartaoCompra() {}
    
    public CartaoCompra(CartaoCredito cartao, BigDecimal valor) {
        super();
        this.cartao = cartao;
        this.valor = valor;
        
    }
    public CartaoCredito getCartao() {
        return cartao;
    }
    public void setCartao(CartaoCredito cartao) {
        this.cartao = cartao;
    }
    public BigDecimal getValor() {
        return valor;
    }
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    public Pedido getPedido() {
        return pedido;
    }
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    public boolean isRegistrar() {
        return isRegistrar;
    }
    public void setRegistrar(boolean isRegistrar) {
        this.isRegistrar = isRegistrar;
    }
    
    
    
}
