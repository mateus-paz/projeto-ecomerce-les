package strategy.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dominio.cliente.Cliente;
import dominio.cliente.Endereco;
import dominio.cliente.Genero;
import dominio.cliente.Telefone;
import dominio.cliente.Usuario;

public class ValidarDadosClienteTest {

    @Mock
    private ValidarDadosTelefone vTelefone;

    @Mock
    private ValidarDadosEndereco vEndereco;

    @Mock
    private ValidarDadosUsuario vUsuario;

    @Mock
    private ValidarUnicidadeCliente vUnicidade;

    @InjectMocks
    private ValidarDadosCliente validador;

    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        validador = new ValidarDadosCliente(vTelefone, vEndereco, vUsuario, vUnicidade);
        cliente = new Cliente();
        cliente.setEnderecos(new ArrayList<>());
        cliente.setNome("Fulano de Tal");
        cliente.setCpf("123.456.789-00");
        cliente.setDtNascimento("2000-01-01");
        cliente.setGenero(Genero.MASCULINO);
        cliente.setTelefone(new Telefone());
        cliente.setUsuario(new Usuario());
        cliente.getEnderecos().add(new Endereco());
    }

    @Test
    @DisplayName("Deve retornar nulo para cliente válido")
    public void deveRetornarNuloClienteValido() {
        when(vTelefone.processar(cliente.getTelefone())).thenReturn(null);
        when(vEndereco.processar(cliente.getEnderecos().get(0))).thenReturn(null);
        when(vUsuario.processar(cliente.getUsuario())).thenReturn(null);
        when(vUnicidade.processar(cliente)).thenReturn(null);

        String resultado = validador.processar(cliente);

        assertNull(resultado);
    }

    @Test
    @DisplayName("Não deve retornar nulo para cliente com nome nulo")
    public void naoDeveRetornarNuloClienteNomeNulo() {
        cliente.setNome(null);

        String resultado = validador.processar(cliente);

        assertEquals("E obrigatorio o cadastro do Nome; ", resultado);
    }

    @Test
    @DisplayName("Não deve retornar nulo para cliente com CPF nulo")
    public void naoDeveRetornarNuloClienteCpfNulo() {
        cliente.setCpf(null);

        String resultado = validador.processar(cliente);

        assertEquals("E obrigatorio o cadastro do CPF; ", resultado);
    }

    @Test
    @DisplayName("Não deve retornar nulo para cliente com CPF inválido")
    public void naoDeveRetornarNuloClienteCpfInvalido() {
        cliente.setCpf("123.456.789-0");

        String resultado = validador.processar(cliente);

        assertEquals("O Tamanho do CPF deve corresponder a 11 digitos (Considerando apenas numeros); ", resultado);
    }

    @Test
    @DisplayName("Não deve retornar nulo para cliente com data de nascimento nula")
    public void naoDeveRetornarNuloClienteDtNascimentoNula() {
        cliente.setDtNascimento(null);

        String resultado = validador.processar(cliente);

        assertEquals("E obrigatorio o cadastro da Data de Nascimento; ", resultado);
    }

    @Test
    @DisplayName("Não deve retornar nulo para cliente com gênero nulo")
    public void naoDeveRetornarNuloClienteGeneroNulo() {
        cliente.setGenero(null);

        String resultado = validador.processar(cliente);

        assertEquals("E obrigatorio a selecao do Genero; ", resultado);
    }
}