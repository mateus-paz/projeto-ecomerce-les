package dao.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

public class UsuarioDAO implements IDAO {
    private Connection connection = null;
    private boolean controleTransacao = true;
	
	public UsuarioDAO() {}
	
	public UsuarioDAO(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void salvar(EntidadeDominio entidade) {
		Usuario usuario = (Usuario) entidade;
    	PreparedStatement pst = null;
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO usuarios ");
		sql.append("(usr_email, usr_senha, usr_isAdmin) ");
		sql.append("VALUES (?, ?, ?)");
		
		try {
		    if(connection == null) {
                connection = Conexao.getConnectionPostgres();
            }else {
                controleTransacao = false;
            }
		    
			connection.setAutoCommit(false);
			
			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, usuario.getEmail());
			pst.setString(2, criptografarSenha(usuario.getNovaSenha()));
			pst.setBoolean(3, false);
			
			pst.executeUpdate();	
			
			ResultSet rs = pst.getGeneratedKeys();
			
			int idUsuario = 0;
			
			if (rs.next())
				idUsuario = rs.getInt(1);
			
			usuario.setId(idUsuario);
			
			Notificacao notificacao = new Notificacao("Aqui serão mostradas suas notificações", usuario);
			NotificacaoDAO notDao = new NotificacaoDAO(connection);
			notDao.salvar(notificacao);
			
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
		Usuario usuario = (Usuario) entidade;
		PreparedStatement pst = null;
		StringBuilder sql = new StringBuilder();
		
		sql.append("UPDATE usuarios SET ");
    	sql.append("usr_senha = ? ");
    	sql.append("WHERE (usr_id = ?) ;");
        
        try {
            
            if(connection == null) {
                connection = Conexao.getConnectionPostgres();
            }else {
                controleTransacao = false;
            }
            
        	connection.setAutoCommit(false);
        	
        	pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
        	
    		pst.setString(1, criptografarSenha(usuario.getNovaSenha()));
    		
        	pst.setInt(2, usuario.getId());
        	
        	pst.executeUpdate();

        	usuario.setNovaSenha(null);
        	usuario.setConfirmarSenha(null);
        	
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
	
	public boolean confirmarLogin(Usuario usuario) {
		PreparedStatement pst = null;
    	StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT usr_id FROM usuarios ");
		sql.append("WHERE usr_email = ? and ");
		sql.append("usr_senha = ? ;");
		
		try {
    		
		    if(connection == null) {
                connection = Conexao.getConnectionPostgres();
            }else {
                controleTransacao = false;
            }
		    
    		pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
    		
    		pst.setString(1, usuario.getEmail());
    		pst.setString(2, criptografarSenha(usuario.getSenha()));
    		
    		ResultSet rs = pst.executeQuery();
    		
    		if(rs.next()) {
    		    usuario.setSenha(null);
    		    usuario.setId(rs.getInt("usr_id"));
    			
    			return true;
    		}    		
    			
    		return false;
    		
		} catch (SQLException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
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
        
        return false;
		
	}
	
	private String criptografarSenha(String senha) {
		MessageDigest algorithm;
		try {
			algorithm = MessageDigest.getInstance("SHA-256");
			byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));

			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
				hexString.append(String.format("%02X", 0xFF & b));
			}
			String senhahex = hexString.toString();
			
			return senhahex;
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
    @Override
    public EntidadeDominio consultarPorId(EntidadeDominio entidade) {
        Usuario usuario = (Usuario) entidade;
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();

        sql.append("select usr_email, usr_isAdmin ");
        sql.append("from usuarios where usr_id = ?;");
        
        try {
            if(connection == null) {
                connection = Conexao.getConnectionPostgres();
            }else {
                controleTransacao = false;
            }
            
            connection.setAutoCommit(false);
            
            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            
            pst.setInt(1, usuario.getId());
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()) {
               usuario.setEmail(rs.getString("usr_email"));
               usuario.setAdmin(rs.getBoolean("usr_isAdmin"));
               
               if(!usuario.isAdmin()) {
                   ClienteDAO cliDao = new ClienteDAO(connection);
                   usuario.setCliente(cliDao.consultarPorUser(usuario));
                   
                   NotificacaoDAO notificacaoDao = new NotificacaoDAO(connection);
                   List<EntidadeDominio> retorno = notificacaoDao.consultar(usuario);
                   List<Notificacao> notificacoes = new ArrayList<>();
                   for(EntidadeDominio ent : retorno)
                       notificacoes.add((Notificacao) ent);
                   
                   usuario.setNotificacoes(notificacoes);
                       
               }
            }
            
            return usuario;
            
        } catch (SQLException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
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
	
    public boolean isCadastradoEmail(Usuario usuario) {
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();
        
        sql.append("SELECT usr_id FROM usuarios WHERE usr_email = ?;");
                
        try {
            
            if(connection == null) {
                connection = Conexao.getConnectionPostgres();
            }else {
                controleTransacao = false;
            }
                        
            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            
            pst.setString(1, usuario.getEmail());
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()) {
                
                if(usuario.getId() == rs.getInt("usr_id")) {
                    return false;
                }
                
                return true;
            
            }else {
                return false;
            }
            
        } catch (SQLException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
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
        
        return true;
    }
    
}
