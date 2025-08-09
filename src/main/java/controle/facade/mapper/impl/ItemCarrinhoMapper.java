package controle.facade.mapper.impl;

import controle.facade.dto.input.persistencia.InputPersistenciaDTO;
import controle.facade.dto.input.persistencia.impl.ItemCarrinhoPersistenciaDTO;
import controle.facade.dto.output.OutputDTO;
import controle.facade.dto.output.impl.CarrinhoOutputDTO;
import controle.facade.dto.output.impl.ItemCarrinhoOutputDTO;
import controle.facade.mapper.IMapper;
import dominio.EntidadeDominio;
import dominio.venda.ItemCarrinho;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ItemCarrinhoMapper implements IMapper {

    private final CarrinhoMapper carrinhoMapper;
    private final LivroMapper livroMapper;

    @Override
    public EntidadeDominio toEntidade(InputPersistenciaDTO dto) {
        ItemCarrinhoPersistenciaDTO itemCarrinhoDTO = (ItemCarrinhoPersistenciaDTO) dto;
        return ItemCarrinho.builder()
                // .quantidade(itemCarrinhoDTO.getQuantidade())
                // .valorVenda(itemCarrinhoDTO.getValorVenda())
                .carrinho(itemCarrinhoDTO.getCarrinho() != null
                        ? (dominio.venda.Carrinho) carrinhoMapper.toEntidade(itemCarrinhoDTO.getCarrinho())
                        : null)
                .build();
    }

    @Override
    public OutputDTO toDTO(EntidadeDominio entidade) {
        ItemCarrinho itemCarrinho = (ItemCarrinho) entidade;
        ItemCarrinhoOutputDTO itemCarrinhoDTO = new ItemCarrinhoOutputDTO();
        itemCarrinhoDTO.setQuantidade(itemCarrinho.getQuantidade());
        itemCarrinhoDTO.setValorVenda(itemCarrinho.getValorVenda());
        itemCarrinhoDTO.setCarrinho(itemCarrinho.getCarrinho() != null
                ? (CarrinhoOutputDTO) carrinhoMapper.toDTO(itemCarrinho.getCarrinho())
                : null);
        return itemCarrinhoDTO;
    }

}
