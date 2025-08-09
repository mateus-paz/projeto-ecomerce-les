package controle.facade.dto.input.persistencia.impl;

import controle.facade.dto.input.persistencia.InputPersistenciaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import dominio.EntidadeDominio;
import dominio.venda.Item;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemPersistenciaDTO implements InputPersistenciaDTO {
    private Integer id;
    private LivroPersistenciaDTO livro;
    private int quantidade;
    private BigDecimal valorVenda;

    @Override
    public Class<? extends EntidadeDominio> getDominio() {
        return Item.class;
    }
}