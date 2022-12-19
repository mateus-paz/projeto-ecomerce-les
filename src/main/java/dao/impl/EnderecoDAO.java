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
import dominio.cliente.Cidade;
import dominio.cliente.Cliente;
import dominio.cliente.Endereco;
import dominio.cliente.Estado;
import dominio.cliente.Pais;
import dominio.cliente.TipoLogradouro;
import dominio.cliente.TipoResidencia;

public class EnderecoDAO implements IDAO {
	private Connection connection = null;
	private boolean controleTransacao = true; 
	
	public EnderecoDAO(Connection connection) {
		this.connection = connection;
	}
	
	public EnderecoDAO() {}

	@Override
	public void salvar(EntidadeDominio entidade) {
		Endereco endereco = (Endereco) entidade;
		PreparedStatement pst = null;
		StringBuilder sql = new StringBuilder();
	
		sql.append("INSERT INTO enderecos ");
		sql.append("(end_logradouro, end_numero, end_bairro, ");
		sql.append("end_cep, end_cidade, end_estado, ");
		sql.append("end_pais, end_complemento, end_cli_id, ");
		sql.append("end_tpr_id, end_tpl_id, end_nome_identificacao) ");
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
			pst.setInt(9, endereco.getCliente().getId());
			pst.setInt(10, endereco.getTpResidencia().getValor());
			pst.setInt(11, endereco.getTpLogradouro().getValor());
			pst.setString(12, endereco.getNomeIdentificacao());
			
			pst.executeUpdate();		
			
			ResultSet rs = pst.getGeneratedKeys();
			
			int idEndereco = 0;
			if (rs.next())
				idEndereco = rs.getInt(1);
			
			endereco.setId(idEndereco);
			
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
			
	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		Endereco endereco = (Endereco) entidade;
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM enderecos WHERE end_id = ?");
        
        try {
            if(connection == null) {
                connection = Conexao.getConnectionPostgres();
            }else {
                controleTransacao = false;
            }
            
        	connection.setAutoCommit(false);
        	
        	pst = connection.prepareStatement(sql.toString());
        	
        	pst.setInt(1, endereco.getId());
        	
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
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
    	List<EntidadeDominio> enderecos = new ArrayList<>();
    	PreparedStatement pst = null;
    	StringBuilder sql = new StringBuilder();
        
        sql.append("SELECT * FROM enderecos ");
        sql.append("WHERE end_cli_id = ? ;");
        
    	try {
    	    if(connection == null) {
                connection = Conexao.getConnectionPostgres();
            }else {
                controleTransacao = false;
            }
    	    
//    		sql.append("SELECT * FROM _enderecos WHERE end_cli_id ="+idCliente+";");
    		
    		pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
    		
    		pst.setInt(1, cliente.getId());
    		
    		ResultSet rs = pst.executeQuery();
    		
    		while(rs.next()) {
    			
    		    TipoResidencia tpResidencia = null;
    		    if(rs.getInt("end_tpr_id") == 1) {
    		        tpResidencia = TipoResidencia.CASA;
    		    }else if(rs.getInt("end_tpr_id") == 2) {
                    tpResidencia = TipoResidencia.APARTAMENTO;
    		    }else if(rs.getInt("end_tpr_id") == 3) {
                    tpResidencia = TipoResidencia.OUTRO;
    		    }
    		    
    		    TipoLogradouro tpLogradouro = null;
    		    if(rs.getInt("end_tpl_id") == 1) {
    		        tpLogradouro = TipoLogradouro.RUA;
                }else if(rs.getInt("end_tpl_id") == 2) {
                    tpLogradouro = TipoLogradouro.AVENIDA;
                }else if(rs.getInt("end_tpl_id") == 3) {
                    tpLogradouro = TipoLogradouro.OUTRO;
                }
    		   
    			Endereco endereco = new Endereco( 
                        tpResidencia,
                        tpLogradouro,
    					rs.getString("end_logradouro"),
    					rs.getString("end_numero"), 
    					rs.getString("end_bairro"),
    					rs.getString("end_cep"),
    					new Cidade(rs.getString("end_cidade"), 
    							new Estado(rs.getString("end_estado"), 
    									new Pais(rs.getString("end_pais")))),
    					rs.getString("end_nome_identificacao"),
    					rs.getString("end_complemento")
    					
    					);
    			
    			endereco.setId(rs.getInt("end_id"));
    			
    			enderecos.add(endereco);
    		}
    		    		
    		return enderecos;
    		
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
	
    @Override
    public EntidadeDominio consultarPorId(EntidadeDominio entidade) {
        Endereco endereco = (Endereco) entidade;
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();
        
        sql.append("SELECT * FROM enderecos ");
        sql.append("WHERE end_id = ?;");
        
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
                if(rs.getInt("end_tpr_id") == 1) {
                    tpResidencia = TipoResidencia.CASA;
                }else if(rs.getInt("end_tpr_id") == 2) {
                    tpResidencia = TipoResidencia.APARTAMENTO;
                }else if(rs.getInt("end_tpr_id") == 3) {
                    tpResidencia = TipoResidencia.OUTRO;
                }
                
                TipoLogradouro tpLogradouro = null;
                if(rs.getInt("end_tpl_id") == 1) {
                    tpLogradouro = TipoLogradouro.RUA;
                }else if(rs.getInt("end_tpl_id") == 2) {
                    tpLogradouro = TipoLogradouro.AVENIDA;
                }else if(rs.getInt("end_tpl_id") == 3) {
                    tpLogradouro = TipoLogradouro.OUTRO;
                }
               
                endereco = new Endereco( 
                        tpResidencia,
                        tpLogradouro,
                        rs.getString("end_logradouro"),
                        rs.getString("end_numero"), 
                        rs.getString("end_bairro"),
                        rs.getString("end_cep"),
                        new Cidade(rs.getString("end_cidade"), 
                                new Estado(rs.getString("end_estado"), 
                                        new Pais(rs.getString("end_pais")))),
                        rs.getString("end_nome_identificacao"),
                        rs.getString("end_complemento")
                        
                        );
                
                endereco.setId(rs.getInt("end_id"));
                
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
