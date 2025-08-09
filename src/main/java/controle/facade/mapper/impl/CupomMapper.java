package controle.facade.mapper.impl;

import controle.facade.dto.input.persistencia.InputPersistenciaDTO;
import controle.facade.dto.input.persistencia.impl.CupomPersistenciaDTO;
import controle.facade.dto.output.OutputDTO;
import controle.facade.dto.output.impl.CupomOutputDTO;
import controle.facade.mapper.IMapper;
import dominio.EntidadeDominio;
import dominio.venda.Cupom;
import dominio.venda.TipoCupom;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CupomMapper implements IMapper {

    @Override
    public EntidadeDominio toEntidade(InputPersistenciaDTO dto) {
        CupomPersistenciaDTO cupomDTO = (CupomPersistenciaDTO) dto;
        return Cupom.builder()
                .codigo(cupomDTO.getCodigo())
                .tpCupom(TipoCupom.obterPorDescricao(cupomDTO.getTpCupom()))
                .valor(cupomDTO.getValor())
                .cliente(null) // Avoid circular dependency
                .pedido(null) // Avoid circular dependency
                .build();
    }

    @Override
    public OutputDTO toDTO(EntidadeDominio entidade) {
        Cupom cupom = (Cupom) entidade;
        CupomOutputDTO cupomDTO = new CupomOutputDTO();
        cupomDTO.setCodigo(cupom.getCodigo());
        cupomDTO.setTpCupom(cupom.getTpCupom().getDescricao());
        cupomDTO.setValor(cupom.getValor());
        return cupomDTO;
    }

}
