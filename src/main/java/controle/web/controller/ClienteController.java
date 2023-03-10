package controle.web.controller;

import java.io.IOException;
import java.util.List;

import controle.web.AlterarCommand;
import controle.web.ConsultarCommand;
import controle.web.ConsultarPorIdCommand;
import controle.web.ICommand;
import controle.web.SalvarCommand;
import controle.web.vh.impl.ClienteVH;
import dominio.cliente.Cliente;
import dominio.venda.Pedido;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/adm-clientes", "/adm-cliente-detalhes", "/cli-cadastrar", 
        "/cli-perfil", "/cli-alterar"})
public class ClienteController  extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public void service(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		if(request.getRequestURI().equals("/EcomerceLivroLES/adm-clientes")) {
		    
		    ClienteVH cliVh = new ClienteVH();
		    Cliente cliente = (Cliente) cliVh.getEntidade(request);
		    
		    ICommand cmd = new ConsultarCommand();
		    @SuppressWarnings("unchecked")
            List<Cliente> clientes = (List<Cliente>) cmd.executar(cliente);
		    
	        request.setAttribute("clientes", clientes);
	        
	        RequestDispatcher rd = request.getRequestDispatcher("/adm_clientes.jsp");
	        
	        try {
	            rd.forward(request, response);
	        } catch (ServletException | IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
		    
		}else if(request.getRequestURI().equals("/EcomerceLivroLES/adm-cliente-detalhes")) {
		    
		    ClienteVH cliVh = new ClienteVH();
            Cliente cliente = (Cliente) cliVh.getEntidade(request);
            
            ICommand cmd = new ConsultarPorIdCommand();
            cliente = (Cliente) cmd.executar(cliente);
            
            cliVh.setEntidade(response, request, cliente);
	        
            Pedido pedido = new Pedido();
            pedido.setCliente(cliente);
            
            cmd = new ConsultarCommand();
            
            @SuppressWarnings("unchecked")
            List<Pedido> pedidos = (List<Pedido>) cmd.executar(pedido);
            request.setAttribute("pedidos", pedidos);
            
	        RequestDispatcher rd = request.getRequestDispatcher("/adm_cliente_detalhes.jsp");
	        
	        try {
	            rd.forward(request, response);
	        } catch (ServletException | IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }


		}else if(request.getRequestURI().equals("/EcomerceLivroLES/cli-cadastrar")) {
		    
		    ClienteVH clienteVh = new ClienteVH();
		    Cliente cliente = (Cliente) clienteVh.getEntidade(request);
		    
		    ICommand cmd = new SalvarCommand();
		    String retorno = (String) cmd.executar(cliente);
		    
		    if(retorno != null){
		        request.getSession().setAttribute("mensagem", "Nao foi possivel realizar o Cadastro. Motivos: "+retorno);
		    }else {
		        request.getSession().setAttribute("mensagem", "Cliente cadastrado com Sucesso");
		    }
		    		    
		    response.sendRedirect("login");
		    
        } else if(request.getRequestURI().equals("/EcomerceLivroLES/cli-home")) {
            
            ClienteVH clienteVh = new ClienteVH();
            Cliente cliente = (Cliente) clienteVh.getEntidade(request);
            clienteVh.setEntidade(response, request, cliente);
            
            ICommand cmd = new ConsultarCommand();
            String retorno = (String) cmd.executar(cliente);
            
            if(retorno != null){
                
            }else {
                
            }
            
            
        }else if(request.getRequestURI().equals("/EcomerceLivroLES/cli-perfil")) {
            
            ClienteVH clienteVh = new ClienteVH();
            
            Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
            
            if(cliente == null) {
                cliente = (Cliente) clienteVh.getEntidade(request);
                
            }
            
            ICommand cmd = new ConsultarPorIdCommand();
            cliente = (Cliente) cmd.executar(cliente);
            
            clienteVh.setEntidade(response, request, cliente);
            
            RequestDispatcher rd = request.getRequestDispatcher("/cli_perfil.jsp");
            
            try {
                rd.forward(request, response);
            } catch (ServletException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        
        }else if(request.getRequestURI().equals("/EcomerceLivroLES/cli-alterar")) {
            
            ClienteVH clienteVh = new ClienteVH();
            Cliente cliente = (Cliente) clienteVh.getEntidade(request);
                        
            ICommand cmd = new AlterarCommand();
            String retorno = (String) cmd.executar(cliente);
           
            if(retorno != null){
                request.getSession().setAttribute("mensagem", "Nao foi possivel alterar os dados do cliente. Motivos: "+retorno);
            }else {
                clienteVh.setEntidade(response, request, cliente);
            }
            
            response.sendRedirect("cli-perfil");
        
        }
    	
	}
	
}
