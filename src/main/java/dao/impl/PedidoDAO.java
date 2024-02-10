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
import java.util.Map;
import java.util.TreeMap;

import dao.IDAO;
import dominio.EntidadeDominio;
import dominio.cliente.Cliente;
import dominio.venda.CartaoCompra;
import dominio.venda.Cupom;
import dominio.venda.EnderecoEntrega;
import dominio.venda.ItemCarrinho;
import dominio.venda.ItemPedido;
import dominio.venda.Pedido;
import dominio.venda.StatusPedido;

public class PedidoDAO implements IDAO {
    private Connection connection = null;
    private boolean controleTransacao = true;

    public PedidoDAO() {
    }

    public PedidoDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void salvar(EntidadeDominio entidade) {
        Pedido pedido = (Pedido) entidade;
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();

        sql.append("INSERT INTO pedidos ");
        sql.append("(pdd_cli_id, pdd_valor_total, pdd_stp_id, pdd_ede_id) ");
        sql.append("VALUES (?,?,?,?) ");

        try {
            if (connection == null) {
                connection = Conexao.getConnectionPostgres();
            } else {
                controleTransacao = false;
            }

            connection.setAutoCommit(false);

            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

            pst.setInt(1, pedido.getCliente().getId());
            pst.setString(2, pedido.getCarrinho().getValorTotal().toString());
            pst.setInt(3, 1);
            pst.setInt(4, pedido.getEndereco().getId());

            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();

            int idPedido = 0;

            if (rs.next()) {
                idPedido = rs.getInt(1);
                pedido.setDtCadastro(rs.getDate("pdd_data").toLocalDate());
            }
            pedido.setId(idPedido);

            CartaoCompraDAO ccDao = new CartaoCompraDAO(connection);
            for (CartaoCompra cc : pedido.getCartoes()) {
                cc.setPedido(pedido);
                ccDao.alterar(cc);
            }

            CupomDAO cupomDao = new CupomDAO(connection);
            for (Cupom cpm : pedido.getCupons()) {
                cpm.setPedido(pedido);
                cupomDao.alterar(cpm);
            }

            ItemPedidoDAO itemPedidoDao = new ItemPedidoDAO(connection);
            for (ItemCarrinho i : pedido.getCarrinho().getItens()) {
                ItemPedido item = new ItemPedido(pedido);

                item.setLivro(i.getLivro());
                item.setQuantidade(i.getQuantidade());
                item.setValorVenda(i.getValorVenda());

                itemPedidoDao.salvar(item);
                pedido.getItens().add(item);
            }

            ItemCarrinhoDAO itemCarrinhoDao = new ItemCarrinhoDAO(connection);
            itemCarrinhoDao.esvaziarCarrinho(pedido.getCarrinho());

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
        Pedido pedido = (Pedido) entidade;
        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;

        sql.append("UPDATE pedidos SET ");
        sql.append("pdd_stp_id = ? ");
        sql.append("WHERE pdd_id = ?");

        try {
            if (connection == null) {
                connection = Conexao.getConnectionPostgres();
            } else {
                controleTransacao = false;
            }

            connection.setAutoCommit(false);

            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

            pst.setInt(1, pedido.getStatus().getValor());
            pst.setInt(2, pedido.getId());

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
        // TODO Auto-generated method stub

    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        List<EntidadeDominio> pedidos = new ArrayList<>();
        Pedido pedido = (Pedido) entidade;
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT * FROM pedidos ");
        if (pedido.getCliente() != null)
            sql.append("WHERE pdd_cli_id = ? ;");

        try {

            if (connection == null) {
                connection = Conexao.getConnectionPostgres();
            } else {
                controleTransacao = false;
            }

            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

            if (pedido.getCliente() != null)
                pst.setInt(1, pedido.getCliente().getId());

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int idStatus = rs.getInt("pdd_stp_id");
                StatusPedido status = StatusPedido.values()[(idStatus--)];

                BigDecimal valorTotal = new BigDecimal(rs.getString("pdd_valor_total"));
                LocalDate dtCadastro = rs.getDate("pdd_data").toLocalDate();

                Pedido pdd = Pedido.builder()
                        .status(status)
                        .valorTotal(valorTotal).build();

                pdd.setDtCadastro(dtCadastro);

                pdd.setId(rs.getInt("pdd_id"));

                if (pedido.getCliente() != null) {
                    Cliente cliente = pedido.getCliente();
                    pdd.setCliente(cliente);

                }

                pedidos.add(pdd);
            }

            return pedidos;

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

    public Map<String, Map<String, Integer>> consultarNumeroVendas(String inicio, String fim) {

        Map<String, Map<String, Integer>> dados = new TreeMap<>();

        PreparedStatement pst = null;

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("DISTINCT(pdd_data) as datas, ");
        sql.append("cat_descricao as categoria, ");
        sql.append("sum(itp_quantidade) as quantidade ");
        sql.append("FROM ");
        sql.append("pedidos ");
        sql.append("inner join itensPedidos on (pdd_id = itp_pdd_id) ");
        sql.append("inner join livros on (lvr_id = itp_lvr_id) ");
        sql.append("inner join categorias_livros on (lvr_id = ctl_lvr_id) ");
        sql.append("right join categorias on (cat_id = ctl_cat_id) ");
        sql.append("WHERE ");
        sql.append("pdd_data BETWEEN ? AND ? ");
        sql.append("GROUP BY ");
        sql.append("datas, categoria;");

        try {

            if (connection == null) {
                connection = Conexao.getConnectionPostgres();
            } else {
                controleTransacao = false;
            }

            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

            pst.setDate(1, Date.valueOf(inicio));
            pst.setDate(2, Date.valueOf(fim));

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                String data = rs.getString("datas");

                String categoria = rs.getString("categoria");

                int quantidade = rs.getInt("quantidade");

                if (dados.keySet().contains(data)) {
                    Map<String, Integer> categoriaQuantidade = dados.get(data);

                    categoriaQuantidade.put(categoria, quantidade);

                    dados.put(data, categoriaQuantidade);

                } else {
                    Map<String, Integer> categoriaQuantidade = new TreeMap<>();
                    categoriaQuantidade.put(categoria, quantidade);

                    dados.put(data, categoriaQuantidade);
                }

            }

            return dados;

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

    @Override
    public EntidadeDominio consultarPorId(EntidadeDominio entidade) {
        Pedido pedido = (Pedido) entidade;
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT * FROM pedidos ");
        sql.append("WHERE pdd_id = ? ;");

        try {

            if (connection == null) {
                connection = Conexao.getConnectionPostgres();
            } else {
                controleTransacao = false;
            }

            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

            pst.setInt(1, pedido.getId());

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                int idStatus = rs.getInt("pdd_stp_id");
                StatusPedido status = StatusPedido.values()[(idStatus--)];

                BigDecimal valorTotal = new BigDecimal(rs.getString("pdd_valor_total"));
                LocalDate dtCadastro = rs.getDate("pdd_data").toLocalDate();

                Pedido pdd = Pedido.builder()
                        .status(status)
                        .valorTotal(valorTotal).build();

                pdd.setDtCadastro(dtCadastro);

                pedido.setId(rs.getInt("pdd_id"));

                ClienteDAO clienteDao = new ClienteDAO(connection);
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("pdd_cli_id"));
                cliente = (Cliente) clienteDao.consultarPorId(cliente);

                pedido.setCliente(cliente);

                ItemPedidoDAO itemPedidoDao = new ItemPedidoDAO(connection);
                List<ItemPedido> itens = new ArrayList<>();
                for (EntidadeDominio e : itemPedidoDao.consultar(pedido))
                    itens.add((ItemPedido) e);

                pedido.setItens(itens);

                EnderecoEntregaDAO endDao = new EnderecoEntregaDAO(connection);
                EnderecoEntrega endereco = new EnderecoEntrega();
                endereco.setId(rs.getInt("pdd_ede_id"));
                endereco = (EnderecoEntrega) endDao.consultarPorId(endereco);
                pedido.setEndereco(endereco);

                CartaoCompraDAO ccDao = new CartaoCompraDAO(connection);
                List<CartaoCompra> cartoes = ccDao.consultarPorPedido(pedido);
                pedido.setCartoes(cartoes);

                CupomDAO crDao = new CupomDAO(connection);
                List<Cupom> cupons = crDao.consultarPorPedido(pedido);
                pedido.setCupons(cupons);

            }

            return pedido;

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
