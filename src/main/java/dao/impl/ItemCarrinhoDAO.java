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
import dominio.venda.Carrinho;
import dominio.venda.ItemCarrinho;
import dominio.venda.ItemEstoque;

public class ItemCarrinhoDAO implements IDAO {
    private Connection connection = null;
    private boolean controleTransacao = true;

    public ItemCarrinhoDAO() {
    }

    public ItemCarrinhoDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void salvar(EntidadeDominio entidade) {
        ItemCarrinho item = (ItemCarrinho) entidade;
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();

        sql.append("INSERT INTO itensCarrinhos ");
        sql.append("(itc_crr_id, itc_lvr_id, itc_quantidade, itc_valor_venda) ");
        sql.append("VALUES (?,?,?,?);");

        try {
            if (connection == null) {
                connection = Conexao.getConnectionPostgres();
            } else {
                controleTransacao = false;
            }

            connection.setAutoCommit(false);

            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

            pst.setInt(1, item.getCarrinho().getId());
            pst.setInt(2, item.getLivro().getId());
            pst.setInt(3, item.getQuantidade());
            pst.setString(4, item.getValorVenda().toString());

            pst.executeUpdate();

            ItemEstoqueDAO estoqueDao = new ItemEstoqueDAO(connection);
            ItemEstoque itemEstoque = estoqueDao.consultarPorLivro(item.getLivro());

            itemEstoque.setQuantidade(itemEstoque.getQuantidade() - item.getQuantidade());

            estoqueDao.alterar(itemEstoque);

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
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void alterar(EntidadeDominio entidade) {
        ItemCarrinho item = (ItemCarrinho) entidade;
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();

        sql.append("UPDATE itensCarrinhos ");
        sql.append("SET itc_quantidade = ? ");
        sql.append("WHERE itc_id = ? ;");

        try {
            if (connection == null) {
                connection = Conexao.getConnectionPostgres();
            } else {
                controleTransacao = false;
            }

            connection.setAutoCommit(false);

            int quantidadeBloqueada = 0;

            for (ItemCarrinho i : item.getCarrinho().getItens())
                if (i.getId() == item.getId())
                    quantidadeBloqueada = i.getQuantidade() - item.getQuantidade();

            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

            pst.setInt(1, item.getQuantidade());
            pst.setInt(2, item.getId());

            pst.executeUpdate();

            ItemEstoqueDAO itemEstoqueDao = new ItemEstoqueDAO(connection);
            ItemEstoque itemEstoque = itemEstoqueDao.consultarPorLivro(item.getLivro());

            itemEstoque.setQuantidade(itemEstoque.getQuantidade() + quantidadeBloqueada);

            itemEstoqueDao.alterar(itemEstoque);

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
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void excluir(EntidadeDominio entidade) {
        ItemCarrinho item = (ItemCarrinho) entidade;
        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;

        sql.append("DELETE FROM itensCarrinhos ");
        sql.append("WHERE itc_id = ? ;");

        try {
            if (connection == null) {
                connection = Conexao.getConnectionPostgres();
            } else {
                controleTransacao = false;
            }

            connection.setAutoCommit(false);

            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

            pst.setInt(1, item.getId());

            pst.executeUpdate();

            ItemEstoqueDAO estoqueDao = new ItemEstoqueDAO(connection);
            ItemEstoque itemEstoque = estoqueDao.consultarPorLivro(item.getLivro());

            itemEstoque.setQuantidade(itemEstoque.getQuantidade() + item.getQuantidade());

            estoqueDao.alterar(itemEstoque);

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
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        Carrinho carrinho = (Carrinho) entidade;
        List<EntidadeDominio> itens = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;

        sql.append("SELECT * FROM itensCarrinhos ");
        sql.append("WHERE itc_crr_id = ? ;");

        try {
            if (connection == null) {
                connection = Conexao.getConnectionPostgres();
            } else {
                controleTransacao = false;
            }

            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

            pst.setInt(1, carrinho.getId());

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                LivroDAO livroDao = new LivroDAO(connection);
                Livro livro = new Livro();
                livro.setId(rs.getInt("itc_lvr_id"));
                livro = (Livro) livroDao.consultarPorId(livro);

                int quantidade = rs.getInt("itc_quantidade");
                BigDecimal valorVenda = new BigDecimal(rs.getString("itc_valor_venda"));

                ItemCarrinho ic = new ItemCarrinho(carrinho);

                ic.setLivro(livro);
                ic.setQuantidade(quantidade);

                ic.setValorVenda(valorVenda);

                ic.setId(rs.getInt("itc_id"));

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

    public void esvaziarCarrinho(Carrinho carrinho) {
        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;

        sql.append("DELETE FROM itensCarrinhos ");
        sql.append("WHERE itc_crr_id = ? ;");

        try {
            if (connection == null) {
                connection = Conexao.getConnectionPostgres();
            } else {
                controleTransacao = false;
            }

            connection.setAutoCommit(false);

            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

            pst.setInt(1, carrinho.getId());

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
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public EntidadeDominio consultarPorId(EntidadeDominio entidade) {
        ItemCarrinho item = (ItemCarrinho) entidade;
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT * FROM itensCarrinhos ");
        sql.append("WHERE itc_id = ?;");

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
                livro.setId(rs.getInt("itc_lvr_id"));
                livro = (Livro) livroDao.consultarPorId(livro);

                int quantidade = rs.getInt("itc_quantidade");

                item = new ItemCarrinho();

                item.setLivro(livro);
                item.setQuantidade(quantidade);

                item.setValorVenda(new BigDecimal(rs.getString("itc_valor_venda")));

                item.setId(rs.getInt("itc_id"));
            }

            return item;

        } catch (SQLException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
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

}
