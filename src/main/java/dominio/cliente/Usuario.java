package dominio.cliente;

import java.util.List;

import dominio.EntidadeDominio;

public class Usuario extends EntidadeDominio {
    private boolean isAdmin;
	private String email;
	private String senha;
	
	private String novaSenha;
	private String confirmarSenha;
	
	private Cliente cliente;
	private List<Notificacao> notificacoes;
	
	public Usuario() {}
	
	public Usuario(String email, String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}
	
	public Usuario(String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public List<Notificacao> getNotificacoes() {
        return notificacoes;
    }

    public void setNotificacoes(List<Notificacao> notificacoes) {
        this.notificacoes = notificacoes;
    }
	
}
