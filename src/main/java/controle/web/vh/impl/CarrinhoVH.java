package controle.web.vh.impl;

import controle.web.vh.IViewHelper;
import dao.impl.ClienteDAO;
import dominio.EntidadeDominio;
import dominio.cliente.Cliente;
import dominio.cliente.Usuario;
import dominio.venda.Carrinho;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CarrinhoVH implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
	    
		Usuario usr = (Usuario) request.getSession().getAttribute("usuario");
		
		ClienteDAO clienteDao = new ClienteDAO();
		Cliente cliente = clienteDao.consultarPorUser(usr);
		
		request.getSession().setAttribute("cliente", cliente);
		Carrinho carrinho = clienteDao.consultarCarrinho(cliente);
		
		return carrinho;
	}

	@Override
	public void setEntidade(HttpServletResponse response, HttpServletRequest request, Object msg) {
	    request.getSession().setAttribute("carrinho", (Carrinho) msg);
	}

}
