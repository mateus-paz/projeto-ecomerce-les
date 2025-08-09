package controle.facade.dto.input.persistencia.impl;

import java.math.BigDecimal;

import dominio.EntidadeDominio;
import dominio.venda.ItemEstoque;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemEstoquePersistenciaDTO extends ItemPersistenciaDTO {
    private BigDecimal precoCusto;
    private boolean ativo;
    private String justificativaStatus;
    private String catStatus; // Representar enum como String

    @Override
    public Class<? extends EntidadeDominio> getDominio() {
        return ItemEstoque.class;
    }
}