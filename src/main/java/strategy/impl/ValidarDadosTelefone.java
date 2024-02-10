package strategy.impl;

import dominio.EntidadeDominio;
import dominio.cliente.Telefone;

public class ValidarDadosTelefone extends AbstractValidador {

	@Override
	public String processar(EntidadeDominio entidade) {
		Telefone telefone = (Telefone) entidade;
		
		StringBuilder msg = new StringBuilder();
		
		if(isNull(telefone.getTpTelefone())) {
			msg.append("E obrigatorio a escolha de um tipo de telefone; ");
		}
		
		if(isNull(telefone.getDdd())) {
			msg.append("E obrigatorio o cadastro de um DDD; ");
		}
		
		if(isNull(telefone.getNumero())) {
			msg.append("E obrigatorio o cadastro de um numero de telefone; ");
		}else if(telefone.getNumero().length() != 9 && telefone.getNumero().length() != 10) {
		    msg.append("O numero do telefone deve ter 8 ou 9 digitos; ");
		}
		
		if(msg.length()>0) {
			return msg.toString();
		}
		
		return null;
	}

}
