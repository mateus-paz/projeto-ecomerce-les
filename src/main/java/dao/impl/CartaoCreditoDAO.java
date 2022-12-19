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
import dominio.cliente.Cliente;
import dominio.venda.Bandeira;
import dominio.venda.CartaoCredito;

public class CartaoCreditoDAO implements IDAO {
	private Connection connection = null;
	private boolean controleTransacao = true;
	
	public CartaoCreditoDAO() {}
	
	public CartaoCreditoDAO(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public void salvar(EntidadeDominio entidade) {
		CartaoCredito cartao = (CartaoCredito) entidade;
		PreparedStatement pst = null;
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO cartoescredito ");
		sql.append("(ctc_nomeIdentificacao, ctc_nomeTitular, ctc_numero, ");
		sql.append("ctc_cvv, ctc_validade, ctc_ban_id, ctc_cli_id)");
		sql.append("VALUES (?,?,?,?,?,?,?);");
		
		try {
			if(connection == null) {
				connection = Conexao.getConnectionPostgres();
			}else {
				controleTransacao = false;
			}
			
			connection.setAutoCommit(false);
			
    		pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
    		
    		pst.setString(1, cartao.getNomeIdentificacao());
    		pst.setString(2, cartao.getTitular());
    		pst.setString(3, cartao.getNumero());
    		pst.setString(4, cartao.getCvv());
    		pst.setString(5, cartao.getDataValidade());
    		pst.setInt(6, cartao.getBandeira().getValor());
    		pst.setInt(7, cartao.getCliente().getId());
    				
    		pst.executeUpdate();
    		
    		ResultSet rs = pst.getGeneratedKeys();
    		
			int idCartao=0;
			if(rs.next())
			    idCartao = rs.getInt(1);
			
			cartao.setId(idCartao);
			
			connection.commit();		

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
		    if(controleTransacao)
		        try {
    			
	    			connection.close();
	    			pst.close();
		        }catch(SQLException e) {
		            e.printStackTrace();
		        }
		}
	
	}

	@Override
	public void alterar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub

	}

	@Override
	public void excluir(EntidadeDominio entidade) {
	    CartaoCredito cartao = (CartaoCredito) entidade;
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();
        
        sql.append("DELETE FROM cartoescredito ");
        sql.append("WHERE ctc_id = ? ;");
        
        try {
            
            if(connection == null) {
                connection = Conexao.getConnectionPostgres();
            }else {
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
        } finally{
            if(controleTransacao)
                try {
                    connection.close();
                    pst.close();
                }catch(SQLException e) {
                    e.printStackTrace();
                }
        }   
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
	    Cliente cliente = (Cliente) entidade;
	    List<EntidadeDominio> cartoes = new ArrayList<>();
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();
        
        sql.append("SELECT * FROM cartoescredito ");
        sql.append("WHERE ctc_cli_id = ?;");
        
        try {
            if(connection == null) {
                connection = Conexao.getConnectionPostgres();
            }else {
                controleTransacao = false;
            }
            
            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            
            pst.setInt(1, cliente.getId());
            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                
                Bandeira bandeira = null;
                if(rs.getInt("ctc_ban_id") == 1) {
                    bandeira = Bandeira.VISA;
                }else if(rs.getInt("ctc_ban_id") == 2) {
                    bandeira = Bandeira.MASTERCARD;
                }
                
                CartaoCredito cartao = new CartaoCredito( 
                        rs.getString("ctc_nomeIdentificacao"),
                        rs.getString("ctc_validade"),
                        rs.getString("ctc_nomeTitular"), 
                        rs.getString("ctc_numero"),
                        rs.getString("ctc_cvv"),
                        bandeira
                        );
                
                cartao.setId(rs.getInt("ctc_id"));
                
                cartoes.add(cartao);
            }
                        
            return cartoes;
            
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
        CartaoCredito cartao = (CartaoCredito) entidade;
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();
        
        sql.append("SELECT * FROM cartoescredito ");
        sql.append("WHERE ctc_id = ? ;");
        
        try {
            
            if(connection == null) {
                connection = Conexao.getConnectionPostgres();
            }else {
                controleTransacao = false;
            }
            
            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            
            pst.setInt(1, cartao.getId());
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()) {
                
                Bandeira bandeira = null;
                if(rs.getInt("ctc_ban_id") == 1) {
                    bandeira = Bandeira.VISA;
                }else if(rs.getInt("ctc_ban_id") == 2) {
                    bandeira = Bandeira.MASTERCARD;
                }
                
                cartao = new CartaoCredito( 
                        rs.getString("ctc_nomeIdentificacao"),
                        rs.getString("ctc_validade"),
                        rs.getString("ctc_nomeTitular"), 
                        rs.getString("ctc_numero"),
                        rs.getString("ctc_cvv"),
                        bandeira
                        );
                
            }
                        
            return cartao;
            
        } catch (Exception e) {
            
            e.printStackTrace();
            return null;
            
        }   finally{
            if(controleTransacao)
                try {
                    connection.close();
                    pst.close();
                }catch(SQLException e) {
                    e.printStackTrace();
                }
        }
    }
}
