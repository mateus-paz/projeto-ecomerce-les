package controle.facade.dto.input.persistencia.impl;

import dominio.EntidadeDominio;
import dominio.venda.ItemTroca;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class ItemTrocaPersistenciaDTO extends ItemPersistenciaDTO {
    private PedidoTrocaPersistenciaDTO pedido;

    @Override
    public Class<? extends EntidadeDominio> getDominio() {
        return ItemTroca.class;
    }
}