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
import dominio.venda.ItemPedido;
import dominio.venda.Pedido;

public class ItemPedidoDAO implements IDAO {
    private Connection connection = null;
    private boolean controleTransacao = true;

    public ItemPedidoDAO() {
    }

    public ItemPedidoDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void salvar(EntidadeDominio entidade) {
        ItemPedido item = (ItemPedido) entidade;
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();

        sql.append("INSERT INTO itenspedidos   ");
        sql.append("(itp_lvr_id, itp_quantidade, itp_pdd_id, itp_valor_unitario) ");
        sql.append("VALUES (?,?,?, ?);");

        try {
            if (connection == null) {
                connection = Conexao.getConnectionPostgres();
            } else {
                controleTransacao = false;
            }

            connection.setAutoCommit(false);

            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

            pst.setInt(1, item.getLivro().getId());
            pst.setInt(2, item.getQuantidade());
            pst.setInt(3, item.getPedido().getId());
            pst.setString(4, (item.getValorVenda().multiply(
                    new BigDecimal(
                            item.getQuantidade())))
                    .toString());
            pst.executeUpdate();

            connection.commit();

        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
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

    }

    @Override
    public void alterar(EntidadeDominio entidade) {
        // TODO Auto-generated method stub

    }

    @Override
    public void excluir(EntidadeDominio entidade) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        Pedido pedido = (Pedido) entidade;
        List<EntidadeDominio> itens = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;

        sql.append("SELECT * FROM itenspedidos ");
        sql.append("WHERE itp_pdd_id = ? ;");

        try {
            if (connection == null) {
                connection = Conexao.getConnectionPostgres();
            } else {
                controleTransacao = false;
            }

            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

            pst.setInt(1, pedido.getId());

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Livro livro = new Livro();
                LivroDAO livroDao = new LivroDAO(connection);
                livro.setId(rs.getInt("itp_lvr_id"));
                livro = (Livro) livroDao.consultarPorId(livro);

                int quantidade = rs.getInt("itp_quantidade");
                BigDecimal valorUnitario = new BigDecimal(rs.getString("itp_valor_unitario"));

                ItemPedido ic = new ItemPedido();

                ic.setLivro(livro);
                ic.setQuantidade(quantidade);
                ic.setValorVenda(valorUnitario);

                ic.setId(rs.getInt("itp_id"));

                itens.add(ic);
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
        ItemPedido item = (ItemPedido) entidade;
        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;

        sql.append("SELECT * FROM itenspedidos ");
        sql.append("WHERE itp_id = ? ;");

        try {
            if (connection == null) {
                connection = Conexao.getConnectionPostgres();
            } else {
                controleTransacao = false;
            }

            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

            pst.setInt(1, item.getId());

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                LivroDAO livroDao = new LivroDAO(connection);
                Livro livro = new Livro();
                livro.setId(rs.getInt("itp_lvr_id"));
                livro = (Livro) livroDao.consultarPorId(livro);

                int quantidade = rs.getInt("itp_quantidade");
                BigDecimal valorUnitario = new BigDecimal(rs.getString("itp_valor_unitario"));

                item = new ItemPedido();

                item.setLivro(livro);
                item.setQuantidade(quantidade);
                item.setValorVenda(valorUnitario);

                item.setId(rs.getInt("itp_id"));
            }

            return item;

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

}
