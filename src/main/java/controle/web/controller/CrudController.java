
package controle.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.command.ICommand;
import controle.command.impl.AlterarCommand;
import controle.command.impl.ConsultarCommand;
import controle.command.impl.ConsultarPorIdCommand;
import controle.command.impl.ExcluirCommand;
import controle.command.impl.SalvarCommand;
import controle.facade.dto.input.InputDTO;
import controle.web.viewhelper.IViewHelper;
import controle.web.viewhelper.factory.IViewHelperFactory;
import controle.web.viewhelper.factory.impl.PersistanceViewHelperFactory;
import dominio.Operacao;

@WebServlet(urlPatterns = { "/clientes" })
public class CrudController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Map<Operacao, ICommand> cmds;

    Map<Operacao, IViewHelperFactory> viewHelperFactories = new HashMap<>();

    public CrudController() {
        PersistanceViewHelperFactory persistanceViewHelperFactory = new PersistanceViewHelperFactory();
        viewHelperFactories.put(Operacao.SALVAR, persistanceViewHelperFactory);
        viewHelperFactories.put(Operacao.ALTERAR, persistanceViewHelperFactory);

        cmds = new HashMap<>();
        cmds.put(Operacao.SALVAR, new SalvarCommand());
        cmds.put(Operacao.ALTERAR, new AlterarCommand());
        cmds.put(Operacao.EXCLUIR, new ExcluirCommand());
        cmds.put(Operacao.CONSULTAR, new ConsultarCommand());
        cmds.put(Operacao.CONSULTAR_POR_ID, new ConsultarPorIdCommand());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        Operacao operacao = Operacao.obterPorValor(req.getParameter("operacao"));
        String dominio = obterDominio(uri);
        execute(req, resp, operacao, dominio);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        Operacao operacao = obterOperacaoConsulta(uri);
        String dominio = obterDominio(uri);
        execute(req, resp, operacao, dominio);
    }

    private void execute(HttpServletRequest req, HttpServletResponse res, Operacao operacao, String dominio) {
        ICommand cmd = cmds.get(operacao); // Factory
        IViewHelperFactory factory = viewHelperFactories.get(operacao);
        IViewHelper vh = factory.create(dominio); // Factory
        InputDTO entidade = vh.getDTO(req);
        Object resultado = cmd.executar(entidade);
        vh.setView(req, res, null);
    }

    private String obterDominio(String uri) {
        String[] partes = uri.split("/");
        return partes[1];
    }

    private Operacao obterOperacaoConsulta(String uri) {
        String[] parts = uri.split("/");
        if (parts.length == 2) // dominio
            return Operacao.CONSULTAR;
        if (parts.length == 3) // dominio/id
            return Operacao.CONSULTAR_POR_ID;
        throw new IllegalArgumentException("Consulta operation not recognized for path: " + uri);
    }
}
