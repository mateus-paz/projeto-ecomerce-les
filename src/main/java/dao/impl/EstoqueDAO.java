package dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.IDAO;
import dominio.EntidadeDominio;
import dominio.livro.Livro;
import dominio.venda.Estoque;
import dominio.venda.ItemEstoque;

public class EstoqueDAO implements IDAO {
    private Connection connection = null;
    private boolean controleTransacao = true;

    public EstoqueDAO() {
    }

    public EstoqueDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void salvar(EntidadeDominio entidade) {
        // TODO Auto-generated method stub

    }

    @Override
    public void alterar(EntidadeDominio entidade) {
        Estoque estoque = (Estoque) entidade;

        try {
            if (connection == null) {
                connection = Conexao.getConnectionPostgres();
            } else {
                controleTransacao = false;
            }

            for (ItemEstoque item : estoque.getItens()) {

                ItemEstoqueDAO itDao = new ItemEstoqueDAO(connection);
                ItemEstoque it = new ItemEstoque();

                it.setLivro(item.getLivro());

                it = (ItemEstoque) itDao.consultarPorLivroId(it);

                int quantidade = it.getQuantidade() + item.getQuantidade();

                item.setQuantidade(quantidade);

                itDao.alterar(item);

            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (controleTransacao) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void excluir(EntidadeDominio entidade) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        Estoque estoque = (Estoque) entidade;

        List<EntidadeDominio> itens = new ArrayList<>();
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT distinct(etq_id), etq_quantidade, etq_valor_venda, lvr_id ");
        sql.append("FROM estoque inner join livros on (etq_lvr_id = lvr_id) ");

        if (estoque.getParametros().size() != 0) {

            sql.append("inner join autores_livros on (atl_lvr_id = lvr_id) ");
            sql.append("inner join autores on (atl_atr_id = atr_id) ");
            sql.append("inner join categorias_livros on (ctl_lvr_id = lvr_id) ");
            sql.append("inner join categorias on (ctl_cat_id = cat_id) ");

            String clausulaWhere = "WHERE ";
            for (String parametro : estoque.getParametros()) {
                clausulaWhere += parametro + " like '%" + estoque.getValorBusca() + "%' or ";
            }
            clausulaWhere = clausulaWhere.substring(0, clausulaWhere.length() - 4);
            sql.append(clausulaWhere);
        }

        try {
            if (connection == null) {
                connection = Conexao.getConnectionPostgres();
            } else {
                controleTransacao = false;
            }

            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Livro livro = new Livro();
                LivroDAO livroDao = new LivroDAO(connection);
                livro.setId(rs.getInt("lvr_id"));
                livro = (Livro) livroDao.consultarPorId(livro);

                int quantidade = rs.getInt("etq_quantidade");
                BigDecimal valorVenda = new BigDecimal(rs.getString("etq_valor_venda"));

                ItemEstoque item = new ItemEstoque();

                item.setLivro(livro);
                item.setQuantidade(quantidade);
                item.setValorVenda(valorVenda);

                int id = rs.getInt("etq_id");
                item.setId(id);

                itens.add(item);
            }

            return itens;

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (controleTransacao) {
                try {
                    pst.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;

    }

    @Override
    public EntidadeDominio consultarPorId(EntidadeDominio entidade) {
        // TODO Auto-generated method stub
        return null;
    }

}
