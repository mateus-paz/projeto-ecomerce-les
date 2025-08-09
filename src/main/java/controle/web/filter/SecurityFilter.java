package controle.web.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controle.command.ICommand;
import dominio.Operacao;
import dominio.cliente.Usuario;

@WebFilter("/*")
public class SecurityFilter implements Filter {

    private static final Map<String, ICommand> commands = new HashMap<>();

    // AuthTokenDAO authTokenDAO = new AuthTokenDAO();

    // IStrategy authStrategy = new AuthTokenStrategy(authTokenDAO);

    // validarTokenAutenticacao;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        // new AuthTokenDTO
        String uri = req.getRequestURI();
        String operacao = req.getParameter("operacao");

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        // / vai para /home ou então /dashboard ou então /login

        // if (usuario == null && !uri.endsWith("/login") &&
        // !uri.endsWith("/cli-cadastrar")) {
        // resp.sendRedirect("login");
        // return;
        // }

        if (operacao != null) {
            Operacao op = Operacao.obterPorValor(operacao);
            String dominio = uri.substring(uri.lastIndexOf('/') + 1);

            // IViewHelper vh = ViewHelperFactory.obterViewHelper(op, dominio);
            // EntidadeDominio entidade = vh.getEntidade(req);

            // ICommand cmd = commands.get(operacao);
            // ResultadoDTO resultado = (ResultadoDTO) //cmd.executar(entidade);

            // vh.setView(resultado, resp, req);
        } else {
            chain.doFilter(request, response);
        }
    }
}
