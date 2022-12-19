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
import dominio.venda.CategoriaStatus;
import dominio.venda.ItemEstoque;

public class ItemEstoqueDAO implements IDAO {
	private Connection connection = null;
	private boolean controleTransacao = true;
	
	public ItemEstoqueDAO(){
	    this.connection = null;
	}
	
	public ItemEstoqueDAO(Connection connection){
        this.connection = connection;
    }
	
	@Override
	public void salvar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub

	}

	@Override
	public void alterar(EntidadeDominio entidade) {
	    ItemEstoque estoque = (ItemEstoque) entidade;
		PreparedStatement pst = null;
		StringBuilder sql = new StringBuilder();
		
		sql.append("UPDATE estoque SET ");
		sql.append("etq_quantidade = ?, etq_status = ?, ");
		sql.append("etq_justificativa = ?, etq_cgs_id = ? ");
		sql.append("WHERE (etq_lvr_id = ?);");
		
		if(estoque.getQuantidade() == 0) {
		   //set status fora de mercado
		   // justificativa, sem estoque 
		}
		    
		
		try {
		    
		    if(connection == null) {
                connection = Conexao.getConnectionPostgres();
            }else {
                controleTransacao = false;
            }
            
        	connection.setAutoCommit(false);
        	
        	pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
        	
        	
    		pst.setInt(1, estoque.getQuantidade());
    		pst.setBoolean(2, true);
    		pst.setString(3, estoque.getJustificativaStatus());
    		pst.setInt(4, estoque.getCatStatus().getValor());
        	pst.setInt(5, estoque.getLivro().getId());
        	        	
        	pst.executeUpdate();
        	
        	connection.commit();
        	
		} catch (SQLException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            try {
                if(controleTransacao)
                    connection.close();
                    pst.close();
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
	    List<EntidadeDominio> itens = new ArrayList<>();
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();
        
        sql.append("SELECT etq_id, etq_lvr_id, etq_quantidade, ");
        sql.append("etq_status, etq_cgs_id, etq_valor_venda ");
        sql.append("FROM estoque;");
	            
        try {
            
            if(connection == null) {
                connection = Conexao.getConnectionPostgres();
            }else {
                controleTransacao = false;
            }
            
            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            
            ResultSet rs = pst.executeQuery();

            
            while(rs.next()) {
                LivroDAO livroDao = new LivroDAO(connection);
                Livro livro = new Livro();
                livro.setId(rs.getInt("etq_lvr_id"));
                
                CategoriaStatus catStatus = null;
                
                if(rs.getInt("etq_cgs_id") == 1) {
                    catStatus = CategoriaStatus.EM_ESTOQUE;
                }else if(rs.getInt("etq_cgs_id") == 2) {
                    catStatus = CategoriaStatus.FORA_DE_MERCADO;
                }else if(rs.getInt("etq_cgs_id") == 3) {
                    catStatus = CategoriaStatus.SEM_ESTOQUE;
                }
                
                ItemEstoque item = new ItemEstoque(              
                            (Livro) livroDao.consultarPorId(livro),
                            rs.getInt("etq_quantidade"),
                            new BigDecimal (rs.getString("etq_valor_venda")),
                            rs.getBoolean("etq_status"),
                            catStatus                           
                        ); 
                
                item.setId(rs.getInt("etq_id"));
                
                itens.add(item);
            }
            
            return itens;
            
        } catch (Exception e) {
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
        	    
		return null;
	}

	public ItemEstoque consultarPorLivro(Livro livro) {
	    ItemEstoque item = null;
	    PreparedStatement pst = null;
	    StringBuilder sql = new StringBuilder();
	    
	    sql.append("SELECT * FROM estoque ");
	    sql.append("WHERE etq_lvr_id = ?;");
	    
	    try {
            
            if(connection == null) {
                connection = Conexao.getConnectionPostgres();
            }else {
                controleTransacao = false;
            }
            
            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            
            pst.setInt(1, livro.getId());
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()) {
                LivroDAO livroDao = new LivroDAO(connection);
                          
                CategoriaStatus catStatus = null;
                
                if(rs.getInt("etq_cgs_id") == 1) {
                    catStatus = CategoriaStatus.EM_ESTOQUE;
                }else if(rs.getInt("etq_cgs_id") == 2) {
                    catStatus = CategoriaStatus.FORA_DE_MERCADO;
                }else if(rs.getInt("etq_cgs_id") == 3) {
                    catStatus = CategoriaStatus.SEM_ESTOQUE;
                }
                
                item = new ItemEstoque(              
                            (Livro) livroDao.consultarPorId(livro),
                            rs.getInt("etq_quantidade"),
                            new BigDecimal (rs.getString("etq_valor_venda")),
                            rs.getBoolean("etq_status"),
                            catStatus                            
                        );  
                
                item.setId(rs.getInt("etq_id"));
                        
            }
            
            return item;
            
        } catch (SQLException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
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
	
	public EntidadeDominio consultarPorId(EntidadeDominio entidade) {
	    ItemEstoque item = (ItemEstoque) entidade;
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();
        
        sql.append("SELECT * FROM estoque ");
        sql.append("WHERE etq_id = ?;");
        
        try {
            
            if(connection == null) {
                connection = Conexao.getConnectionPostgres();
            }else {
                controleTransacao = false;
            }
            
            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            
            pst.setInt(1, item.getId());
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()) {
                LivroDAO livroDao = new LivroDAO(connection);
                Livro livro = new Livro();
                livro.setId(rs.getInt("etq_lvr_id"));
                
                CategoriaStatus catStatus = null;
                
                if(rs.getInt("etq_cgs_id") == 1) {
                    catStatus = CategoriaStatus.EM_ESTOQUE;
                }else if(rs.getInt("etq_cgs_id") == 2) {
                    catStatus = CategoriaStatus.FORA_DE_MERCADO;
                }else if(rs.getInt("etq_cgs_id") == 3) {
                    catStatus = CategoriaStatus.SEM_ESTOQUE;
                }
                
                item = new ItemEstoque(              
                            (Livro) livroDao.consultarPorId(livro),
                            rs.getInt("etq_quantidade"),
                            new BigDecimal (rs.getString("etq_valor_venda")),
                            rs.getBoolean("etq_status"),
                            catStatus                           
                        );  
                                
                item.setId(rs.getInt("etq_id"));
                        
            }
            
            return item;
            
        } catch (SQLException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
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
	
	public ItemEstoque consultarPorLivroId(ItemEstoque item) {
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();
        
        sql.append("SELECT * FROM estoque ");
        sql.append("WHERE etq_lvr_id = ?;");
        
        try {
            
            if(connection == null) {
                connection = Conexao.getConnectionPostgres();
            }else {
                controleTransacao = false;
            }
            
            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            
            pst.setInt(1, item.getLivro().getId());
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()) {
                LivroDAO livroDao = new LivroDAO(connection);
                Livro livro = new Livro();
                livro.setId(rs.getInt("etq_lvr_id"));
                
                CategoriaStatus catStatus = null;
                
                if(rs.getInt("etq_cgs_id") == 1) {
                    catStatus = CategoriaStatus.EM_ESTOQUE;
                }else if(rs.getInt("etq_cgs_id") == 2) {
                    catStatus = CategoriaStatus.FORA_DE_MERCADO;
                }else if(rs.getInt("etq_cgs_id") == 3) {
                    catStatus = CategoriaStatus.SEM_ESTOQUE;
                }
                
                item = new ItemEstoque(              
                            (Livro) livroDao.consultarPorId(livro),
                            rs.getInt("etq_quantidade"),
                            new BigDecimal (rs.getString("etq_valor_venda")),
                            rs.getBoolean("etq_status"),
                            catStatus                           
                        );  
                                
                item.setId(rs.getInt("etq_id"));
                        
            }
            
            return item;
            
        } catch (SQLException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
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
