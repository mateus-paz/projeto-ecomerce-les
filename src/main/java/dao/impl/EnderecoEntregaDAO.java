package dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import dao.IDAO;
import dominio.EntidadeDominio;
import dominio.cliente.Cidade;
import dominio.cliente.Endereco;
import dominio.cliente.Estado;
import dominio.cliente.Pais;
import dominio.cliente.TipoLogradouro;
import dominio.cliente.TipoResidencia;
import dominio.venda.EnderecoEntrega;
import dominio.venda.Frete;

public class EnderecoEntregaDAO implements IDAO {
    private Connection connection = null;
    private boolean controleTransacao = true;
    
    public EnderecoEntregaDAO() {}
    public EnderecoEntregaDAO(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public void salvar(EntidadeDominio entidade) {
        
        EnderecoEntrega endEntrega = (EnderecoEntrega) entidade;
        Endereco endereco = endEntrega.getEndereco();
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();
    
        sql.append("INSERT INTO enderecosEntregas ");
        sql.append("(ede_logradouro, ede_numero, ede_bairro, ");
        sql.append("ede_cep, ede_cidade, ede_estado, ");
        sql.append("ede_pais, ede_complemento, ");
        sql.append("ede_tpr_id, ede_tpl_id, ede_prazo_entrega, ede_valor_frete) ");
        sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
        
        try {
            if(connection == null) {
                connection = Conexao.getConnectionPostgres();
            }else {
                controleTransacao = false;
            }
            
            connection.setAutoCommit(false);
            
            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            
            pst.setString(1, endereco.getLogradouro());
            pst.setString(2, endereco.getNumero());
            pst.setString(3, endereco.getBairro());
            pst.setString(4, endereco.getCep());
            pst.setString(5, endereco.getCidade().getNome());
            pst.setString(6, endereco.getCidade().getEstado().getNome());
            pst.setString(7, endereco.getCidade().getEstado().getPais().getNome());
            pst.setString(8, endereco.getComplemento());
            pst.setInt(9, endereco.getTpResidencia().getValor());
            pst.setInt(10, endereco.getTpLogradouro().getValor());
            pst.setInt(11, endEntrega.getFrete().getPrazo());
            pst.setString(12, endEntrega.getFrete().getValor().toString());
            
            pst.executeUpdate();        
            
            ResultSet rs = pst.getGeneratedKeys();
            
            int idEndereco = 0;
            if (rs.next())
                idEndereco = rs.getInt(1);
            
            endEntrega.setId(idEndereco);
            
            if(endEntrega.isSalvar()) {
                EnderecoDAO endDao = new EnderecoDAO(connection);
                endDao.salvar(endEntrega.getEndereco());
            }
            
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
        EnderecoEntrega endereco = (EnderecoEntrega) entidade;
        StringBuilder sql = new StringBuilder();
        PreparedStatement pst = null;
        
        sql.append("DELETE FROM enderecosEntregas ");
        sql.append("WHERE ede_id = ?;");
        
        try {
            if(connection == null) {
                connection = Conexao.getConnectionPostgres();
            }else {
                controleTransacao = false;
            }   
         
            connection.setAutoCommit(false);
            
            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            
            pst.setInt(1, endereco.getId());
            
            pst.executeUpdate();
            
            connection.commit();
                
        }  catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();    
        }finally{
            if(controleTransacao){
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EntidadeDominio consultarPorId(EntidadeDominio entidade) {
        EnderecoEntrega endereco = (EnderecoEntrega) entidade;
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();
        
        sql.append("SELECT * FROM enderecosEntregas ");
        sql.append("WHERE ede_id = ?;");
        
        try {
            
            if(connection == null) {
                connection = Conexao.getConnectionPostgres();
            }else {
                controleTransacao = false;
            }
            
            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            
            pst.setInt(1, endereco.getId());
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()) {
                
                TipoResidencia tpResidencia = null;
                if(rs.getInt("ede_tpr_id") == 1) {
                    tpResidencia = TipoResidencia.CASA;
                }else if(rs.getInt("ede_tpr_id") == 2) {
                    tpResidencia = TipoResidencia.APARTAMENTO;
                }else if(rs.getInt("ede_tpr_id") == 3) {
                    tpResidencia = TipoResidencia.OUTRO;
                }
                
                TipoLogradouro tpLogradouro = null;
                if(rs.getInt("ede_tpl_id") == 1) {
                    tpLogradouro = TipoLogradouro.RUA;
                }else if(rs.getInt("ede_tpl_id") == 2) {
                    tpLogradouro = TipoLogradouro.AVENIDA;
                }else if(rs.getInt("ede_tpl_id") == 3) {
                    tpLogradouro = TipoLogradouro.OUTRO;
                }
                
                Endereco end = new Endereco( 
                        tpResidencia,
                        tpLogradouro,
                        rs.getString("ede_logradouro"),
                        rs.getString("ede_numero"), 
                        rs.getString("ede_bairro"),
                        rs.getString("ede_cep"),
                        new Cidade(rs.getString("ede_cidade"), 
                                new Estado(rs.getString("ede_estado"), 
                                        new Pais(rs.getString("ede_pais")))),
                        rs.getString("ede_complemento")
                        );
                
                Frete frete = new Frete(
                            rs.getInt("ede_prazo_entrega"),
                            new BigDecimal(rs.getString("ede_valor_frete"))
                        );
                
                endereco = new EnderecoEntrega(end, frete);
                
                endereco.setId(rs.getInt("ede_id"));
            }
                        
            return endereco;
            
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
        
        return null;
        
    }
}
