package controle.facade.dto.input.consulta.impl;

import controle.facade.dto.input.consulta.InputConsultaDTO;
import dominio.EntidadeDominio;
import dominio.livro.Dimensao;
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
public class DimensaoConsultaDTO implements InputConsultaDTO {
    private Integer id;
    private Integer paginaAtual;
    private Integer itensPorPagina;
    private String campoOrdenacao;

    @Override
    public Class<? extends EntidadeDominio> getDominio() {
        return Dimensao.class;
    }
}