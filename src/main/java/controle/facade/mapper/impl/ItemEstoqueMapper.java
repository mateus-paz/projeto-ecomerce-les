package controle.facade.mapper.impl;

import controle.facade.dto.input.persistencia.InputPersistenciaDTO;
import controle.facade.dto.input.persistencia.impl.ItemEstoquePersistenciaDTO;
import controle.facade.dto.output.OutputDTO;
import controle.facade.dto.output.impl.ItemEstoqueOutputDTO;
import controle.facade.mapper.IMapper;
import dominio.EntidadeDominio;
import dominio.venda.CategoriaStatus;
import dominio.venda.ItemEstoque;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ItemEstoqueMapper implements IMapper {

    private final LivroMapper livroMapper;

    @Override
    public EntidadeDominio toEntidade(InputPersistenciaDTO dto) {
        ItemEstoquePersistenciaDTO itemEstoqueDTO = (ItemEstoquePersistenciaDTO) dto;
        ItemEstoque itemEstoque = ItemEstoque.builder()
                // .quantidade(itemEstoqueDTO.getQuantidade())
                .ativo(itemEstoqueDTO.isAtivo())
                .justificativaStatus(itemEstoqueDTO.getJustificativaStatus())
                .catStatus(CategoriaStatus.obterPorDescricao(itemEstoqueDTO.getJustificativaStatus())) // Assuming
                                                                                                       // justificativaStatus
                                                                                                       // maps to
                                                                                                       // CategoriaStatus
                .build();
        // Livro and precoCusto are not in DTO, so not mapped here
        return itemEstoque;
    }

    @Override
    public OutputDTO toDTO(EntidadeDominio entidade) {
        ItemEstoque itemEstoque = (ItemEstoque) entidade;
        ItemEstoqueOutputDTO itemEstoqueDTO = new ItemEstoqueOutputDTO();
        itemEstoqueDTO.setId(itemEstoque.getId());
        itemEstoqueDTO.setQuantidade(itemEstoque.getQuantidade());
        itemEstoqueDTO.setAtivo(itemEstoque.isAtivo());
        itemEstoqueDTO.setJustificativaStatus(itemEstoque.getJustificativaStatus());
        // Livro and precoCusto are not in DTO, so not mapped here
        return itemEstoqueDTO;
    }

}
