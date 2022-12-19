package strategy.impl;

import dao.impl.ClienteDAO;
import dao.impl.UsuarioDAO;
import dominio.EntidadeDominio;
import dominio.cliente.Cliente;

public class ValidarUnicidadeCliente extends AbstractValidador {

    @Override
    public String processar(EntidadeDominio entidade) {
        Cliente cliente = (Cliente) entidade;
        
        ClienteDAO cliDao = new ClienteDAO();
        
        if(cliDao.isCadastradoCpf(cliente)) {
            sb.append("CPF descrito ja esta pertence a outro usuario; ");
            
        }

        UsuarioDAO usrDao = new UsuarioDAO();
        
        if(usrDao.isCadastradoEmail(cliente.getUsuario())) {
            sb.append("Email descrito ja pertence a outro usuario; ");
            
        }
        
        if(sb.length()>0) {
            return sb.toString();
        }
        
        return null;
    }

}
