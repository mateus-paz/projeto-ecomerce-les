package controle.facade.dto.input.persistencia.impl;

import dominio.EntidadeDominio;
import dominio.venda.ItemCarrinho;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemCarrinhoPersistenciaDTO extends ItemPersistenciaDTO {
    private CarrinhoPersistenciaDTO carrinho;

    @Override
    public Class<? extends EntidadeDominio> getDominio() {
        return ItemCarrinho.class;
    }
}