package dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.IDAO;
import dominio.EntidadeDominio;
import dominio.cliente.Cliente;
import dominio.venda.Cupom;
import dominio.venda.Pedido;
import dominio.venda.TipoCupom;

public class CupomDAO implements IDAO {
    private Connection connection = null;
    private boolean controleTransacao = true;

    public CupomDAO() {
    }

    public CupomDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void salvar(EntidadeDominio entidade) {
        Cupom cupom = (Cupom) entidade;
        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;

        sql.append("INSERT INTO cupons ");
        sql.append("(cpm_codigo, cpm_valor, cpm_validade, cpm_tpc_id, cpm_cli_id) ");
        sql.append("VALUES (?,?,?,?,?);");

        try {
            if (connection == null) {
                connection = Conexao.getConnectionPostgres();
            } else {
                controleTransacao = false;
            }

            connection.setAutoCommit(false);

            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, cupom.getCodigo());
            pst.setString(2, cupom.getValor().toString());
            pst.setDate(3, Date.valueOf(cupom.getValidade()));
            pst.setInt(4, cupom.getTpCupom().getValor());
            pst.setInt(5, cupom.getCliente().getId());

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
        Cupom cupom = (Cupom) entidade;
        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;

        sql.append("UPDATE cupons SET ");
        sql.append("cpm_isResgatado = true, cpm_pdd_id = ? ");
        sql.append("WHERE cpm_id = ?");

        try {
            if (connection == null) {
                connection = Conexao.getConnectionPostgres();
            } else {
                controleTransacao = false;
            }

            connection.setAutoCommit(false);

            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

            pst.setInt(1, cupom.getPedido().getId());
            pst.setInt(2, cupom.getId());

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
    public void excluir(EntidadeDominio entidade) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        Cliente cliente = (Cliente) entidade;
        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;
        List<EntidadeDominio> cupons = new ArrayList<>();

        sql.append("SELECT * ");
        sql.append("FROM cupons ");
        sql.append("WHERE cpm_cli_id = ? and ");
        sql.append("cpm_isResgatado = false ;");

        try {

            if (connection == null) {
                connection = Conexao.getConnectionPostgres();
            } else {
                controleTransacao = false;
            }

            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

            pst.setInt(1, cliente.getId());

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String codigo = rs.getString("cpm_codigo");
                BigDecimal valor = new BigDecimal(rs.getString("cpm_valor"));
                LocalDate validade = rs.getDate("cpm_validade").toLocalDate();

                int idTpCupom = rs.getInt("cpm_tpc_id");
                TipoCupom tpCupom = TipoCupom.values()[(idTpCupom--)];

                Cupom cupom = Cupom.builder()
                        .codigo(codigo)
                        .valor(valor)
                        .validade(validade)
                        .tpCupom(tpCupom)
                        .cliente(cliente).build();

                int id = rs.getInt("cpm_id");
                cupom.setId(id);

                cupons.add(cupom);
            }

            return cupons;

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

    public boolean validarCupom(Cupom cupom) {

        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;

        sql.append("SELECT * ");
        sql.append("FROM cupons ");
        sql.append("WHERE cpm_codigo = ? and ");
        sql.append("cpm_cli_id = ? and ");
        sql.append("cpm_isResgatado = false ;");

        try {

            if (connection == null) {
                connection = Conexao.getConnectionPostgres();
            } else {
                controleTransacao = false;
            }

            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, cupom.getCodigo());
            pst.setInt(2, cupom.getCliente().getId());

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                if (!rs.getDate("cpm_validade").toLocalDate().isAfter(LocalDate.now()))
                    return false;

                BigDecimal valor = new BigDecimal(rs.getString("cpm_valor"));

                TipoCupom tpCupom = null;
                if (rs.getInt("cpm_tpc_id") == 1) {
                    tpCupom = TipoCupom.TROCA;
                } else if (rs.getInt("cpm_tpc_id") == 2) {

                    tpCupom = TipoCupom.PROMOCIONAL;
                }

                cupom.setValor(valor);
                cupom.setTpCupom(tpCupom);

                cupom.setId(rs.getInt("cpm_id"));

                return true;
            }

            return false;

        } catch (SQLException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
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
    public EntidadeDominio consultarPorId(EntidadeDominio entidade) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<Cupom> consultarPorPedido(Pedido pedido) {
        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;
        List<Cupom> cupons = new ArrayList<>();

        sql.append("SELECT * ");
        sql.append("FROM cupons ");
        sql.append("WHERE cpm_pdd_id = ? ");

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
                String codigo = rs.getString("cpm_codigo");
                BigDecimal valor = new BigDecimal(rs.getString("cpm_valor"));
                LocalDate validade = rs.getDate("cpm_validade").toLocalDate();

                int idTpCupom = rs.getInt("cpm_tpc_id");
                TipoCupom tpCupom = TipoCupom.values()[(idTpCupom--)];

                Cupom cupom = Cupom.builder()
                        .codigo(codigo)
                        .valor(valor)
                        .validade(validade)
                        .tpCupom(tpCupom)
                        .pedido(pedido).build();

                int id = rs.getInt("cpm_id");
                cupom.setId(id);

                cupons.add(cupom);
            }

            return cupons;

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
