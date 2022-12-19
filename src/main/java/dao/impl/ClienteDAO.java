package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controle.web.ConsultarCommand;
import controle.web.ICommand;
import dao.IDAO;
import dominio.EntidadeDominio;
import dominio.cliente.Cliente;
import dominio.cliente.Endereco;
import dominio.cliente.Genero;
import dominio.cliente.Telefone;
import dominio.cliente.Usuario;
import dominio.venda.Carrinho;
import dominio.venda.CartaoCredito;
import dominio.venda.Cupom;
import dominio.venda.ItemCarrinho;

public class ClienteDAO implements IDAO{
    private Connection connection = null;
    private boolean controleTransacao = true;

    public ClienteDAO() {}
     
    public ClienteDAO(Connection connection) {
        this.connection = connection;
    }
     
	@Override
	public void salvar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
    	PreparedStatement pst = null;
		StringBuilder sql = new StringBuilder();

		sql.append("INSERT INTO clientes ");
		sql.append("(cli_nome, cli_dtNascimento, cli_cpf, ");
		sql.append("cli_gen_id, cli_tel_id, cli_usr_id) ");
		sql.append("VALUES (?, ?, ?, ?, ?, ?)");

		try {
		    if(connection == null) {
                connection = Conexao.getConnectionPostgres();
            }else {
                controleTransacao = false;
            }
		    
			connection.setAutoCommit(false);
			
    		UsuarioDAO usuarioDao = new UsuarioDAO(connection);
    		usuarioDao.salvar(cliente.getUsuario());
    		
    		TelefoneDAO telefoneDao = new TelefoneDAO(connection);
    		telefoneDao.salvar(cliente.getTelefone());
    		
    		pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
    		
    		pst.setString(1, cliente.getNome());
    		pst.setString(2, cliente.getDtNascimento());
    		pst.setString(3, cliente.getCpf());
    		pst.setInt(4, cliente.getGenero().getValor());	 
    		pst.setInt(5, cliente.getTelefone().getId());
    		pst.setInt(6, cliente.getUsuario().getId());
    		
    		pst.executeUpdate();
    		
    		ResultSet rs = pst.getGeneratedKeys();
    		
			int idCliente=0;
			
			if(rs.next())
				idCliente = rs.getInt(1);
			
			cliente.setId(idCliente);
			
			connection.commit();		
			
			Endereco end = cliente.getEnderecos().get(0);
			end.setCliente(cliente);
			
    		EnderecoDAO enderecoDao = new EnderecoDAO(connection);
    		enderecoDao.salvar(end);
		
    		criarCarrinho(cliente);
    		
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
		Cliente cliente = (Cliente) entidade;
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();
        
    	sql.append("UPDATE clientes SET ");
    	sql.append("cli_nome = ?, cli_dtNascimento = ?, cli_cpf = ?, cli_gen_id = ?  ");
    	sql.append("WHERE (cli_id = ?) ;");
        
        try {
            
            if(connection == null) {
                connection = Conexao.getConnectionPostgres();
            }else {
                controleTransacao = false;
            }
            
        	connection.setAutoCommit(false);
        	
        	pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
        	        	
        	pst.setString(1, cliente.getNome());
    		pst.setString(2, cliente.getDtNascimento());
    		pst.setString(3, cliente.getCpf());
    		pst.setInt(4, cliente.getGenero().getValor());
        	pst.setInt(5, cliente.getId());
        	
        	pst.executeUpdate();
        	
        	ResultSet rs = pst.getGeneratedKeys();
            
            int idTelefone=0;
            
            if(rs.next())
                idTelefone = rs.getInt("cli_tel_id");
            
            cliente.getTelefone().setId(idTelefone);
            
        	TelefoneDAO telDao = new TelefoneDAO(connection);
            telDao.alterar(cliente.getTelefone());
            
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
	    List<EntidadeDominio> clientes = new ArrayList<>();
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();
        
        sql.append("SELECT cli_id, cli_cpf, cli_nome, cli_gen_id, ");
        sql.append("cli_dtNascimento, cli_usr_id, cli_tel_id ");
        sql.append("FROM clientes;");
        
        try {
            if(connection == null) {
                connection = Conexao.getConnectionPostgres();
            }else {
                controleTransacao = false;
            }
            
            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                
                Genero genero = null;
                if(rs.getInt("cli_gen_id") == 1) {
                    genero = Genero.MASCULINO;
                }else if(rs.getInt("cli_gen_id") == 2) {
                    genero = Genero.MASCULINO;
                }else if(rs.getInt("cli_gen_id") == 3) {
                    genero = Genero.NAOBINARIO;
                }
                
                Cliente cliente = new Cliente(
                        genero, 
                        rs.getString("cli_nome"),
                        rs.getString("cli_dtNascimento"), 
                        rs.getString("cli_cpf")
                        );
                
                TelefoneDAO telDao = new TelefoneDAO(connection);
                Telefone tel = new Telefone();
                tel.setId(rs.getInt("cli_tel_id"));
                cliente.setTelefone((Telefone) telDao.consultarPorId(tel));
                
                UsuarioDAO usrDao = new UsuarioDAO(connection);
                Usuario usr = new Usuario();
                usr.setId(rs.getInt("cli_usr_id"));
                cliente.setUsuario((Usuario) usrDao.consultarPorId(usr));
                
                cliente.setId(rs.getInt("cli_id"));
                
                clientes.add(cliente);
            }

            return clientes;
            
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
	
	public Cliente consultarPorUser(Usuario user) {
		Cliente cliente = null;
		PreparedStatement pst = null;
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM clientes ");
		sql.append("WHERE cli_usr_id = ? ;");
				
		try {
		    if(connection == null) {
                connection = Conexao.getConnectionPostgres();
            }else {
                controleTransacao = false;
            }
			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			
			pst.setInt(1, user.getId());
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
			    
			    Genero genero = null;
                if(rs.getInt("cli_gen_id") == 1) {
                    genero = Genero.MASCULINO;
                }else if(rs.getInt("cli_gen_id") == 2) {
                    genero = Genero.MASCULINO;
                }else if(rs.getInt("cli_gen_id") == 3) {
                    genero = Genero.NAOBINARIO;
                }
                
			    cliente = new Cliente(
                        genero, 
                        rs.getString("cli_nome"),
                        rs.getString("cli_dtNascimento"), 
                        rs.getString("cli_cpf")
                        );
                
                cliente.setId(rs.getInt("cli_id"));
            
                TelefoneDAO telefoneDao = new TelefoneDAO(connection);
                Telefone tel = new Telefone();
                tel.setId(rs.getInt("cli_tel_id"));
                cliente.setTelefone((Telefone) telefoneDao.consultarPorId(tel));
                
                EnderecoDAO endDao = new EnderecoDAO(connection);
                List<Endereco> enderecos = new ArrayList<>();
                for(EntidadeDominio entidade : endDao.consultar(cliente))
                    enderecos.add((Endereco) entidade);
                cliente.setEnderecos(enderecos);
                
                CartaoCreditoDAO cardDao = new CartaoCreditoDAO(connection);
                List<CartaoCredito> cartoes = new ArrayList<>();
                for(EntidadeDominio entidade : cardDao.consultar(cliente))
                    cartoes.add((CartaoCredito) entidade);
                cliente.setCartoes(cartoes);

                CupomDAO cupomDao = new CupomDAO(connection);
                List<Cupom> cupons = new ArrayList<>();
                for(EntidadeDominio entidade : cupomDao.consultar(cliente))
                    cupons.add((Cupom) entidade);
                cliente.setCupons(cupons);

                cliente.setUsuario(user);
			
			}
			
			return cliente;
			
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

	public boolean isCadastradoCpf(Cliente cliente) {
	    
		PreparedStatement pst = null;
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT cli_id FROM clientes WHERE cli_cpf = ?;");
				
		try {
			
		    if(connection == null) {
                connection = Conexao.getConnectionPostgres();
            }else {
                controleTransacao = false;
            }
		    
			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, cliente.getCpf());
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
			    
			    if(cliente.getId() == rs.getInt("cli_id")) {
			        return false;
			    }
			    
				return true;
			
			}else {
			    return false;
			}
			
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
        
        return true;
	}
	
	public Carrinho consultarCarrinho(Cliente cliente) {
		Carrinho carrinho;
		PreparedStatement pst = null;
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM carrinhos ");
		sql.append("WHERE crr_cli_id = ?;");
		
		try {
			
			connection = Conexao.getConnectionPostgres();
							
			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			pst.setInt(1, cliente.getId());
			
			ResultSet rs = pst.executeQuery();
		
			if(rs.next()) {
				carrinho = new Carrinho();
				
				carrinho.setId(rs.getInt("crr_id"));
				
				ICommand cmd = new ConsultarCommand();
				
				
				@SuppressWarnings("unchecked")
                List<ItemCarrinho> itens = (List<ItemCarrinho>) cmd.executar(carrinho);
				
				carrinho.setItens(itens);
				
				return carrinho;
			}
		
			return null;
		
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
		
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		}
		
		return null;
	}

    @Override
    public EntidadeDominio consultarPorId(EntidadeDominio entidade) {
        Cliente cliente = (Cliente) entidade;
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();
        
        sql.append("SELECT * FROM clientes ");
        sql.append("WHERE cli_id = ? ;");
        
        try {
            if(connection == null) {
                connection = Conexao.getConnectionPostgres();
            }else {
                controleTransacao = false;
            }
            
            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            
            pst.setInt(1, cliente.getId());
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()) {
                
                Genero genero = null;
                if(rs.getInt("cli_gen_id") == 1) {
                    genero = Genero.MASCULINO;
                }else if(rs.getInt("cli_gen_id") == 2) {
                    genero = Genero.MASCULINO;
                }else if(rs.getInt("cli_gen_id") == 3) {
                    genero = Genero.NAOBINARIO;
                }
                
                cliente = new Cliente(
                        genero, 
                        rs.getString("cli_nome"),
                        rs.getString("cli_dtNascimento"), 
                        rs.getString("cli_cpf")
                        );
                
                cliente.setId(rs.getInt("cli_id"));
            
                TelefoneDAO telefoneDao = new TelefoneDAO(connection);
                Telefone tel = new Telefone();
                tel.setId(rs.getInt("cli_tel_id"));
                cliente.setTelefone((Telefone) telefoneDao.consultarPorId(tel));
                
                EnderecoDAO endDao = new EnderecoDAO(connection);
                List<Endereco> enderecos = new ArrayList<>();
                for(EntidadeDominio ent : endDao.consultar(cliente))
                    enderecos.add((Endereco) ent);
                cliente.setEnderecos(enderecos);
                
                CartaoCreditoDAO cardDao = new CartaoCreditoDAO(connection);
                List<CartaoCredito> cartoes = new ArrayList<>();
                for(EntidadeDominio ent : cardDao.consultar(cliente))
                    cartoes.add((CartaoCredito) ent);
                cliente.setCartoes(cartoes);
                
                UsuarioDAO usrDao = new UsuarioDAO(connection);
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("cli_usr_id"));
                cliente.setUsuario((Usuario) usrDao.consultarPorId(usuario));
                
                CupomDAO cupomDao = new CupomDAO(connection);
                List<Cupom> cupons = new ArrayList<>();
                for(EntidadeDominio ent : cupomDao.consultar(cliente))
                    cupons.add((Cupom) ent);
                cliente.setCupons(cupons);
            }
            
            return cliente;
            
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
	
	private void criarCarrinho(Cliente cliente) {
	    PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();

        sql.append("INSERT INTO carrinhos ");
        sql.append("(crr_cli_id) ");
        sql.append("VALUES (?)");

        try {
            if(connection == null) {
                connection = Conexao.getConnectionPostgres();
            }else {
                controleTransacao = false;
            }
            
            connection.setAutoCommit(false);
           
            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            
            pst.setInt(1, cliente.getId());
            
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
}
