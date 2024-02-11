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
import controle.web.vh.impl.EstoqueVH;
import controle.web.vh.impl.ItemEstoqueVH;
import dominio.Resultado;
import dominio.venda.Estoque;
import dominio.venda.ItemEstoque;

@WebServlet(urlPatterns = { "/adm-estoque", "/adm-item-detalhes", "/adm-estoque-detalhes", "/cli-item-detalhes",
		"/cli-itens" })
public class EstoqueController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher rd = null;

	public void service(HttpServletRequest request, HttpServletResponse response) {

		String operacao = request.getParameter("operacao");

		if (request.getRequestURI().equals("/EcomerceLivroLES/adm-estoque-alterar")) {
			EstoqueVH estoqueVh = new EstoqueVH();
			Estoque estoque = (Estoque) estoqueVh.getEntidade(request);

			ICommand cmd = new AlterarCommand();

			String retorno = cmd.executar(estoque).getMensagemErro();

			if (retorno != null) {
				request.setAttribute("retorno", retorno);
			}

			try {
				response.sendRedirect("adm-estoque");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			if (request.getRequestURI().equals("/EcomerceLivroLES/adm-estoque")) {

				EstoqueVH estoqueVh = new EstoqueVH();
				Estoque estoque = (Estoque) estoqueVh.getEntidade(request);

				ICommand cmd = new ConsultarCommand();

				@SuppressWarnings("unchecked")
				List<ItemEstoque> itens = (List<ItemEstoque>) cmd.executar(estoque);

				estoque.setItens(itens);

				Resultado resultado = new Resultado();
				resultado.setEntidade(estoque);

				estoqueVh.setEntidade(response, request, resultado);

				rd = request.getRequestDispatcher("adm_estoque.jsp");

			} else if (request.getRequestURI().equals("/EcomerceLivroLES/adm-item-detalhes")) {

				ItemEstoqueVH iteVh = new ItemEstoqueVH();
				ItemEstoque item = (ItemEstoque) iteVh.getEntidade(request);

				ICommand cmd = null;
				Resultado resultado = new Resultado();

				if (operacao != null)
					if (operacao.equals("ConsultarPorId")) {
						cmd = new ConsultarPorIdCommand();
						resultado = cmd.executar(item);

					} else if (operacao.equals("Alterar")) {
						cmd = new AlterarCommand();
						
						resultado = cmd.executar(item);
						String retorno = resultado.getMensagemErro();

						if (retorno != null) {
							request.setAttribute("mensagem", retorno);
						}
					}

				iteVh.setEntidade(response, request, resultado);

				rd = request.getRequestDispatcher("adm_item_detalhes.jsp");

			} else if (request.getRequestURI().equals("/EcomerceLivroLES/cli-item-detalhes")) {

				ItemEstoqueVH itemVh = new ItemEstoqueVH();
				ItemEstoque it = (ItemEstoque) itemVh.getEntidade(request);

				ICommand cmd = new ConsultarPorIdCommand();
				Resultado resultado = cmd.executar(it);

				itemVh.setEntidade(response, request, resultado);

				rd = request.getRequestDispatcher("cli_item_detalhes.jsp");

			} else if (request.getRequestURI().equals("/EcomerceLivroLES/cli-itens")) {

				EstoqueVH estoqueVh = new EstoqueVH();
				Estoque estoque = (Estoque) estoqueVh.getEntidade(request);

				if (operacao != null) {
					if (operacao.equals("Consultar")) {

						ICommand cmd = new ConsultarCommand();
						Resultado resultado = cmd.executar(estoque);

						List<ItemEstoque> itens = resultado.getEntidades().stream().map(entity -> {
							return (ItemEstoque) entity;
						}).toList();
			
						estoque.setItens(itens);
						resultado.setEntidade(estoque);
						
						estoqueVh.setEntidade(response, request, resultado);
					}

				}

				rd = request.getRequestDispatcher("cli_itens.jsp");

			}

			if (rd == null)
				rd = request.getRequestDispatcher("login");

			try {
				rd.forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
