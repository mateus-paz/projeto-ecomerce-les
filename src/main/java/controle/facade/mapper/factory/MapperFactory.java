package controle.facade.mapper.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import controle.facade.mapper.IMapper;
import controle.facade.mapper.impl.CarrinhoMapper;
import controle.facade.mapper.impl.CartaoCompraMapper;
import controle.facade.mapper.impl.CartaoCreditoMapper;
import controle.facade.mapper.impl.CategoriaMapper;
import controle.facade.mapper.impl.ClienteMapper;
import controle.facade.mapper.impl.CupomMapper;
import controle.facade.mapper.impl.EnderecoEntregaMapper;
import controle.facade.mapper.impl.EnderecoMapper;
import controle.facade.mapper.impl.EstoqueMapper;
import controle.facade.mapper.impl.ItemCarrinhoMapper;
import controle.facade.mapper.impl.ItemEstoqueMapper;
import controle.facade.mapper.impl.ItemTrocaMapper;
import controle.facade.mapper.impl.LivroMapper;
import controle.facade.mapper.impl.NotificacaoMapper;
import controle.facade.mapper.impl.PagamentoMapper;
import controle.facade.mapper.impl.PedidoMapper;
import controle.facade.mapper.impl.PedidoTrocaMapper;
import controle.facade.mapper.impl.TelefoneMapper;
import controle.facade.mapper.impl.UsuarioMapper;
import dominio.cliente.Cliente;
import dominio.cliente.Endereco;
import dominio.cliente.Notificacao;
import dominio.cliente.Telefone;
import dominio.cliente.Usuario;
import dominio.livro.Categoria;
import dominio.livro.Livro;
import dominio.venda.Carrinho;
import dominio.venda.CartaoCompra;
import dominio.venda.CartaoCredito;
import dominio.venda.Cupom;
import dominio.venda.EnderecoEntrega;
import dominio.venda.Estoque;
import dominio.venda.ItemCarrinho;
import dominio.venda.ItemEstoque;
import dominio.venda.ItemTroca;
import dominio.venda.Pagamento;
import dominio.venda.Pedido;
import dominio.venda.PedidoTroca;

public class MapperFactory {

    private static final Map<String, IMapper> mapers = new HashMap<>();

    static {
        // Mappers without dependencies
        UsuarioMapper usuarioMapper = new UsuarioMapper();
        TelefoneMapper telefoneMapper = new TelefoneMapper();
        EnderecoMapper enderecoMapper = new EnderecoMapper();
        CartaoCreditoMapper cartaoCreditoMapper = new CartaoCreditoMapper();
        CupomMapper cupomMapper = new CupomMapper();
        LivroMapper livroMapper = new LivroMapper();
        CategoriaMapper categoriaMapper = new CategoriaMapper();

        // Mappers with dependencies
        ClienteMapper clienteMapper = new ClienteMapper(usuarioMapper, telefoneMapper, enderecoMapper);
        CarrinhoMapper carrinhoMapper = new CarrinhoMapper(clienteMapper);
        CartaoCompraMapper cartaoCompraMapper = new CartaoCompraMapper(cartaoCreditoMapper);
        EnderecoEntregaMapper enderecoEntregaMapper = new EnderecoEntregaMapper(enderecoMapper);
        ItemEstoqueMapper itemEstoqueMapper = new ItemEstoqueMapper(livroMapper);
        ItemTrocaMapper itemTrocaMapper = new ItemTrocaMapper(livroMapper);
        NotificacaoMapper notificacaoMapper = new NotificacaoMapper(usuarioMapper);
        EstoqueMapper estoqueMapper = new EstoqueMapper(itemEstoqueMapper);
        ItemCarrinhoMapper itemCarrinhoMapper = new ItemCarrinhoMapper(carrinhoMapper, livroMapper);
        PagamentoMapper pagamentoMapper = new PagamentoMapper(carrinhoMapper, enderecoEntregaMapper, cartaoCompraMapper, cupomMapper);
        PedidoMapper pedidoMapper = new PedidoMapper(clienteMapper, carrinhoMapper, enderecoEntregaMapper, cartaoCompraMapper, cupomMapper);
        PedidoTrocaMapper pedidoTrocaMapper = new PedidoTrocaMapper(pedidoMapper, itemTrocaMapper);

        // Populate the map
        mapers.put(Carrinho.class.getSimpleName(), carrinhoMapper);
        mapers.put(CartaoCompra.class.getSimpleName(), cartaoCompraMapper);
        mapers.put(CartaoCredito.class.getSimpleName(), cartaoCreditoMapper);
        mapers.put(Categoria.class.getSimpleName(), categoriaMapper);
        mapers.put(Cliente.class.getSimpleName(), clienteMapper);
        mapers.put(Cupom.class.getSimpleName(), cupomMapper);
        mapers.put(EnderecoEntrega.class.getSimpleName(), enderecoEntregaMapper);
        mapers.put(Endereco.class.getSimpleName(), enderecoMapper);
        mapers.put(Estoque.class.getSimpleName(), estoqueMapper);
        mapers.put(ItemCarrinho.class.getSimpleName(), itemCarrinhoMapper);
        mapers.put(ItemEstoque.class.getSimpleName(), itemEstoqueMapper);
        mapers.put(ItemTroca.class.getSimpleName(), itemTrocaMapper);
        mapers.put(Livro.class.getSimpleName(), livroMapper);
        mapers.put(Notificacao.class.getSimpleName(), notificacaoMapper);
        mapers.put(Pagamento.class.getSimpleName(), pagamentoMapper);
        mapers.put(Pedido.class.getSimpleName(), pedidoMapper);
        mapers.put(PedidoTroca.class.getSimpleName(), pedidoTrocaMapper);
        mapers.put(Telefone.class.getSimpleName(), telefoneMapper);
        mapers.put(Usuario.class.getSimpleName(), usuarioMapper);
    }

    private MapperFactory() {
    }

    public static IMapper obterMapper(String nomeClasse) {
        return Optional.ofNullable(mapers.get(nomeClasse))
                .orElseThrow(() -> new RuntimeException("Mapper não encontrado para a classe: " + nomeClasse));
    }
}