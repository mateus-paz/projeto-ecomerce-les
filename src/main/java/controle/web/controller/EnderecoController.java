package controle.web.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.web.ExcluirCommand;
import controle.web.ICommand;
import controle.web.SalvarCommand;
import controle.web.vh.impl.EnderecoEntregaVH;
import controle.web.vh.impl.EnderecoVH;
import dominio.cliente.Endereco;
import dominio.venda.EnderecoEntrega;

@WebServlet(urlPatterns = { "/cli-selecionar-endereco", "/cli-endereco" })
public class EnderecoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Endereco endereco = null;

	public void service(HttpServletRequest request, HttpServletResponse response) {

		if (request.getRequestURI().equals("/EcomerceLivroLES/cli-endereco")) {

			String operacao = request.getParameter("operacao");

			EnderecoVH endVh = new EnderecoVH();
			Endereco end = (Endereco) endVh.getEntidade(request);

			ICommand cmd = null;

			if (operacao.equals("Salvar")) {
				cmd = new SalvarCommand();
			} else if (operacao.equals("Excluir")) {
				cmd = new ExcluirCommand();
			}

			String retorno = (String) cmd.executar(end);

			if (retorno != null) {
				request.getSession().setAttribute("mensagem", retorno);
			}

			try {
				response.sendRedirect("cli-perfil");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			if (request.getRequestURI().equals("/EcomerceLivroLES/cli-selecionar-endereco")) {
				String operacao = request.getParameter("operacao");

				if (operacao != null) {
					EnderecoEntregaVH endEntVh = new EnderecoEntregaVH();
					EnderecoEntrega end = (EnderecoEntrega) endEntVh.getEntidade(request);

					ICommand cmd = null;

					if (operacao.equals("Salvar") || operacao.equals("Selecionar")) {
						cmd = new SalvarCommand();

					} else if (operacao.equals("Remover")) {
						cmd = new ExcluirCommand();
					}

					String retorno = (String) cmd.executar(end);

					if (retorno != null) {
						request.setAttribute("mensagemErro", retorno);
					} else {
						if (operacao.equals("Remover"))
							end = null;

						endEntVh.setEntidade(response, request, end);
					}
				}

			}

			RequestDispatcher rd = request.getRequestDispatcher("/cli_selecionar_endereco.jsp");

			try {
				rd.forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
