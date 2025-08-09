package controle.web.viewhelper.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.EstoquePersistenciaDTO;
import controle.web.viewhelper.IViewHelper;
import dominio.venda.CategoriaStatus;
import dominio.venda.ItemEstoque;
import dominio.venda.ItemTroca;
import dominio.venda.PedidoTroca;

public class EstoquePersistenciaViewHelper implements IViewHelper {

    @Override
    public EstoquePersistenciaDTO getDTO(HttpServletRequest request) {
        EstoquePersistenciaDTO estoquePersistenciaDTO = new EstoquePersistenciaDTO();
        String operacao = request.getParameter("operacao");

        if (operacao != null && operacao.equals("Alterar")) {
            PedidoTroca pedido = (PedidoTroca) request.getSession().getAttribute("pedidoTroca");

            if (pedido != null) {
                List<ItemEstoque> itens = new ArrayList<>();
                for (ItemTroca item : pedido.getItens()) {
                    String idItemParam = request.getParameter("idItem" + item.getId());
                    if (idItemParam != null) {
                        String quantidadeParam = request.getParameter("txtQnt" + item.getId());
                        if (quantidadeParam != null && !quantidadeParam.isEmpty()) {
                            int quantidade = Integer.valueOf(quantidadeParam);
                            if (quantidade != 0) {
                                ItemEstoque ite = new ItemEstoque();
                                ite.setLivro(item.getLivro());
                                ite.setQuantidade(quantidade);
                                ite.setCatStatus(CategoriaStatus.EM_ESTOQUE);
                                ite.setJustificativaStatus("Reentrada em estoque após troca. ");
                                itens.add(ite);
                            }
                        }
                    }
                }
                // estoquePersistenciaDTO.setItens(itens);
            }
        }
        return estoquePersistenciaDTO;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // Lógica para definir a view após a persistência do estoque
    }
}
