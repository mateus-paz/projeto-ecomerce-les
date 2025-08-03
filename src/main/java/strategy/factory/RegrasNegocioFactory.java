package strategy.factory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import dominio.cliente.Cliente;
import dominio.cliente.Endereco;
import dominio.cliente.Usuario;
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

public class RegrasNegocioFactory {
  private static final String nmCliente = Cliente.class.getName();
  private static final String nmUsuario = Usuario.class.getName();
  private static final String nmEndereco = Endereco.class.getName();
  private static final String nmCartao = CartaoCredito.class.getName();
  private static final String nmCartaoCompra = CartaoCompra.class.getName();
  private static final String nmItemEstoque = ItemEstoque.class.getName();
  private static final String nmItemCarrinho = ItemCarrinho.class.getName();
  private static final String nmEnderecoEntrega = EnderecoEntrega.class.getName();
  private static final String nmPagamento = Pagamento.class.getName();
  private static final String nmPedido = Pedido.class.getName();
  private static final String nmPedidoTroca = PedidoTroca.class.getName();
  private static final String nmEstoque = Estoque.class.getName();

  private static final Map<String, List<IStrategy>> regrasNegocio = new HashMap<>();

  static {
    ValidarDadosEndereco vEndereco = new ValidarDadosEndereco();
    ValidarDadosCartao vCartao = new ValidarDadosCartao();
    ValidarUnicidadeCartao vUniCartao = new ValidarUnicidadeCartao();
    regrasNegocio.put(nmCliente, Arrays.asList(new ValidarDadosCliente()));
    regrasNegocio.put(nmUsuario, Arrays.asList(new ValidarDadosUsuario()));
    regrasNegocio.put(nmEndereco, Arrays.asList(vEndereco));
    regrasNegocio.put(nmEnderecoEntrega, Arrays.asList(vEndereco, new CalcularFreteEntrega()));
    regrasNegocio.put(nmCartao, Arrays.asList(vCartao, vUniCartao));
    regrasNegocio.put(nmCartaoCompra, Arrays.asList(vCartao, vUniCartao));
    regrasNegocio.put(nmItemCarrinho, Arrays.asList(new ValidarDisponibilidadeEstoque()));
    regrasNegocio.put(nmItemEstoque, Arrays.asList(new ValidarDadosDeEstoque()));
    regrasNegocio.put(nmEstoque, Arrays.asList());
    regrasNegocio.put(nmPagamento, Arrays.asList(new ValidarPagamento()));
    regrasNegocio.put(nmPedido, Arrays.asList());
    regrasNegocio.put(nmPedidoTroca, Arrays.asList(new GerarCupomTroca()));
  }

  private RegrasNegocioFactory() {
  }

  public static List<IStrategy> obterRegrasNegocio(String nomeClasse) {
    return Optional.ofNullable(regrasNegocio.get(nomeClasse)).orElseThrow(
        () -> new RuntimeException("Regras de negócio não encontradas para a classe: " + nomeClasse));
  }
}
