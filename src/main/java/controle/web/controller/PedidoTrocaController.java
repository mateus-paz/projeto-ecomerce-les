package controle.web.controller;

import java.io.IOException;
import java.util.List;

import controle.web.AlterarCommand;
import controle.web.ConsultarCommand;
import controle.web.ConsultarPorIdCommand;
import controle.web.ICommand;
import controle.web.SalvarCommand;
import controle.web.vh.impl.EstoqueVH;
import controle.web.vh.impl.PedidoTrocaVH;
import dao.impl.PedidoTrocaDAO;
import dominio.venda.Estoque;
import dominio.venda.PedidoTroca;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/cli-pedido-troca-detalhes", "/cli-pedidos-troca",
        "/adm-pedidos-troca","/adm-pedido-troca-detalhes"})

public class PedidoTrocaController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private RequestDispatcher rd = null;
    
    protected void service(HttpServletRequest request, HttpServletResponse response) {
        
        PedidoTrocaVH pedidoVH = new PedidoTrocaVH();
        PedidoTroca pedido = (PedidoTroca) pedidoVH.getEntidade(request);
        
        ICommand cmd = null;
        String retorno = null;
        
        if(request.getRequestURI().equals("/EcomerceLivroLES/cli-pedido-troca-detalhes")) {
            String operacao = request.getParameter("operacao");
            
            if(operacao != null) {
                if(operacao.equals("Salvar")) {
                    cmd = new SalvarCommand();
                    retorno = (String) cmd.executar(pedido);
                } else if(operacao.equals("ConsultarPorId")) {
                    cmd = new ConsultarPorIdCommand();
                    pedido = (PedidoTroca) cmd.executar(pedido);
                }else if(operacao.equals("ConsultarPorPedido")) {
                    PedidoTrocaDAO pedidoDao = new PedidoTrocaDAO();
                    pedido = pedidoDao.consultarPorPedido(pedido);
                }
                
            } 
            
            if(retorno != null) {
                request.setAttribute("mensagemErro", retorno);
                rd = request.getRequestDispatcher("/cli_pedido_detalhes.jsp");
            }else {
                pedidoVH.setEntidade(response, request, pedido);
                rd = request.getRequestDispatcher("/cli_pedido_troca_detalhes.jsp");
            }
            
        }else if(request.getRequestURI().equals("/EcomerceLivroLES/cli-pedidos-troca")){
             
            cmd = new ConsultarCommand();
            
            @SuppressWarnings("unchecked")
            List<PedidoTroca> pedidos = (List<PedidoTroca>) cmd.executar(pedido);
            
            request.getSession().setAttribute("pedidos", pedidos);
            
            rd = request.getRequestDispatcher("/cli_pedidos_troca.jsp");
            
        } else if(request.getRequestURI().equals("/EcomerceLivroLES/adm-pedidos-troca")){
             
            cmd = new ConsultarCommand();
            
            @SuppressWarnings("unchecked")
            List<PedidoTroca> pedidos = (List<PedidoTroca>) cmd.executar(pedido);
            
            request.getSession().setAttribute("pedidos", pedidos);
            
            rd = request.getRequestDispatcher("/adm_pedidos_troca.jsp");
        } else if(request.getRequestURI().equals("/EcomerceLivroLES/adm-pedido-troca-detalhes")) {
            
            String operacao = request.getParameter("operacao");
            
            if(operacao != null)
                if(operacao.equals("Alterar")) {
                    cmd = new AlterarCommand();
                    retorno = (String) cmd.executar(pedido);
                    
                    if(retorno == null && pedido.getPedido().getStatus().getValor() == 7) {
                        EstoqueVH estoqueVh = new EstoqueVH();
                        Estoque estoque = (Estoque) estoqueVh.getEntidade(request);
                        retorno = (String) cmd.executar(estoque);
                    }
                    
                } else if(operacao.equals("ConsultarPorId")) {
                    cmd = new ConsultarPorIdCommand();
                    pedido = (PedidoTroca) cmd.executar(pedido);
                    
                } else if(operacao.equals("ConsultarPorPedido")) {
                    PedidoTrocaDAO pedidoDao = new PedidoTrocaDAO();
                    pedido = pedidoDao.consultarPorPedido(pedido);
                }
            
            if(retorno != null) {
                request.setAttribute("mensagemErro", retorno);
            }
            
            pedidoVH.setEntidade(response, request, pedido);
            
            rd = request.getRequestDispatcher("/adm_pedido_troca_detalhes.jsp");
     
        }
        
        try {
            rd.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
        
                
    }

}
