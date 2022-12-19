package controle.web.vh.impl;

import controle.web.vh.IViewHelper;
import dao.impl.EnderecoDAO;
import dominio.EntidadeDominio;
import dominio.cliente.Cidade;
import dominio.cliente.Cliente;
import dominio.cliente.Endereco;
import dominio.cliente.Estado;
import dominio.cliente.Pais;
import dominio.cliente.TipoLogradouro;
import dominio.cliente.TipoResidencia;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EnderecoVH implements IViewHelper {

	public EntidadeDominio getEntidade(HttpServletRequest request) {
		Endereco endereco = null;
		
		String idEndereco = request.getParameter("txtEnderecoId");
		
        if(idEndereco != null) {
            EnderecoDAO enderecoDao = new EnderecoDAO();
            endereco = new Endereco();
            endereco.setId(Integer.valueOf(idEndereco));
        
            endereco = (Endereco) enderecoDao.consultarPorId(endereco);
            
            return endereco;
        }
		
		String nmPais = request.getParameter("txtPais");
		Pais pais = new Pais(nmPais);
		
		String nmEstado = request.getParameter("txtEstado");
		Estado estado = new Estado(nmEstado, pais);
		
		String nmCidade = request.getParameter("txtCidade");
		Cidade cidade = new Cidade(nmCidade, estado);
		
		int nmTpResidencia = Integer.valueOf(request.getParameter("cbbTpResidencia"));
		TipoResidencia tpResidencia = null;
		if(nmTpResidencia == 1) {
		    tpResidencia = TipoResidencia.CASA;
		}else if (nmTpResidencia == 2){
		    tpResidencia = TipoResidencia.APARTAMENTO;
		}else if (nmTpResidencia == 3){
		    tpResidencia = TipoResidencia.OUTRO;
        }
		    
		int nmTpLogradouro = Integer.valueOf(request.getParameter("cbbTpLogradouro"));
		TipoLogradouro tpLogradouro = null; 
		if(nmTpLogradouro == 1) {
		    tpLogradouro = TipoLogradouro.RUA;
        }else if (nmTpLogradouro == 2){
            tpLogradouro = TipoLogradouro.AVENIDA;
        }else if (nmTpLogradouro == 3){
            tpLogradouro = TipoLogradouro.OUTRO;
        }
		
		String nmObservacao = request.getParameter("txtObservacao");
		String nmLogradouro = request.getParameter("txtLogradouro");
		String nmNumero = request.getParameter("txtNumero");
		String nmBairro = request.getParameter("txtBairro");
		String nmCep = request.getParameter("txtCep");
		String nmComplemento = request.getParameter("txtComplemento");
	
		endereco = new Endereco(
				tpResidencia, tpLogradouro, nmLogradouro, 
				nmNumero, nmBairro, nmCep, cidade, 
				nmObservacao, nmComplemento
				);
	
		Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
		endereco.setCliente(cliente);	
		
		return endereco;
		
	}

	
	public void setEntidade(HttpServletResponse response, HttpServletRequest request, Object msg) {
	    request.getSession().setAttribute("endereco", (Endereco) msg);
		
	}

}
