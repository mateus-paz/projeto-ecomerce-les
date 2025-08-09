package controle.facade.dto.input.consulta.impl;

import controle.facade.dto.input.consulta.InputConsultaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import dominio.EntidadeDominio;
import dominio.venda.ItemPedido;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoConsultaDTO implements InputConsultaDTO {
    private Integer id;
    private Integer paginaAtual;
    private Integer itensPorPagina;
    private String campoOrdenacao;

    @Override
    public Class<? extends EntidadeDominio> getDominio() {
        return ItemPedido.class;
    }
}