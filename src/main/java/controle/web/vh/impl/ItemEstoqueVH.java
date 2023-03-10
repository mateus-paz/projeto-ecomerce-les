package controle.web.vh.impl;

import controle.web.vh.IViewHelper;
import dominio.EntidadeDominio;
import dominio.venda.ItemEstoque;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ItemEstoqueVH implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {

        ItemEstoque item = new ItemEstoque();
        
        String operacao = request.getParameter("operacao");
        
        if(operacao != null) {
            if(operacao.equals("Alterar")) {
                int qnt = 0;
                
                String txtQnt = request.getParameter("txtQnt");
                if(txtQnt != null)
                    if(!txtQnt.equals("") && !txtQnt.equals("0"))
                        qnt = Integer.valueOf(txtQnt);
                        
                if(qnt != 0) {
                    item.setQuantidade( item.getQuantidade() + qnt );
                }
                
                String status = request.getParameter("cbbStatus");
                if(status != null)
                    if(status.equals("Ativo")) {
                        item.setAtivo(true);
                    }else {
                        item.setAtivo(false);
                    }
                
                String motivoInativacao = request.getParameter("txtMotivo");
                if(motivoInativacao != null) {
                    item.setJustificativaStatus(motivoInativacao);
                }
                
            }else if(operacao.equals("ConsultarPorId")) {
                int idItem = Integer.valueOf(request.getParameter("idItem"));
                item.setId(idItem);
                
            }
        }else {
            int idItem = Integer.valueOf(request.getParameter("idItem"));
            item.setId(idItem);
            
        }
            
        return item;
    }

    @Override
    public void setEntidade(HttpServletResponse response, HttpServletRequest request, Object msg) {
        request.getSession().setAttribute("item", (ItemEstoque) msg);
        
    }

}
