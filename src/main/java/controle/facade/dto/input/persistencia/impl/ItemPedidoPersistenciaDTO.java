package controle.facade.dto.input.persistencia.impl;

import dominio.EntidadeDominio;
import dominio.venda.ItemPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoPersistenciaDTO extends ItemPersistenciaDTO {
    private PedidoPersistenciaDTO pedido;

    @Override
    public Class<? extends EntidadeDominio> getDominio() {
        return ItemPedido.class;
    }
}