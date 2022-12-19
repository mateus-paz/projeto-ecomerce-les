package controle.web.controller;

import java.io.IOException;
import java.util.List;

import controle.web.AlterarCommand;
import controle.web.ConsultarCommand;
import controle.web.ConsultarPorIdCommand;
import controle.web.ICommand;
import controle.web.vh.impl.EstoqueVH;
import controle.web.vh.impl.ItemEstoqueVH;
import dominio.venda.Estoque;
import dominio.venda.ItemEstoque;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/adm-estoque", "/adm-item-detalhes", "/adm-estoque-detalhes", "/cli-item-detalhes", "/cli-itens"})
public class EstoqueController extends HttpServlet{
    private static final long serialVersionUID = 1L;
    RequestDispatcher rd = null;
    
    public void service(HttpServletRequest request, HttpServletResponse response) {
        
        String operacao = request.getParameter("operacao");
        
        if(request.getRequestURI().equals("/EcomerceLivroLES/adm-estoque-alterar")) {
            EstoqueVH estoqueVh = new EstoqueVH();
            Estoque estoque = (Estoque) estoqueVh.getEntidade(request);
            
            ICommand cmd = new AlterarCommand();
            
            String retorno = (String) cmd.executar(estoque);
            
            if(retorno != null) {
                request.setAttribute("retorno", retorno);
            }
            
            try {
                response.sendRedirect("adm-estoque");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }else {
            if(request.getRequestURI().equals("/EcomerceLivroLES/adm-estoque")) {
                
                EstoqueVH estoqueVh = new EstoqueVH();
                Estoque estoque = (Estoque) estoqueVh.getEntidade(request);
                
                ICommand cmd = new ConsultarCommand();
                
                @SuppressWarnings("unchecked")
                List<ItemEstoque> itens = (List<ItemEstoque>) cmd.executar(estoque);

                estoque.setItens(itens);
                
                estoqueVh.setEntidade(response, request, estoque);
                
                rd = request.getRequestDispatcher("adm_estoque.jsp");
                
            }else if(request.getRequestURI().equals("/EcomerceLivroLES/adm-item-detalhes")) {
                
                ItemEstoqueVH iteVh = new ItemEstoqueVH();
                ItemEstoque item = (ItemEstoque) iteVh.getEntidade(request);
                
                ICommand cmd = null;
                
                if(operacao!=null)
                    if(operacao.equals("ConsultarPorId")) {
                        cmd = new ConsultarPorIdCommand();
                        item = (ItemEstoque) cmd.executar(item);
                        
                    }else if (operacao.equals("Alterar")) {
                        cmd = new AlterarCommand();
                        String retorno = (String) cmd.executar(item);
                        
                        if(retorno != null) {
                            request.setAttribute("mensagem", retorno);
                        }
                    }
                
                iteVh.setEntidade(response, request, item);
                
                rd = request.getRequestDispatcher("adm_item_detalhes.jsp");
                
            } else if(request.getRequestURI().equals("/EcomerceLivroLES/cli-item-detalhes")) {
                
                ItemEstoqueVH itemVh = new ItemEstoqueVH();
                ItemEstoque it = (ItemEstoque) itemVh.getEntidade(request);

                ICommand cmd = new ConsultarPorIdCommand();
                it = (ItemEstoque) cmd.executar(it);
              
                itemVh.setEntidade(response, request, it);
                
                rd = request.getRequestDispatcher("cli_item_detalhes.jsp");
                
            } else if(request.getRequestURI().equals("/EcomerceLivroLES/cli-itens")) {

                EstoqueVH estoqueVh = new EstoqueVH();
                Estoque estoque = (Estoque) estoqueVh.getEntidade(request);
                
                if(operacao != null) {
                    if(operacao.equals("Consultar")) {
                        
                        ICommand cmd = new ConsultarCommand();
                        
                        @SuppressWarnings("unchecked")
                        List<ItemEstoque> itens = (List<ItemEstoque>) cmd.executar(estoque);
                        
                        estoque.setItens(itens);
                        
                        estoqueVh.setEntidade(response, request, estoque);
                        
                    }
                    
                }
                
                rd = request.getRequestDispatcher("cli_itens.jsp");
               
            }
            
            
            if(rd == null)
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
