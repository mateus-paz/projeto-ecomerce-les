package controle.web.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.web.AlterarCommand;
import controle.web.ExcluirCommand;
import controle.web.ICommand;
import controle.web.SalvarCommand;
import controle.web.vh.impl.CartaoCreditoVH;
import controle.web.vh.impl.PagamentoVH;
import dominio.venda.CartaoCredito;
import dominio.venda.Pagamento;

@WebServlet(urlPatterns = { "/cli-selecionar-pagamento", "/cli-cartao" })
public class PagamentoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) {

		if (request.getRequestURI().equals("/EcomerceLivroLES/cli-cartao")) {

			String operacao = request.getParameter("operacao");

			CartaoCreditoVH cardVh = new CartaoCreditoVH();
			CartaoCredito card = (CartaoCredito) cardVh.getEntidade(request);

			ICommand cmd = null;

			if (operacao.equals("Salvar")) {
				cmd = new SalvarCommand();
			} else if (operacao.equals("Remover")) {
				cmd = new ExcluirCommand();
			}

			String retorno = (String) cmd.executar(card);

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
			String operacao = request.getParameter("operacao");

			PagamentoVH pagamentoVh = new PagamentoVH();
			Pagamento pagamento = (Pagamento) pagamentoVh.getEntidade(request);

			if (operacao != null) {

				ICommand cmd = null;

				if (operacao.equals("Adicionar") || operacao.equals("Selecionar")) {
					cmd = new SalvarCommand();
				} else if (operacao.equals("Alterar")) {
					cmd = new AlterarCommand();
				} else if (operacao.equals("Remover")) {
					cmd = new ExcluirCommand();
				}

				String retorno = (String) cmd.executar(pagamento);

				if (retorno != null) {
					request.setAttribute("mensagemErro", retorno);
				}

			}

			pagamentoVh.setEntidade(response, request, pagamento);

			RequestDispatcher rd = request.getRequestDispatcher("/cli_selecionar_pagamento.jsp");

			try {
				rd.forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}

	}

}
