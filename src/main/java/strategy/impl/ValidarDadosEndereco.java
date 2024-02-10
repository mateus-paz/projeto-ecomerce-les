package strategy.impl;

import dominio.EntidadeDominio;
import dominio.cliente.Endereco;
import dominio.venda.EnderecoEntrega;

public class ValidarDadosEndereco extends AbstractValidador{

	@Override
	public String processar(EntidadeDominio entidade) {
	    Endereco endereco = null;
	    String nmClass = EnderecoEntrega.class.getName();
	    
	    if(entidade.getClass().getName().equals(nmClass)) {
	        EnderecoEntrega endEntrega = (EnderecoEntrega) entidade;
	        endereco = endEntrega.getEndereco();
	    }else {
	        endereco = (Endereco) entidade;
	    }
	        
		StringBuilder msg = new StringBuilder();
		
		if(isNull(endereco.getTpResidencia())) {
			msg.append("E obrigatorio o cadastro de um Tipo de Residencia \n");
		}
		
		if(isNull(endereco.getTpLogradouro())) {
			msg.append("E obrigatorio o cadastro de um Tipo de Logradouro \n");
		}
		
		if(isNull(endereco.getLogradouro())) {
			msg.append("E obrigatorio o cadastro de um Logradouro \n");
		}
		
		if(isNull(endereco.getNumero())) {
			msg.append("E obrigatorio o cadastro de um numero \n");
		}
		
		if(isNull(endereco.getBairro())) {
			msg.append("E obrigatorio o cadastro de um bairro \n");
		}
		
		if(isNull(endereco.getCep())) {
			msg.append("E obrigatorio o cadastro de um CEP \n");
		}
		
		if(endereco.getCep().length() != 10) {
            msg.append("O cep cadastrado deve, obrigatoriamente, ter 8 digitos \n");
        }
		
		if(isNull(endereco.getCidade())) {
			msg.append("E obrigatorio o cadastro de uma cidade \n");
		}
		
		if(isNull(endereco.getCidade().getEstado())) {
			msg.append("E obrigatorio o cadastro de um estado \n");
		}
		
		if(isNull(endereco.getCidade().getEstado().getPais())) {
			msg.append("E obrigatorio o cadastro de um pais \n");
		}
		
		if(msg.length()>0) {
			return msg.toString();
		}
		
		return null;
	}

}
