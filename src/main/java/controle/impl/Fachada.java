package controle.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controle.IFachada;
import dao.IDAO;
import dao.impl.CartaoCompraDAO;
import dao.impl.CartaoCreditoDAO;
import dao.impl.ClienteDAO;
import dao.impl.EnderecoDAO;
import dao.impl.EnderecoEntregaDAO;
import dao.impl.EstoqueDAO;
import dao.impl.ItemCarrinhoDAO;
import dao.impl.ItemEstoqueDAO;
import dao.impl.LivroDAO;
import dao.impl.PagamentoDAO;
import dao.impl.PedidoDAO;
import dao.impl.PedidoTrocaDAO;
import dao.impl.UsuarioDAO;
import dominio.EntidadeDominio;
import dominio.cliente.Cliente;
import dominio.cliente.Endereco;
import dominio.cliente.Usuario;
import dominio.livro.Livro;
import dominio.venda.Carrinho;
import dominio.venda.CartaoCompra;
import dominio.venda.CartaoCredito;
import dominio.venda.EnderecoEntrega;
import dominio.venda.Estoque;
import dominio.venda.ItemCarrinho;
import dominio.venda.ItemEstoque;
import dominio.venda.Pagamento;
import dominio.venda.Pedido;
import dominio.venda.PedidoTroca;
import strategy.IStrategy;
import strategy.impl.CalcularFreteEntrega;
import strategy.impl.GerarCupomTroca;
import strategy.impl.ValidarDadosCartao;
import strategy.impl.ValidarDadosCliente;
import strategy.impl.ValidarDadosDeEstoque;
import strategy.impl.ValidarDadosEndereco;
import strategy.impl.ValidarDadosUsuario;
import strategy.impl.ValidarDisponibilidadeEstoque;
import strategy.impl.ValidarPagamento;
import strategy.impl.ValidarUnicidadeCartao;

public class Fachada implements IFachada{
	
	private Map<String, IDAO> daos = new HashMap<String, IDAO>();
	private Map<String, List<IStrategy>> rns = new HashMap<String, List<IStrategy>>();
	
