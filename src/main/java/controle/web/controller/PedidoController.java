package controle.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.web.AlterarCommand;
import controle.web.ConsultarCommand;
import controle.web.ConsultarPorIdCommand;
import controle.web.ICommand;
import controle.web.SalvarCommand;
import controle.web.vh.impl.PedidoVH;
import dominio.venda.Pedido;

@WebServlet(urlPatterns = { "/cli-finalizar-pedido", "/cli-pedidos", "/cli-pedido-detalhes", "/adm-pedidos",
		"/adm-pedido-detalhes" })
public class PedidoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestDispatcher rd = null;

	protected void service(HttpServletRequest request, HttpServletResponse response) {

		PedidoVH pedidoVh = new PedidoVH();
		Pedido pedido = (Pedido) pedidoVh.getEntidade(request);

		if (request.getRequestURI().equals("/EcomerceLivroLES/cli-finalizar-pedido")) {

			ICommand cmd = new SalvarCommand();
			String retorno = (String) cmd.executar(pedido);

			if (retorno != null) {
				request.setAttribute("mensagemErro", retorno);
			}

			pedidoVh.setEntidade(response, request, pedido);
			request.getSession().setAttribute("pedido", pedido);

			rd = request.getRequestDispatcher("/cli_pedido_detalhes.jsp");

		} else if (request.getRequestURI().equals("/EcomerceLivroLES/cli-pedidos")) {

			ICommand cmd = new ConsultarCommand();

			@SuppressWarnings("unchecked")
			List<Pedido> pedidos = (List<Pedido>) cmd.executar(pedido);

			request.getSession().setAttribute("pedidos", pedidos);

			rd = request.getRequestDispatcher("/cli_pedidos.jsp");

		} else if (request.getRequestURI().equals("/EcomerceLivroLES/cli-pedido-detalhes")) {

			ICommand cmd = new ConsultarPorIdCommand();

			pedido = (Pedido) cmd.executar(pedido);

			pedidoVh.setEntidade(response, request, pedido);

			rd = request.getRequestDispatcher("/cli_pedido_detalhes.jsp");

		} else if (request.getRequestURI().equals("/EcomerceLivroLES/adm-pedidos")) {

			ICommand cmd = new ConsultarCommand();

			@SuppressWarnings("unchecked")
			List<Pedido> pedidos = (List<Pedido>) cmd.executar(pedido);

			request.getSession().setAttribute("pedidos", pedidos);

			rd = request.getRequestDispatcher("/adm_pedidos.jsp");

		} else if (request.getRequestURI().equals("/EcomerceLivroLES/adm-pedido-detalhes")) {
			String operacao = request.getParameter("operacao");

			ICommand cmd = null;

			if (operacao.equals("Alterar")) {
				cmd = new AlterarCommand();
				cmd.executar(pedido);
			} else if (operacao.equals("ConsultarPorId")) {
				cmd = new ConsultarPorIdCommand();
				pedido = (Pedido) cmd.executar(pedido);
			}

			pedidoVh.setEntidade(response, request, pedido);

			rd = request.getRequestDispatcher("/adm_pedido_detalhes.jsp");
		}

		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
