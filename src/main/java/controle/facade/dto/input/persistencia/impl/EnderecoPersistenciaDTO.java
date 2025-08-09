package controle.facade.dto.input.persistencia.impl;

import controle.facade.dto.input.persistencia.InputPersistenciaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import dominio.EntidadeDominio;
import dominio.cliente.Endereco;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoPersistenciaDTO implements InputPersistenciaDTO {
    private Integer id;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cep;
    private String complemento;
    private String nomeIdentificacao;
    private String tpResidencia; // Representar enum como String
    private String tpLogradouro; // Representar enum como String
    private CidadePersistenciaDTO cidade;
    private ClientePersistenciaDTO cliente;
    private PedidoPersistenciaDTO pedido;

    @Override
    public Class<? extends EntidadeDominio> getDominio() {
        return Endereco.class;
    }
}