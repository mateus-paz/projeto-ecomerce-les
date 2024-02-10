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
import controle.web.vh.impl.CarrinhoVH;
import controle.web.vh.impl.ItemCarrinhoVH;
import dominio.venda.Carrinho;
import dominio.venda.ItemCarrinho;

@WebServlet(urlPatterns = {"/cli-carrinho-compras"})

public class CarrinhoController  extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Carrinho carrinho = null;
	
	protected void service(HttpServletRequest request, HttpServletResponse response){
		
	    String operacao = request.getParameter("operacao");
	    
	    if(operacao != null){
		    ItemCarrinhoVH itemVh = new ItemCarrinhoVH();
		    ItemCarrinho item = (ItemCarrinho) itemVh.getEntidade(request);
		    		    
		    ICommand cmd = null;
		    
		    if(operacao.equals("Adicionar")) {
	            if(item.getCarrinho().getItens().contains(item)) {
	                cmd = new AlterarCommand();
	            }else {    
	                cmd = new SalvarCommand();
	            }
	            
	        }else if(operacao.equals("Editar")) {
	            cmd = new AlterarCommand();
	            
	        }else if(operacao.equals("Remover")) {
	            cmd = new ExcluirCommand();
	        }
	            
	        String retorno = (String) cmd.executar(item);
	        
            if(retorno != null) {
                request.setAttribute("mensagemErro", retorno);
            }
		    
		}
		
        CarrinhoVH carrinhoVh = new CarrinhoVH();
        carrinho = (Carrinho) carrinhoVh.getEntidade(request);
		
		carrinhoVh.setEntidade(response, request, carrinho);
			
		RequestDispatcher rd = request.getRequestDispatcher("cli_carrinho_compras.jsp");
		
		try {
            rd.forward(request, response);
        } catch (ServletException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
	}
	
}
