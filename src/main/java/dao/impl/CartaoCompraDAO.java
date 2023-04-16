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
import dominio.venda.Bandeira;
import dominio.venda.CartaoCompra;
import dominio.venda.CartaoCredito;
import dominio.venda.Pedido;

public class CartaoCompraDAO implements IDAO {
    private Connection connection = null;
    private boolean controleTransacao = true;

    public CartaoCompraDAO() {
    }

    public CartaoCompraDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void salvar(EntidadeDominio entidade) {
        CartaoCompra cartao = (CartaoCompra) entidade;
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();

        sql.append("INSERT INTO cartoescompras ");
        sql.append("(ccc_nomeTitular, ccc_numero, ccc_cvv, ");
        sql.append("ccc_validade, ccc_ban_id, ccc_valor) ");
        sql.append("VALUES (?,?,?,?,?,?);");

        try {
            if (connection == null) {
                connection = Conexao.getConnectionPostgres();
            } else {
                controleTransacao = false;
            }

            connection.setAutoCommit(false);

            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, cartao.getCartao().getTitular());
            pst.setString(2, cartao.getCartao().getNumero());
            pst.setString(3, cartao.getCartao().getCvv());
            pst.setString(4, cartao.getCartao().getDataValidade());
            pst.setInt(5, cartao.getCartao().getBandeira().getValor());
            pst.setString(6, cartao.getValor().toString());

            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();

            int idCartao = 0;
            if (rs.next())
                idCartao = rs.getInt(1);

            cartao.setId(idCartao);

            if (cartao.isRegistrar()) {
                CartaoCreditoDAO cartaoDao = new CartaoCreditoDAO(connection);
                cartaoDao.salvar(cartao.getCartao());
            }

            connection.commit();

        } catch (SQLException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (controleTransacao)
                try {
                    connection.close();
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }

    }

    @Override
    public void alterar(EntidadeDominio entidade) {
        CartaoCompra cartao = (CartaoCompra) entidade;
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();

        sql.append("UPDATE cartoescompras SET ");
        sql.append("ccc_valor = ? ");
        if (cartao.getPedido() != null)
            sql.append(",ccc_pdd_id = ? ");
        sql.append("WHERE ccc_id = ? ;");

        try {
            if (connection == null) {
                connection = Conexao.getConnectionPostgres();
            } else {
                controleTransacao = false;
            }

            connection.setAutoCommit(false);

            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, cartao.getValor().toString());
            if (cartao.getPedido() == null) {
                pst.setInt(2, cartao.getId());
            } else {
                pst.setInt(2, cartao.getPedido().getId());
                pst.setInt(3, cartao.getId());
            }

            pst.executeUpdate();

            connection.commit();

        } catch (SQLException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (controleTransacao)
                try {

                    connection.close();
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }

    }

    @Override
    public void excluir(EntidadeDominio entidade) {
        CartaoCompra cartao = (CartaoCompra) entidade;
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();

        sql.append("DELETE FROM cartoescompras ");
        sql.append("WHERE ccc_id = ? ;");

        try {

            if (connection == null) {
                connection = Conexao.getConnectionPostgres();
            } else {
                controleTransacao = false;
            }

            connection.setAutoCommit(false);

            pst = connection.prepareStatement(sql.toString());

            pst.setInt(1, cartao.getId());

            pst.executeUpdate();

            connection.commit();

        } catch (SQLException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (controleTransacao)
                try {
                    connection.close();
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<CartaoCompra> consultarPorPedido(Pedido pedido) {
        List<CartaoCompra> ccs = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;

        sql.append("SELECT * FROM cartoescompras ");
        sql.append("WHERE ccc_pdd_id = ? ;");

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
                int idBandeira = rs.getInt("ccc_ban_id");
                Bandeira bandeira = Bandeira.values()[(idBandeira--)];

                String validade = rs.getString("ccc_validade");
                String titular = rs.getString("ccc_nomeTitular");
                String numero = rs.getString("ccc_numero");
                String cvv = rs.getString("ccc_cvv");

                CartaoCredito cartaoCredito = CartaoCredito.builder()
                        .dataValidade(validade)
                        .titular(titular)
                        .numero(numero)
                        .cvv(cvv)
                        .bandeira(bandeira).build();

                BigDecimal valor = new BigDecimal(rs.getString("ccc_valor"));

                CartaoCompra cartaoCompra = CartaoCompra.builder()
                        .cartao(cartaoCredito)
                        .valor(valor).build();

                ccs.add(cartaoCompra);
            }

            return ccs;

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
        CartaoCompra cartao = (CartaoCompra) entidade;
        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;

        sql.append("SELECT * FROM cartoescompras ");
        sql.append("WHERE ccc_id = ? ;");

        try {
            if (connection == null) {
                connection = Conexao.getConnectionPostgres();
            } else {
                controleTransacao = false;
            }

            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

            pst.setInt(1, cartao.getId());

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int idBandeira = rs.getInt("ccc_ban_id");
                Bandeira bandeira = Bandeira.values()[(idBandeira--)];

                String validade = rs.getString("ccc_validade");
                String titular = rs.getString("ccc_nomeTitular");
                String numero = rs.getString("ccc_numero");
                String cvv = rs.getString("ccc_cvv");

                CartaoCredito cartaoCredito = CartaoCredito.builder()
                        .dataValidade(validade)
                        .titular(titular)
                        .numero(numero)
                        .cvv(cvv)
                        .bandeira(bandeira).build();

                BigDecimal valor = new BigDecimal(rs.getString("ccc_valor"));

                cartao = CartaoCompra.builder()
                        .cartao(cartaoCredito)
                        .valor(valor).build();

                int id = rs.getInt("ccc_id");
                cartao.setId(id);
            }

            return cartao;

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
