package dominio.cliente;

import dominio.EntidadeDominio;

public class Notificacao extends EntidadeDominio {
    private String mensagem;
    private boolean isLida;
    private Usuario usuario;
    
    public Notificacao(String mensagem, Usuario usuario) {
        super();
        this.mensagem = mensagem;
        this.usuario = usuario;
    }
    
    public Notificacao(String mensagem, boolean isLida, Usuario usuario) {
        super();
        this.mensagem = mensagem;
        this.isLida = isLida;
        this.usuario = usuario;
    }

    public String getMensagem() {
        return mensagem;
    }
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    public boolean isLida() {
        return isLida;
    }
    public void setLida(boolean isLida) {
        this.isLida = isLida;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
 
}
