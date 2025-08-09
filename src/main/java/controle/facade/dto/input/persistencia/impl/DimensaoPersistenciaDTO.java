package controle.facade.dto.input.persistencia.impl;

import controle.facade.dto.input.persistencia.InputPersistenciaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import dominio.EntidadeDominio;
import dominio.livro.Dimensao;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DimensaoPersistenciaDTO implements InputPersistenciaDTO {
    private Integer id;
    private double altura;
    private double largura;
    private double peso;
    private double profundidade;

    @Override
    public Class<? extends EntidadeDominio> getDominio() {
        return Dimensao.class;
    }
}