package controle.facade.dto.input.persistencia.impl;

import controle.facade.dto.input.persistencia.InputPersistenciaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import dominio.EntidadeDominio;
import dominio.venda.CartaoCredito;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartaoCreditoPersistenciaDTO implements InputPersistenciaDTO {
    private Integer id;
    private String nomeIdentificacao;
    private String dataValidade;
    private String titular;
    private String numero;
    private String cvv;
    private String bandeira; // Representar enum como String
    private ClientePersistenciaDTO cliente;

    @Override
    public Class<? extends EntidadeDominio> getDominio() {
        return CartaoCredito.class;
    }
}