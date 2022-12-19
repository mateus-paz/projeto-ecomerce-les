 package strategy.impl;

import dominio.EntidadeDominio;
import dominio.cliente.Cliente;
import dominio.cliente.Endereco;

public class ValidarDadosCliente extends AbstractValidador {

	@Override
	public String processar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		
		if(isNull(cliente.getNome())) {
			sb.append("E obrigatorio o cadastro do Nome; ");
		}

		if(isNull(cliente.getCpf())) {
			sb.append("E obrigatorio o cadastro do CPF; ");
		}else if(cliente.getCpf().length() != 14 ) {
		    sb.append("O Tamanho do CPF deve corresponder a 11 digitos (Considerando apenas numeros); ");
		}
		
		if(isNull(cliente.getDtNascimento())) {
			sb.append("E obrigatorio o cadastro da Data de Nascimento; ");
		}
		
		if(isNull(cliente.getGenero())) {
			sb.append("E obrigatorio a selecao do Genero; ");
		}
		
		ValidarDadosTelefone vTelefone = new ValidarDadosTelefone();
		String msgTelefone = null;
		msgTelefone = vTelefone.processar(cliente.getTelefone());
		
		if( msgTelefone != null ){
			sb.append(msgTelefone);
		}
		
		if(cliente.getId() == 0) {
    		ValidarDadosEndereco vEndereco = new ValidarDadosEndereco();
    		String msgEndereco = null;
    		for(Endereco endereco : cliente.getEnderecos()) {
    			msgEndereco = vEndereco.processar(endereco);
    			if( msgEndereco != null ){
    				sb.append(msgEndereco);
    			}
    		}
    		
    		ValidarDadosUsuario vUsuario = new ValidarDadosUsuario();
    		String msgUsuario = null;
    		msgUsuario = vUsuario.processar(cliente.getUsuario());
    		
    		if( msgUsuario != null ){
    			sb.append(msgUsuario);
    		}
		}
		
		ValidarUnicidadeCliente vUnicidade = new ValidarUnicidadeCliente();
		
		String msgUnicidade = null;
		msgUnicidade = vUnicidade.processar(cliente);
        
        if( msgUnicidade != null ){
            sb.append(msgUnicidade);
        }
		
		
		if(sb.length()>0) {
			return sb.toString();
		}
		
		return null;
	}

}
