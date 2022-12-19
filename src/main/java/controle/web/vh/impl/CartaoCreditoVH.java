package controle.web.vh.impl;

import controle.web.vh.IViewHelper;
import dao.impl.CartaoCreditoDAO;
import dominio.EntidadeDominio;
import dominio.cliente.Cliente;
import dominio.venda.Bandeira;
import dominio.venda.CartaoCredito;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CartaoCreditoVH implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        CartaoCredito cartao = null;
        
        String operacao = request.getParameter("operacao");
        
        if(operacao.equals("Salvar") || operacao.equals("Adicionar")){
            
            Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
            
            String txtNumero = request.getParameter("txtNumero");
            String txtCvv = request.getParameter("txtCVV");
            String txtTitular = request.getParameter("txtTitular");
            String txtValidade = request.getParameter("txtValidade");
            String txtNomeIdentificacao = request.getParameter("txtNomeIdentificacao");
            
            int nmBandeira= Integer.valueOf(request.getParameter("cbbBandeira"));
            Bandeira bandeira = null; 
            if(nmBandeira == 1) {
                bandeira = Bandeira.VISA;
            }else if(nmBandeira == 2) {
                bandeira = Bandeira.MASTERCARD;
            }
                
            cartao = new CartaoCredito(txtNomeIdentificacao, txtValidade, txtTitular,
                    txtNumero, txtCvv, bandeira);
            
            cartao.setCliente(cliente);
            
        }else if(operacao.equals("Remover") || operacao.equals("Selecionar")) {
        
            String txtCartaoId = request.getParameter("txtIdCartao");
            
            cartao = new CartaoCredito();
            cartao.setId(Integer.valueOf(txtCartaoId));
            
            if(operacao.equals("Selecionar")) {
                CartaoCreditoDAO cardDao = new CartaoCreditoDAO();
                cartao = (CartaoCredito) cardDao.consultarPorId(cartao);
            }
            
        }
        
        return cartao;
    }

    @Override
    public void setEntidade(HttpServletResponse response, HttpServletRequest request, Object msg) {
        // TODO Auto-generated method stub

    }

}
