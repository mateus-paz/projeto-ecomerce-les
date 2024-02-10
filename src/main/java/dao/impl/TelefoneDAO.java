package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import dao.IDAO;
import dominio.EntidadeDominio;
import dominio.cliente.Telefone;
import dominio.cliente.TipoTelefone;

public class TelefoneDAO implements IDAO {
	private Connection connection = null;
	private boolean controleTransacao = true;
	
	public TelefoneDAO() {}
	
	public TelefoneDAO(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public void salvar(EntidadeDominio entidade) {
		Telefone telefone = (Telefone) entidade;
		PreparedStatement pst = null;
		StringBuilder sql = new StringBuilder();
				
		sql.append("INSERT INTO telefones ");
		sql.append("(tel_ddd, tel_numero, tel_tpt_id) ");
		sql.append("VALUES (?, ?, ?)");
		
		try {
		    if(connection == null) {
                connection = Conexao.getConnectionPostgres();
            }else {
                controleTransacao = false;
            }
			connection.setAutoCommit(false);
			
			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, telefone.getDdd());
			pst.setString(2, telefone.getNumero());
			pst.setInt(3, telefone.getTpTelefone().getValor());
			
			pst.executeUpdate();		
			
			ResultSet rs = pst.getGeneratedKeys();
			
			int idTelefone = 0;
			if (rs.next())
				idTelefone = rs.getInt(1);
			
			telefone.setId(idTelefone);
			
			connection.commit();
			
		} catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();    
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
	public void alterar(EntidadeDominio entidade) {
		Telefone telefone = (Telefone) entidade;
		PreparedStatement pst = null;
		StringBuilder sql = new StringBuilder();
		
		sql.append("UPDATE telefones SET ");
    	sql.append("tel_ddd = ?, tel_numero = ?, tel_tpt_id = ?  ");
    	sql.append("WHERE (tel_id = ?) ;");
        
        try {
            
            if(connection == null) {
                connection = Conexao.getConnectionPostgres();
            }else {
                controleTransacao = false;
            }
            
        	connection.setAutoCommit(false);
        	
        	pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
        	
        	pst.setString(1, telefone.getDdd());
    		pst.setString(2, telefone.getNumero());
    		pst.setInt(3, telefone.getTpTelefone().getValor());
    		
        	pst.setInt(4, telefone.getId());
        	
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
	public void excluir(EntidadeDominio entidade) {
		
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		return null;
	}
	
    @Override
    public EntidadeDominio consultarPorId(EntidadeDominio entidade) {
        Telefone telefone = (Telefone) entidade;
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();
        
        sql.append("SELECT * FROM telefones ");
        sql.append("WHERE tel_id = ?;");
        
        try {
            if(connection == null) {
                connection = Conexao.getConnectionPostgres();
            }else {
                controleTransacao = false;
            }
            
            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            
            pst.setInt(1, telefone.getId());
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()) {
                TipoTelefone tpTelefone = null;
                if(rs.getInt("tel_tpt_id") == 1){
                    tpTelefone = TipoTelefone.FIXO;
                }else if(rs.getInt("tel_tpt_id") == 2){
                    tpTelefone = TipoTelefone.CELULAR;
                }
                
                telefone = new Telefone(
                        tpTelefone, 
                        rs.getString("tel_ddd"), 
                        rs.getString("tel_numero")
                        );
            }
            
            return telefone;
            
        } catch (SQLException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } finally{
            if(controleTransacao) {
                try {
                    connection.close();
                    pst.close();
                }catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
