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
import controle.web.vh.impl.ClienteVH;
import dominio.Resultado;
import dominio.cliente.Cliente;
import dominio.venda.Pedido;

@WebServlet(urlPatterns = { "/adm-clientes", "/adm-cliente-detalhes", "/cli-cadastrar", "/cli-perfil", "/cli-alterar" })
public class ClienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		if (request.getRequestURI().equals("/EcomerceLivroLES/adm-clientes")) {

			ClienteVH cliVh = new ClienteVH();
			Cliente cliente = (Cliente) cliVh.getEntidade(request);

			ICommand cmd = new ConsultarCommand();
			@SuppressWarnings("unchecked")
			List<Cliente> clientes = (List<Cliente>) cmd.executar(cliente);

			request.setAttribute("clientes", clientes);

			RequestDispatcher rd = request.getRequestDispatcher("/adm_clientes.jsp");

			try {
				rd.forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (request.getRequestURI().equals("/EcomerceLivroLES/adm-cliente-detalhes")) {

			ClienteVH cliVh = new ClienteVH();
			Cliente cliente = (Cliente) cliVh.getEntidade(request);

			ICommand cmd = new ConsultarPorIdCommand();

			Resultado resultado = cmd.executar(cliente);
			
			cliVh.setEntidade(response, request, resultado);

			Pedido pedido = new Pedido();
			pedido.setCliente(cliente);

			cmd = new ConsultarCommand();

			@SuppressWarnings("unchecked")
			List<Pedido> pedidos = (List<Pedido>) cmd.executar(pedido);
			request.setAttribute("pedidos", pedidos);

			RequestDispatcher rd = request.getRequestDispatcher("/adm_cliente_detalhes.jsp");

			try {
				rd.forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

} else if (request.getRequestURI().equals("/EcomerceLivroLES/cli-cadastrar")) {

			ClienteVH clienteVh = new ClienteVH();
			Cliente cliente = (Cliente) clienteVh.getEntidade(request);

			ICommand cmd = new SalvarCommand();
			String retorno = cmd.executar(cliente).getMensagemErro();

			if (retorno != null) {
				request.getSession().setAttribute("mensagem",
						"Nao foi possivel realizar o Cadastro. Motivos: " + retorno);
			} else {
				request.getSession().setAttribute("mensagem", "Cliente cadastrado com Sucesso");
			}

			response.sendRedirect("login");

		} else if (request.getRequestURI().equals("/EcomerceLivroLES/cli-home")) {

			ClienteVH clienteVh = new ClienteVH();
			Cliente cliente = (Cliente) clienteVh.getEntidade(request);
			
			Resultado resultado = new Resultado();
			resultado.setEntidade(cliente);
			
			clienteVh.setEntidade(response, request, resultado);

		} else if (request.getRequestURI().equals("/EcomerceLivroLES/cli-perfil")) {

			ClienteVH clienteVh = new ClienteVH();

			Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");

			if (cliente == null) {
				cliente = (Cliente) clienteVh.getEntidade(request);
			}

			ICommand cmd = new ConsultarPorIdCommand();
			Resultado resultado = cmd.executar(cliente);
			
			clienteVh.setEntidade(response, request, resultado);

			RequestDispatcher rd = request.getRequestDispatcher("/cli_perfil.jsp");

			try {
				rd.forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (request.getRequestURI().equals("/EcomerceLivroLES/cli-alterar")) {

			ClienteVH clienteVh = new ClienteVH();
			Cliente cliente = (Cliente) clienteVh.getEntidade(request);

			ICommand cmd = new AlterarCommand();
			Resultado resultado = cmd.executar(cliente);

			if (resultado.getMensagemErro() != null) {
				request.getSession().setAttribute("mensagem",
						"Nao foi possivel alterar os dados do cliente. Motivos: " + resultado.getMensagemErro());
			} else {
				clienteVh.setEntidade(response, request, resultado);
			}

			response.sendRedirect("cli-perfil");

		}

	}

}
