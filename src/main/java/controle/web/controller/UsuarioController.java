package controle.web.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.web.AlterarCommand;
import controle.web.ICommand;
import controle.web.vh.impl.UsuarioVH;
import dao.impl.UsuarioDAO;
import dominio.Resultado;
import dominio.cliente.Usuario;

@WebServlet(urlPatterns = { "/autenticar", "/adm-home", "/cli-home", "/login", "/cli-alterar-login" })
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getRequestURI().equals("/EcomerceLivroLES/autenticar")) {

			UsuarioVH usuarioVh = new UsuarioVH();
			Usuario usuario = (Usuario) usuarioVh.getEntidade(request);

			UsuarioDAO usrDao = new UsuarioDAO();

			if (usrDao.confirmarLogin(usuario)) {
				usrDao = new UsuarioDAO();

				usuario = (Usuario) usrDao.consultarPorId(usuario);

				Resultado resultado = new Resultado();
				resultado.setEntidade(usuario);

				usuarioVh.setEntidade(response, request, resultado);

				if (usuario.isAdmin()) {
					response.sendRedirect("adm-home");

				} else {
					response.sendRedirect("cli-home");
				}

			} else {
				request.getSession().setAttribute("mensagem",
						"Nao foi possivel realizar o login. Email ou senha invalidos ");

				response.sendRedirect("login");

			}

		} else if (request.getRequestURI().equals("/EcomerceLivroLES/cli-home")) {

			RequestDispatcher rd = request.getRequestDispatcher("/cli_home.jsp");

			try {
				rd.forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}

		} else if (request.getRequestURI().equals("/EcomerceLivroLES/adm-home")) {

			RequestDispatcher rd = request.getRequestDispatcher("/adm_home.jsp");

			try {
				rd.forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}

		} else if (request.getRequestURI().equals("/EcomerceLivroLES/login")) {

			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");

			try {
				rd.forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}

		} else if (request.getRequestURI().equals("/EcomerceLivroLES/cli-alterar-login")) {

			UsuarioVH usrVh = new UsuarioVH();
			Usuario usr = (Usuario) usrVh.getEntidade(request);

			ICommand cmd = new AlterarCommand();
			Resultado resultado = cmd.executar(usr);
			String retorno = resultado.getMensagemErro();
			
			if (retorno != null) {
				request.getSession().setAttribute("mensagem", retorno);
			} else {
				usrVh.setEntidade(response, request, resultado);
			}

			try {
				response.sendRedirect("cli-perfil");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
