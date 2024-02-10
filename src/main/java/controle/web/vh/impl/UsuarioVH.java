package controle.web.vh.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.web.vh.IViewHelper;
import dominio.EntidadeDominio;
import dominio.cliente.Usuario;

public class UsuarioVH implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		Usuario usuario = null;

		String nmOperacao = request.getParameter("operacao");

		if (nmOperacao != null)
			if (nmOperacao.equals("Salvar")) {
				usuario = new Usuario();

				String email = request.getParameter("txtEmail");
				String senha = request.getParameter("txtSenha");
				String confirmaSenha = request.getParameter("txtConfirmarSenha");

				usuario.setEmail(email);
				usuario.setNovaSenha(senha);
				usuario.setConfirmarSenha(confirmaSenha);

			} else if (nmOperacao.equals("Alterar")) {

				usuario = (Usuario) request.getSession().getAttribute("usuario");

				if (usuario != null) {
					String senhaAtual = request.getParameter("txtSenhaAtual");

					String novaSenha = request.getParameter("txtNovaSenha");
					String confirmaSenha = request.getParameter("txtConfirmarSenha");

					usuario.setSenha(senhaAtual);
					usuario.setNovaSenha(novaSenha);
					usuario.setConfirmarSenha(confirmaSenha);

				}
			} else if (nmOperacao.equals("Consultar")) {
				usuario = new Usuario();

				String email = request.getParameter("txtEmail");
				String senha = request.getParameter("txtSenha");

				usuario.setEmail(email);
				usuario.setSenha(senha);
			}

		return usuario;
	}

	@Override
	public void setEntidade(HttpServletResponse response, HttpServletRequest request, Object retorno) {

		request.getSession().setAttribute("usuario", (Usuario) retorno);

	}

}
