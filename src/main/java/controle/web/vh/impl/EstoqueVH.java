package controle.web.vh.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.web.vh.IViewHelper;
import dominio.EntidadeDominio;
import dominio.Resultado;
import dominio.venda.CategoriaStatus;
import dominio.venda.Estoque;
import dominio.venda.ItemEstoque;
import dominio.venda.ItemTroca;
import dominio.venda.PedidoTroca;

public class EstoqueVH implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {

        Estoque estoque = new Estoque();

        String operacao = request.getParameter("operacao");

        if (operacao != null)
            if (operacao.equals("Alterar")) {
                PedidoTroca pedido = (PedidoTroca) request.getSession().getAttribute("pedidoTroca");

                List<ItemEstoque> itens = new ArrayList<>();

                String idItem = null;
                int quantidade = 0;
                int i = 1;

                for (ItemTroca item : pedido.getItens()) {
                    idItem = request.getParameter("idItem" + i);

                    if (idItem == null) {
                        continue;
                    } else if (item.getId() == Integer.valueOf(idItem)) {

                        quantidade = Integer.valueOf(request.getParameter("txtQnt" + item.getId()));

                        if (quantidade != 0) {
                            ItemEstoque ite = new ItemEstoque();

                            ite.setLivro(item.getLivro());
                            ite.setQuantidade(quantidade);
                            ite.setCatStatus(CategoriaStatus.EM_ESTOQUE);
                            ite.setJustificativaStatus("Reentrada em estoque após troca. ");

                            itens.add(ite);
                        }

                        quantidade = 0;
                        idItem = null;
                    }

                    i++;
                }

                estoque.setItens(itens);

            } else if (operacao.equals("Consultar")) {

                String valorBusca = request.getParameter("txtPesquisa");
                List<String> parametros = new ArrayList<>();

                if (request.getParameter("swtTitulo") != null)
                    parametros.add("lvr_titulo");

                if (request.getParameter("swtAutores") != null)
                    parametros.add("atr_nome");

                if (request.getParameter("swtCategorias") != null)
                    parametros.add("cat_descricao");

                if (request.getParameter("swtIsbn") != null)
                    parametros.add("lvr_isbn");

                if (request.getParameter("swtEditora") != null)
                    parametros.add("lvr_editora");

                estoque = Estoque.builder()
                        .parametros(parametros)
                        .valorBusca(valorBusca).build();

            }

        return estoque;
    }

    @Override
    public void setEntidade(HttpServletResponse response, HttpServletRequest request, Resultado resultado) {
        request.getSession().setAttribute("estoque", (Estoque) resultado.getEntidades().get(0));

    }

}
