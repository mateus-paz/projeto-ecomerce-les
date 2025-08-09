package controle.facade.mapper.impl;

import java.util.stream.Collectors;

import controle.facade.dto.input.persistencia.InputPersistenciaDTO;
import controle.facade.dto.input.persistencia.impl.EstoquePersistenciaDTO;
import controle.facade.dto.output.OutputDTO;
import controle.facade.dto.output.impl.EstoqueOutputDTO;
import controle.facade.dto.output.impl.ItemEstoqueOutputDTO;
import controle.facade.mapper.IMapper;
import dominio.EntidadeDominio;
import dominio.venda.Estoque;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EstoqueMapper implements IMapper {

    private final ItemEstoqueMapper itemEstoqueMapper;

    @Override
    public EntidadeDominio toEntidade(InputPersistenciaDTO dto) {
        EstoquePersistenciaDTO estoqueDTO = (EstoquePersistenciaDTO) dto;
        Estoque estoque = Estoque.builder()
                .valorBusca(estoqueDTO.getValorBusca())
                .parametros(estoqueDTO.getParametros())
                .build();

        if (estoqueDTO.getItens() != null) {
            estoque.setItens(estoqueDTO.getItens().stream()
                    .map(itemEstoqueMapper::toEntidade)
                    .map(e -> (dominio.venda.ItemEstoque) e)
                    .collect(Collectors.toList()));
        }

        return estoque;
    }

    @Override
    public OutputDTO toDTO(EntidadeDominio entidade) {
        Estoque estoque = (Estoque) entidade;
        EstoqueOutputDTO estoqueDTO = new EstoqueOutputDTO();
        estoqueDTO.setId(estoque.getId());
        estoqueDTO.setValorBusca(estoque.getValorBusca());
        estoqueDTO.setParametros(estoque.getParametros());

        if (estoque.getItens() != null) {
            estoqueDTO.setItens(estoque.getItens().stream()
                    .map(itemEstoqueMapper::toDTO)
                    .map(e -> (ItemEstoqueOutputDTO) e)
                    .collect(Collectors.toList()));
        }

        return estoqueDTO;
    }
}
