package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.IDAO;
import dominio.EntidadeDominio;
import dominio.cliente.Notificacao;
import dominio.cliente.Usuario;

public class NotificacaoDAO implements IDAO {
    private Connection connection;
    private boolean controleTransacao = true;
    
    public NotificacaoDAO() {}
    
    public NotificacaoDAO(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public void salvar(EntidadeDominio entidade) {
        Notificacao notificacao = (Notificacao) entidade;
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();
        
        sql.append("INSERT INTO notificacoes ");
        sql.append("(ntf_mensagem, ntf_usr_id) ");
        sql.append("VALUES (?, ?); ");
        
        try {
            if(connection == null) {
                connection = Conexao.getConnectionPostgres();
            }else {
                controleTransacao = false;
            }
            
            connection.setAutoCommit(false);
            
            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            
            pst.setString(1, notificacao.getMensagem());
            pst.setInt(2, notificacao.getUsuario().getId());
            
            pst.executeUpdate();
                        
            connection.commit();     
            
        } catch (Exception e) {
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
        Usuario usuario = (Usuario) entidade;
        List<EntidadeDominio> notificacoes = new ArrayList<>();
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();
        
        sql.append("SELECT * ");
        sql.append("FROM notificacoes ");
        sql.append("WHERE ntf_usr_id = ? ;");
        
        try {
            if(connection == null) {
                connection = Conexao.getConnectionPostgres();
            }else {
                controleTransacao = false;
            }
            
            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            
            pst.setInt(1, usuario.getId());
            
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                Notificacao not = new Notificacao(rs.getString("ntf_mensagem"), usuario);
                
                not.setId(rs.getInt("ntf_id"));
                not.setDtCadastro(rs.getDate("ntf_data_cadastro").toLocalDate());
                
                notificacoes.add(not);
            }

            return notificacoes;
            
        } catch (Exception e) {
            
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
        
        return null;
        
    }

    @Override
    public EntidadeDominio consultarPorId(EntidadeDominio entidade) {
        // TODO Auto-generated method stub
        return null;
    }

}
