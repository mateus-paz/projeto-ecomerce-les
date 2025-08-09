package controle.facade.mapper.impl;

import controle.facade.dto.input.persistencia.InputPersistenciaDTO;
import controle.facade.dto.input.persistencia.impl.CartaoCreditoPersistenciaDTO;
import controle.facade.dto.output.OutputDTO;
import controle.facade.dto.output.impl.CartaoCreditoOutputDTO;
import controle.facade.mapper.IMapper;
import dominio.EntidadeDominio;
import dominio.venda.Bandeira;
import dominio.venda.CartaoCredito;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CartaoCreditoMapper implements IMapper {

    @Override
    public EntidadeDominio toEntidade(InputPersistenciaDTO dto) {
        CartaoCreditoPersistenciaDTO cartaoCreditoDTO = (CartaoCreditoPersistenciaDTO) dto;
        return CartaoCredito.builder()
                .nomeIdentificacao(cartaoCreditoDTO.getNomeIdentificacao())
                .dataValidade(cartaoCreditoDTO.getDataValidade())
                .titular(cartaoCreditoDTO.getTitular())
                .numero(cartaoCreditoDTO.getNumero())
                .cvv(cartaoCreditoDTO.getCvv())
                .bandeira(Bandeira.obterPorDescricao(cartaoCreditoDTO.getBandeira()))
                .cliente(null) // Avoid circular dependency
                .build();
    }

    @Override
    public OutputDTO toDTO(EntidadeDominio entidade) {
        CartaoCredito cartaoCredito = (CartaoCredito) entidade;
        CartaoCreditoOutputDTO cartaoCreditoDTO = new CartaoCreditoOutputDTO();
        cartaoCreditoDTO.setNomeIdentificacao(cartaoCredito.getNomeIdentificacao());
        cartaoCreditoDTO.setDataValidade(cartaoCredito.getDataValidade());
        cartaoCreditoDTO.setTitular(cartaoCredito.getTitular());
        cartaoCreditoDTO.setNumero(cartaoCredito.getNumero());
        cartaoCreditoDTO.setCvv(cartaoCredito.getCvv());
        cartaoCreditoDTO.setBandeira(cartaoCredito.getBandeira().getDescricao());
        cartaoCreditoDTO.setCliente(null); // Avoid circular dependency
        return cartaoCreditoDTO;
    }

}
