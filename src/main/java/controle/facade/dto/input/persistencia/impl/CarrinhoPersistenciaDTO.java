package controle.facade.dto.input.persistencia.impl;

import java.math.BigDecimal;
import java.util.List;

import controle.facade.dto.input.persistencia.InputPersistenciaDTO;
import dominio.EntidadeDominio;
import dominio.venda.Carrinho;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarrinhoPersistenciaDTO implements InputPersistenciaDTO {
    private Integer id;
    private List<ItemCarrinhoPersistenciaDTO> itens;
    private BigDecimal valorTotal;

    @Override
    public Class<? extends EntidadeDominio> getDominio() {
        return Carrinho.class;
    }
}