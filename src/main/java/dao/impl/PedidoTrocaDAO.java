package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.IDAO;
import dominio.EntidadeDominio;
import dominio.cliente.Notificacao;
import dominio.venda.Cupom;
import dominio.venda.ItemTroca;
import dominio.venda.Pedido;
import dominio.venda.PedidoTroca;

public class PedidoTrocaDAO implements IDAO {
    private Connection connection;
    private boolean controleTransacao = true;
    public PedidoTrocaDAO() {}
    
    public PedidoTrocaDAO( Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public void salvar(EntidadeDominio entidade) {
        PedidoTroca pedido = (PedidoTroca) entidade;
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();
        
        sql.append("INSERT INTO pedidosTrocas ");
        sql.append("(pdt_pdd_id, pdt_cli_id) ");
        sql.append("VALUES (?, ?)");
        
        try {
            if(connection == null){
                connection = Conexao.getConnectionPostgres();
            }else {
                controleTransacao = false;
            }
            
            connection.setAutoCommit(false);
            
            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            
            pst.setInt(1, pedido.getPedido().getId());
            pst.setInt(2, pedido.getPedido().getCliente().getId());
            
            pst.executeUpdate();
        
            ResultSet rs = pst.getGeneratedKeys();
            
            int idPedido = 0;
            LocalDate data = LocalDate.now();
            if(rs.next()) {
                idPedido = rs.getInt(1);
                data = rs.getDate("pdt_data").toLocalDate();
            }
           
            pedido.setId(idPedido);
            pedido.setDtCadastro(data);
            
            ItemTrocaDAO itemDao = new ItemTrocaDAO(connection);
            
            for(ItemTroca item : pedido.getItens()) {
                item.setPedido(pedido);
                itemDao.salvar(item);
                
            }
            
            PedidoDAO pddDao = new PedidoDAO(connection);
            pddDao.alterar(pedido.getPedido());
            
            connection.commit();
            
        }catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();    
        }finally{
            if(controleTransacao){
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
        PedidoTroca pedido = (PedidoTroca) entidade;
        
        try {
            if(connection == null) {
                connection = Conexao.getConnectionPostgres();
            }else {
                controleTransacao = false;
            }
            
            connection.setAutoCommit(false);
            
            PedidoDAO pedidoDao = new PedidoDAO(connection);
            Pedido pdd = pedido.getPedido();
            pedidoDao.alterar( pdd );
            
            if(pedido.getPedido().getStatus().getValor() == 5) {
                NotificacaoDAO notificacaoDao = new NotificacaoDAO(connection);
                Notificacao notificacao = new Notificacao(
                        "Solicitação de Troca Aprovada!", 
                        pedido.getPedido().getCliente().getUsuario()
                        );
                
                notificacaoDao.salvar(notificacao);
            }else if(pedido.getPedido().getStatus().getValor() == 7) {
                CupomDAO cupomDao = new CupomDAO(connection);
                Cupom cupom = pedido.getCupom();
                cupom.setPedido(pedido.getPedido());
                cupom.setCliente(pedido.getPedido().getCliente());
                cupomDao.salvar(cupom);
                
                NotificacaoDAO notificacaoDao = new NotificacaoDAO(connection);
                Notificacao notificacao = new Notificacao(
                        "Novo cupom de troca disponível. Clique em Sua conta > cupons para conferir!", 
                        pedido.getPedido().getCliente().getUsuario()
                        );
                
                notificacaoDao.salvar(notificacao);
                
            }
            
            connection.commit();        

        } catch (SQLException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            if(controleTransacao)
                try {
                    connection.close();
                }catch(SQLException e) {
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
        PedidoTroca pedido = (PedidoTroca) entidade;
        List<EntidadeDominio> pedidos = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;
        
        sql.append("SELECT * ");
        sql.append("FROM pedidosTrocas ");
        if(pedido.getPedido() != null)
            sql.append("WHERE pdt_cli_id = ? ");
        
        try {
            
            if(connection == null) {
                connection = Conexao.getConnectionPostgres();
            }else {
                controleTransacao = false;
            }
            
            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            
            if(pedido.getPedido() != null)
                pst.setInt(1, pedido.getPedido().getCliente().getId());
            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                
                PedidoTroca pdt = new PedidoTroca();
                pdt.setId(rs.getInt("pdt_id"));
                               
                PedidoDAO pedidoDao = new PedidoDAO(connection);
                Pedido pdd = new Pedido();
                pdd.setId(rs.getInt("pdt_pdd_id"));
                pdd = (Pedido) pedidoDao.consultarPorId(pdd);
                
                ItemTrocaDAO itemTrocaDao = new ItemTrocaDAO(connection);
                List<ItemTroca> itens = new ArrayList<>();
                for(EntidadeDominio ent : itemTrocaDao.consultar(pdt))
                    itens.add((ItemTroca) ent);
                                
                pdt.setPedido(pdd);
                pdt.setItens(itens);
                pdt.setDtCadastro(rs.getDate("pdt_data").toLocalDate());
                                                
                pedidos.add(pdt);        
            }
            
            return pedidos;
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
            
        } finally{
            if(controleTransacao){
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
        PedidoTroca pedido = (PedidoTroca) entidade;
        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;
        
        sql.append("SELECT * ");
        sql.append("FROM pedidosTrocas ");
        sql.append("WHERE pdt_id = ? ");
        
        try {
            
            if(connection == null) {
                connection = Conexao.getConnectionPostgres();
            }else {
                controleTransacao = false;
            }
            
            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            
            pst.setInt(1, pedido.getId());
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()) {

                pedido.setId(rs.getInt("pdt_id"));
                pedido.setDtCadastro(rs.getDate("pdt_data").toLocalDate());
                
                ItemTrocaDAO itemTrocaDao = new ItemTrocaDAO(connection);
                List<EntidadeDominio> retorno = itemTrocaDao.consultar(pedido);
                List<ItemTroca> itens = new ArrayList<>();
                
                for(EntidadeDominio ent : retorno)
                    itens.add((ItemTroca) ent);
                
                pedido.setItens(itens);
                
                PedidoDAO pedidoDao = new PedidoDAO(connection);
                Pedido pdd = new Pedido();
                pdd.setId(rs.getInt("pdt_pdd_id"));
                
                pedido.setPedido((Pedido) pedidoDao.consultarPorId(pdd));
                
            }
            
            return pedido;
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
            
        } finally{
            if(controleTransacao){
                try {
                    pst.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public PedidoTroca consultarPorPedido(PedidoTroca pedido) {
        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;
        
        sql.append("SELECT * ");
        sql.append("FROM pedidosTrocas ");
        sql.append("WHERE pdt_pdd_id = ? ");
        
        try {
            
            if(connection == null) {
                connection = Conexao.getConnectionPostgres();
            }else {
                controleTransacao = false;
            }
            
            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            
            pst.setInt(1, pedido.getPedido().getId());
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()) {

                pedido.setId(rs.getInt("pdt_id"));
                pedido.setDtCadastro(rs.getDate("pdt_data").toLocalDate());
                
                ItemTrocaDAO itemTrocaDao = new ItemTrocaDAO();
                List<EntidadeDominio> retorno = itemTrocaDao.consultar(pedido);
                List<ItemTroca> itens = new ArrayList<>();
                
                for(EntidadeDominio ent : retorno)
                    itens.add((ItemTroca) ent);
                
                pedido.setItens(itens);
                       
            }
            
            return pedido;
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
            
        } finally{
            if(controleTransacao){
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