	public Fachada() {
		String nmCliente = Cliente.class.getName();
		String nmUsuario = Usuario.class.getName();
		String nmEndereco = Endereco.class.getName();
		String nmLivro = Livro.class.getName();
		String nmCartao = CartaoCredito.class.getName();
		String nmCartaoCompra = CartaoCompra.class.getName();
		String nmItemEstoque = ItemEstoque.class.getName();
		String nmItemCarrinho = ItemCarrinho.class.getName();
		String nmCarrinho = Carrinho.class.getName();
		String nmEnderecoEntrega = EnderecoEntrega.class.getName();
		String nmPagamento = Pagamento.class.getName();
		String nmPedido = Pedido.class.getName();
		String nmPedidoTroca = PedidoTroca.class.getName();
		String nmEstoque = Estoque.class.getName();
		
		daos.put(nmCliente, new ClienteDAO());
		daos.put(nmUsuario, new UsuarioDAO());
		daos.put(nmEndereco, new EnderecoDAO());
		daos.put(nmLivro, new LivroDAO());
		daos.put(nmCartao, new CartaoCreditoDAO());
		daos.put(nmCartaoCompra, new CartaoCompraDAO());
		daos.put(nmItemEstoque, new ItemEstoqueDAO());
		
		daos.put(nmCarrinho, new ItemCarrinhoDAO());
		daos.put(nmItemCarrinho, new ItemCarrinhoDAO());
		daos.put(nmEnderecoEntrega, new EnderecoEntregaDAO());
		daos.put(nmPagamento, new PagamentoDAO());
		
		daos.put(nmPedido, new PedidoDAO());
		daos.put(nmEstoque, new EstoqueDAO());
		daos.put(nmPedidoTroca, new PedidoTrocaDAO());
		
		ValidarDadosCliente vCliente = new ValidarDadosCliente();
		ValidarDadosUsuario vUsuario = new ValidarDadosUsuario();
		ValidarDadosEndereco vEndereco = new ValidarDadosEndereco();
		
		ValidarDadosCartao vCartao = new ValidarDadosCartao();
		ValidarUnicidadeCartao vUniCartao = new ValidarUnicidadeCartao();
		
		List<IStrategy> rnsCliente = new ArrayList<IStrategy>();
		rnsCliente.add(vCliente);
		rns.put(nmCliente, rnsCliente);
		
		List<IStrategy> rnsEndereco = new ArrayList<IStrategy>();
		rnsEndereco.add(vEndereco);
		rns.put(nmEndereco, rnsEndereco);
		
		List<IStrategy> rnsUsuario = new ArrayList<IStrategy>();
		rnsUsuario.add(vUsuario);
		rns.put(nmUsuario, rnsUsuario);
		
		List<IStrategy> rnsCartaoCredito = new ArrayList<>();
		rnsCartaoCredito.add(vCartao);
		rnsCartaoCredito.add(vUniCartao);
        rns.put(nmCartao, rnsCartaoCredito);
		
		List<IStrategy> rnsCartaoCompra = new ArrayList<>();
		rnsCartaoCompra.add(vCartao);
		rnsCartaoCompra.add(vUniCartao);
		rns.put(nmCartaoCompra, rnsCartaoCompra);
		
		ValidarDisponibilidadeEstoque vEstoque = new ValidarDisponibilidadeEstoque();
		List<IStrategy> rnsItemCarrinho = new ArrayList<>();
		rnsItemCarrinho.add(vEstoque);
		rns.put(nmItemCarrinho, rnsItemCarrinho);
		
		ValidarDadosDeEstoque vDadosEstoque = new ValidarDadosDeEstoque();
		List<IStrategy> rnsItemEstoque = new ArrayList<>();
		rnsItemEstoque.add(vDadosEstoque);
		rns.put(nmItemEstoque, rnsItemEstoque);
		
		List<IStrategy> rnsEstoque = new ArrayList<>();
		rns.put(nmEstoque, rnsEstoque);
		
		CalcularFreteEntrega cFrete = new CalcularFreteEntrega();
		List<IStrategy> rnsEnderecoEntrega = new ArrayList<>();
		rnsEnderecoEntrega.add(vEndereco);		
		rnsEnderecoEntrega.add(cFrete);
		rns.put(nmEnderecoEntrega, rnsEnderecoEntrega);
		
		ValidarPagamento vPagamento = new ValidarPagamento();
        List<IStrategy> rnsPagamento = new ArrayList<>();
        rnsPagamento.add(vPagamento);      
        rns.put(nmPagamento, rnsPagamento);
		
        List<IStrategy> rnsPedido = new ArrayList<>();   
        rns.put(nmPedido, rnsPedido);
        
        GerarCupomTroca gTroca = new GerarCupomTroca();
        List<IStrategy> rnsPedidoTroca = new ArrayList<>();
        rnsPedidoTroca.add(gTroca);
        rns.put(nmPedidoTroca, rnsPedidoTroca);
	}
	
	@Override
	public String salvar(EntidadeDominio entidade) {
				
		StringBuilder sb = new StringBuilder();
		String nmEntidade = entidade.getClass().getName();
		List<IStrategy> regras = rns.get(nmEntidade);
		String msg;
		
		for(IStrategy s: regras){
			msg = s.processar(entidade);
			if(msg != null){
				sb.append(msg);
				sb.append("\n");	
			}
		}
		
		if(sb.length() == 0){
			IDAO dao = daos.get(nmEntidade);
			dao.salvar(entidade);
		}else{
			return sb.toString();
		}
		
		return null;
	}

	@Override
	public String alterar(EntidadeDominio entidade) {
		StringBuilder sb = new StringBuilder();
		String nmEntidade = entidade.getClass().getName();
		List<IStrategy> regras = rns.get(nmEntidade);
		String msg;
		
		for(IStrategy s: regras){
			msg = s.processar(entidade);
			if(msg != null){
				sb.append(msg);
				sb.append("\n");	
			}
		}
		
		if(sb.length() == 0){
			IDAO dao = daos.get(nmEntidade);
			dao.alterar(entidade);
		}else{
			return sb.toString();
		}
		
		return null;
	}

	@Override
	public String excluir(EntidadeDominio entidade) {
		String nmEntidade = entidade.getClass().getName();
		
		IDAO dao = daos.get(nmEntidade);
		
		dao.excluir(entidade);
		
		return null;
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		
		String nmEntidade = entidade.getClass().getName();
				
		IDAO dao = daos.get(nmEntidade);
		
		return dao.consultar(entidade);
	}

    @Override
    public EntidadeDominio consultarPorId(EntidadeDominio entidade) {

        String nmEntidade = entidade.getClass().getName();
                
        IDAO dao = daos.get(nmEntidade);
        
        return dao.consultarPorId(entidade);
    }

	
	
}
