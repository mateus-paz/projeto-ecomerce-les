package controle.facade.dto.input.persistencia.impl;

import java.math.BigDecimal;
import java.util.List;

import controle.facade.dto.input.persistencia.InputPersistenciaDTO;
import dominio.EntidadeDominio;
import dominio.venda.PedidoTroca;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class PedidoTrocaPersistenciaDTO implements InputPersistenciaDTO {
    private Integer id;
    private BigDecimal valorTotal;
    private PedidoPersistenciaDTO pedido;
    private CupomPersistenciaDTO cupom;
    private List<ItemTrocaPersistenciaDTO> itens;

    @Override
    public Class<? extends EntidadeDominio> getDominio() {
        return PedidoTroca.class;
    }
}