package dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.IDAO;
import dominio.EntidadeDominio;
import dominio.venda.Pagamento;

public class PagamentoDAO implements IDAO {
    private Connection connection = null;
    private boolean controleTransacao = true;
    
    public PagamentoDAO(){}
    
    public PagamentoDAO(Connection connection){
        this.connection = connection;
    }
    
    @Override
    public void salvar(EntidadeDominio entidade) {
        Pagamento pagamento = (Pagamento) entidade;
        
        try {
            
            if(connection == null) {
                connection = Conexao.getConnectionPostgres();
            }else {
                controleTransacao = false;
            }
            
            if(pagamento.getNovoCartao() != null) {
                CartaoCompraDAO ccDao = new CartaoCompraDAO(connection);
                ccDao.salvar(pagamento.getNovoCartao());
            }
            
        } catch (SQLException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            
        } finally{
            if(controleTransacao)
                try {
                    connection.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
        }
        
        
    }

    @Override
    public void alterar(EntidadeDominio entidade) {
        Pagamento pagamento = (Pagamento) entidade;
        
        try {
            if(connection == null) {
                connection = Conexao.getConnectionPostgres();
            }else {
                controleTransacao = false;
            }
            
            if(pagamento.getNovoCartao() != null) {
                CartaoCompraDAO ccDao = new CartaoCompraDAO(connection);
                ccDao.alterar(pagamento.getNovoCartao());
            }
            
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
        Pagamento pagamento = (Pagamento) entidade;
        
        try {
            
            if(connection == null) {
                connection = Conexao.getConnectionPostgres();
            }else {
                controleTransacao = false;
            }
        
            if(pagamento.getNovoCartao() != null) {
                CartaoCompraDAO ccDao = new CartaoCompraDAO(connection);
                ccDao.excluir(pagamento.getNovoCartao());
            }

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
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EntidadeDominio consultarPorId(EntidadeDominio entidade) {
        // TODO Auto-generated method stub
        return null;
    }

}
