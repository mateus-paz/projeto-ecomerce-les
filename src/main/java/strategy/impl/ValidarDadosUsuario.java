package strategy.impl;

import dao.impl.UsuarioDAO;
import dominio.EntidadeDominio;
import dominio.cliente.Usuario;

public class ValidarDadosUsuario extends AbstractValidador {
	
	@Override
	public String processar(EntidadeDominio entidade) {
		Usuario usuario = (Usuario) entidade;
		
		UsuarioDAO usrDao = new UsuarioDAO();
		
		if(usuario.getId() != 0)
    		if(!usrDao.confirmarLogin(usuario)) {
    		    sb.append("Senha atual incorreta; ");
    		}
		
		if(!usuario.getNovaSenha().equals(usuario.getConfirmarSenha())) {
			sb.append("A nova senha e sua confirmação devem ser iguais; ");
		}
		
		String senha = usuario.getNovaSenha();
		
		if(senha.matches("^(.{0,7}|[^0-9]*|[^A-Z]*|[^a-z]*|[a-zA-Z0-9]*)$")) {
			sb.append("A senha deve conter no minimo: 1 letra maiuscula, 1 letra minuscula, 1 numero e 1 caracter especial, "
					+ "alem de ter um tamanho minimo correspondente a 8 digitos; ");
		}
					
		if(sb.length()>0) {
			return sb.toString();
		}
		
		return null;
	}

}
