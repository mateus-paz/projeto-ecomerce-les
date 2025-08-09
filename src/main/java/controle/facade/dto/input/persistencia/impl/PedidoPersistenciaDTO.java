package controle.facade.dto.input.persistencia.impl;

import controle.facade.dto.input.persistencia.InputPersistenciaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

import dominio.EntidadeDominio;
import dominio.venda.Pedido;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoPersistenciaDTO implements InputPersistenciaDTO {
    private Integer id;
    private BigDecimal valorTotal;
    private ClientePersistenciaDTO cliente;
    private CarrinhoPersistenciaDTO carrinho;
    private EnderecoEntregaPersistenciaDTO endereco;
    private PedidoTrocaPersistenciaDTO troca;
    private String status; // Representar enum como String
    private List<CartaoCompraPersistenciaDTO> cartoes;
    private List<CupomPersistenciaDTO> cupons;
    private List<ItemPedidoPersistenciaDTO> itens;

    @Override
    public Class<? extends EntidadeDominio> getDominio() {
        return Pedido.class;
    }
}