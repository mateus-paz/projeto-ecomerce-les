package dao.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

public class DaoFactory {
    private static final String nmCliente = Cliente.class.getName();
    private static final String nmUsuario = Usuario.class.getName();
    private static final String nmEndereco = Endereco.class.getName();
    private static final String nmLivro = Livro.class.getName();
    private static final String nmCartao = CartaoCredito.class.getName();
    private static final String nmCartaoCompra = CartaoCompra.class.getName();
    private static final String nmItemEstoque = ItemEstoque.class.getName();
    private static final String nmItemCarrinho = ItemCarrinho.class.getName();
    private static final String nmCarrinho = Carrinho.class.getName();
    private static final String nmEnderecoEntrega = EnderecoEntrega.class.getName();
    private static final String nmPagamento = Pagamento.class.getName();
    private static final String nmPedido = Pedido.class.getName();
    private static final String nmPedidoTroca = PedidoTroca.class.getName();
    private static final String nmEstoque = Estoque.class.getName();

    private static final Map<String, IDAO> daos = new HashMap<>();

    static {
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
    }

    private DaoFactory() {}

    public static IDAO obterDao(String nomeClasse) {
        return Optional.ofNullable(daos.get(nomeClasse))
                .orElseThrow(() -> new RuntimeException("DAO não encontrado para a classe: " + nomeClasse));
    }
}
