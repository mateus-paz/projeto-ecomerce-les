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
import dominio.venda.ItemTroca;
import dominio.venda.PedidoTroca;

public class ItemTrocaDAO implements IDAO {
    private Connection connection = null;
    private boolean controleTransacao = true;
    
    public ItemTrocaDAO() {
        connection = null;
    }
    
    public ItemTrocaDAO(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public void salvar(EntidadeDominio entidade) {
        ItemTroca item = (ItemTroca) entidade;
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();
        
        sql.append("INSERT INTO itensTrocas ");
        sql.append("(itt_pdt_id, itt_lvr_id, itt_quantidade, ");
        sql.append("itt_valor_unitario) ");
        sql.append("VALUES (?, ?, ?, ?);");
                
        try {
            if(connection == null){
                connection = Conexao.getConnectionPostgres();
            }else {
                controleTransacao = false;
            }
            
            connection.setAutoCommit(false);
            
            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            
            pst.setInt(1, item.getPedido().getId());
            pst.setInt(2, item.getLivro().getId());
            pst.setInt(3, item.getQuantidade());
            pst.setString(4, item.getValorVenda().toString());
            
            pst.executeUpdate();
        
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
        // TODO Auto-generated method stub

    }

    @Override
    public void excluir(EntidadeDominio entidade) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        PedidoTroca pedido = (PedidoTroca) entidade;
        List<EntidadeDominio> itens = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;
        
        sql.append("SELECT * ");
        sql.append("FROM itensTrocas ");
        sql.append("WHERE itt_pdt_id = ? ;");
        try {
            
            if(connection == null) {
                connection = Conexao.getConnectionPostgres();
            }else {
                controleTransacao = false;
            }
            
            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            
            pst.setInt(1, pedido.getId());
            
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                LivroDAO livroDao = new LivroDAO(connection);
                Livro livro = new Livro();
                livro.setId(rs.getInt("itt_lvr_id"));
                
                ItemTroca item = new ItemTroca(
                            (Livro) livroDao.consultarPorId(livro),
                            rs.getInt("itt_quantidade"),
                            new BigDecimal(rs.getString("itt_valor_unitario"))
                        );
                
                itens.add(item);        
            }
            
            return itens;
            
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
        // TODO Auto-generated method stub
        return null;
    }

}
