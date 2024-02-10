package dominio.cliente;

import java.util.List;

import dominio.EntidadeDominio;
import dominio.venda.CartaoCredito;
import dominio.venda.Cupom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends EntidadeDominio {
    private String nome;
    private String dtNascimento;
    private String cpf;

    private Genero genero;
    private Usuario usuario;
    private Telefone telefone;

    private List<Endereco> enderecos;
    private List<CartaoCredito> cartoes;
    private List<Cupom> cupons;

}