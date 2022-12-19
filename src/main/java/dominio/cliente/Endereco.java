package dominio.cliente;

import dominio.EntidadeDominio;
import dominio.venda.Pedido;

public class Endereco extends EntidadeDominio {
	
	private String logradouro;
	private String numero;
	private String bairro;
	private String cep;
	private String complemento;
	private String nomeIdentificacao;
	
	private TipoResidencia tpResidencia;
    private TipoLogradouro tpLogradouro;
    
    private Cidade cidade;
	private Cliente cliente;
	private Pedido pedido;
	
	public Endereco() {}
	
	public Endereco(TipoResidencia tpResidencia, TipoLogradouro tpLogradouro, String logradouro, String numero,
            String bairro, String cep, Cidade cidade, String complemento) {
        super();
        this.tpResidencia = tpResidencia;
        this.tpLogradouro = tpLogradouro;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.complemento = complemento;;
    }
	
	public Endereco(TipoResidencia tpResidencia, TipoLogradouro tpLogradouro, String logradouro, String numero,
			String bairro, String cep, Cidade cidade, String nomeIdentificacao, String complemento) {
		super();
		this.tpResidencia = tpResidencia;
		this.tpLogradouro = tpLogradouro;
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.nomeIdentificacao = nomeIdentificacao;
		this.complemento = complemento;

	}
		
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public TipoResidencia getTpResidencia() {
		return tpResidencia;
	}
	public void setTpResidencia(TipoResidencia tpResidencia) {
		this.tpResidencia = tpResidencia;
	}
	public TipoLogradouro getTpLogradouro() {
		return tpLogradouro;
	}
	public void setTpLogradouro(TipoLogradouro tpLogradouro) {
		this.tpLogradouro = tpLogradouro;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	public String getNomeIdentificacao() {
        return nomeIdentificacao;
    }

    public void setNomeIdentificacao(String nomeIdentificacao) {
        this.nomeIdentificacao = nomeIdentificacao;
    }

    public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
	
}
