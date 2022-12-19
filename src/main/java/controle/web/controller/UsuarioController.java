package controle.web.controller;

import java.io.IOException;

import controle.web.AlterarCommand;
import controle.web.ICommand;
import controle.web.vh.impl.UsuarioVH;
import dao.impl.UsuarioDAO;
import dominio.cliente.Usuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/autenticar" ,"/adm-home","/cli-home", "/login", "/cli-alterar-login"})
public class UsuarioController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
	    if(request.getRequestURI().equals("/EcomerceLivroLES/autenticar")) {
	        
	        UsuarioVH usuarioVh = new UsuarioVH();
            Usuario usuario = (Usuario) usuarioVh.getEntidade(request);
            
            UsuarioDAO usrDao = new UsuarioDAO();
            
            if(usrDao.confirmarLogin(usuario)){
                usrDao = new UsuarioDAO();
                
                usuario = (Usuario) usrDao.consultarPorId(usuario);
                
                usuarioVh.setEntidade(response, request, usuario);
                
                if(usuario.isAdmin()) {
                    response.sendRedirect("adm-home");
                    
                }else {
                    response.sendRedirect("cli-home");
                }

            }else {
                request.getSession().setAttribute("mensagem", "Nao foi possivel realizar o login. Email ou senha invalidos ");
                
                response.sendRedirect("login");
                
            }
             
	    } else if(request.getRequestURI().equals("/EcomerceLivroLES/cli-home")) {
            
            RequestDispatcher rd = request.getRequestDispatcher("/cli_home.jsp");
            
            try {
                rd.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
            
        } else if (request.getRequestURI().equals("/EcomerceLivroLES/adm-home")){
            
            RequestDispatcher rd = request.getRequestDispatcher("/adm_home.jsp");
            
            try {
                rd.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
            
        } else if (request.getRequestURI().equals("/EcomerceLivroLES/login")){
            
            RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
            
            try {
                rd.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
            
        } else if(request.getRequestURI().equals("/EcomerceLivroLES/cli-alterar-login")) {
            
            UsuarioVH usrVh = new UsuarioVH(); 
            Usuario usr = (Usuario) usrVh.getEntidade(request);
            
            ICommand cmd = new AlterarCommand();
            String retorno = (String) cmd.executar(usr);
            
            if(retorno != null){
                request.getSession().setAttribute("mensagem", retorno);
            }else {
                usrVh.setEntidade(response, request, usr);
            }
            
            try {
                response.sendRedirect("cli-perfil");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
	
}
