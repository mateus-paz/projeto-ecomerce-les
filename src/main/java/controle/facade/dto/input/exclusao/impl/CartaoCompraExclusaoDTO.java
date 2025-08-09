package controle.facade.dto.input.exclusao.impl;

import controle.facade.dto.input.exclusao.InputExclusaoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import dominio.EntidadeDominio;
import dominio.venda.CartaoCompra;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartaoCompraExclusaoDTO implements InputExclusaoDTO {
    private Integer id;

    @Override
    public Class<? extends EntidadeDominio> getDominio() {
        return CartaoCompra.class;
    }
}