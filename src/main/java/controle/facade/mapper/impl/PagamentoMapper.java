package controle.facade.mapper.impl;

import java.util.stream.Collectors;

import controle.facade.dto.input.persistencia.InputPersistenciaDTO;
import controle.facade.dto.input.persistencia.impl.PagamentoPersistenciaDTO;
import controle.facade.dto.output.OutputDTO;
import controle.facade.dto.output.impl.CarrinhoOutputDTO;
import controle.facade.dto.output.impl.CartaoCompraOutputDTO;
import controle.facade.dto.output.impl.CupomOutputDTO;
import controle.facade.dto.output.impl.EnderecoEntregaOutputDTO;
import controle.facade.dto.output.impl.PagamentoOutputDTO;
import controle.facade.mapper.IMapper;
import dominio.EntidadeDominio;
import dominio.venda.Pagamento;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PagamentoMapper implements IMapper {

    private final CarrinhoMapper carrinhoMapper;
    private final EnderecoEntregaMapper enderecoEntregaMapper;
    private final CartaoCompraMapper cartaoCompraMapper;
    private final CupomMapper cupomMapper;

    @Override
    public EntidadeDominio toEntidade(InputPersistenciaDTO dto) {
        PagamentoPersistenciaDTO pagamentoDTO = (PagamentoPersistenciaDTO) dto;
        Pagamento pagamento = Pagamento.builder()
                .totalCompra(pagamentoDTO.getTotalCompra())
                .carrinho(pagamentoDTO.getCarrinho() != null
                        ? (dominio.venda.Carrinho) carrinhoMapper.toEntidade(pagamentoDTO.getCarrinho())
                        : null)
                .endereco(pagamentoDTO.getEndereco() != null
                        ? (dominio.venda.EnderecoEntrega) enderecoEntregaMapper.toEntidade(pagamentoDTO.getEndereco())
                        : null)
                .build();

        if (pagamentoDTO.getCartoes() != null) {
            pagamento.setCartoes(pagamentoDTO.getCartoes().stream()
                    .map(cartaoCompraMapper::toEntidade)
                    .map(e -> (dominio.venda.CartaoCompra) e)
                    .collect(Collectors.toList()));
        }
        if (pagamentoDTO.getCupons() != null) {
            pagamento.setCupons(pagamentoDTO.getCupons().stream()
                    .map(cupomMapper::toEntidade)
                    .map(e -> (dominio.venda.Cupom) e)
                    .collect(Collectors.toList()));
        }
        if (pagamentoDTO.getNovoCartao() != null) {
            pagamento.setNovoCartao(
                    (dominio.venda.CartaoCompra) cartaoCompraMapper.toEntidade(pagamentoDTO.getNovoCartao()));
        }
        if (pagamentoDTO.getNovoCupom() != null) {
            pagamento.setNovoCupom((dominio.venda.Cupom) cupomMapper.toEntidade(pagamentoDTO.getNovoCupom()));
        }

        return pagamento;
    }

    @Override
    public OutputDTO toDTO(EntidadeDominio entidade) {
        Pagamento pagamento = (Pagamento) entidade;
        PagamentoOutputDTO pagamentoDTO = new PagamentoOutputDTO();
        pagamentoDTO.setId(pagamento.getId());
        pagamentoDTO.setTotalCompra(pagamento.getTotalCompra());
        pagamentoDTO.setCarrinho(pagamento.getCarrinho() != null
                ? (CarrinhoOutputDTO) carrinhoMapper.toDTO(pagamento.getCarrinho())
                : null);
        pagamentoDTO.setEndereco(pagamento.getEndereco() != null
                ? (EnderecoEntregaOutputDTO) enderecoEntregaMapper
                        .toDTO(pagamento.getEndereco())
                : null);

        if (pagamento.getCartoes() != null) {
            pagamentoDTO.setCartoes(pagamento.getCartoes().stream()
                    .map(cartaoCompraMapper::toDTO)
                    .map(e -> (CartaoCompraOutputDTO) e)
                    .collect(Collectors.toList()));
        }
        if (pagamento.getCupons() != null) {
            pagamentoDTO.setCupons(pagamento.getCupons().stream()
                    .map(cupomMapper::toDTO)
                    .map(e -> (CupomOutputDTO) e)
                    .collect(Collectors.toList()));
        }
        if (pagamento.getNovoCartao() != null) {
            pagamentoDTO.setNovoCartao((CartaoCompraOutputDTO) cartaoCompraMapper
                    .toDTO(pagamento.getNovoCartao()));
        }
        if (pagamento.getNovoCupom() != null) {
            pagamentoDTO.setNovoCupom(
                    (CupomOutputDTO) cupomMapper.toDTO(pagamento.getNovoCupom()));
        }

        return pagamentoDTO;
    }

}
