package controle.facade.mapper.impl;

import controle.facade.dto.input.persistencia.InputPersistenciaDTO;
import controle.facade.dto.input.persistencia.impl.CarrinhoPersistenciaDTO;
import controle.facade.dto.output.OutputDTO;
import controle.facade.dto.output.impl.CarrinhoOutputDTO;
import controle.facade.mapper.IMapper;
import dominio.EntidadeDominio;
import dominio.venda.Carrinho;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CarrinhoMapper implements IMapper {

    private final ClienteMapper clienteMapper;

    @Override
    public EntidadeDominio toEntidade(InputPersistenciaDTO dto) {
        CarrinhoPersistenciaDTO carrinhoDTO = (CarrinhoPersistenciaDTO) dto;
        Carrinho carrinho = Carrinho.builder()
                .build();
        carrinho.setId(carrinhoDTO.getId().intValue());
        // Cliente is not directly mapped in Carrinho entity, so skipping for now
        // if (carrinhoDTO.getCliente() != null) {
        // carrinho.setCliente((dominio.cliente.Cliente)
        // clienteMapper.toEntidade(carrinhoDTO.getCliente()));
        // }
        return carrinho;
    }

    @Override
    public OutputDTO toDTO(EntidadeDominio entidade) {
        Carrinho carrinho = (Carrinho) entidade;
        CarrinhoOutputDTO carrinhoDTO = new CarrinhoOutputDTO();
        carrinhoDTO.setId(carrinho.getId());
        // Cliente is not directly mapped in Carrinho entity, so skipping for now
        // if (carrinho.getCliente() != null) {
        // carrinhoDTO.setCliente((controle.facade.dto.output.impl.ClienteOutputDTO)
        // clienteMapper.toDTO(carrinho.getCliente()));
        // }
        return carrinhoDTO;
    }

}
