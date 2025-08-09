package controle.facade.dto.input.persistencia.impl;

import controle.facade.dto.input.persistencia.InputPersistenciaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import dominio.EntidadeDominio;
import dominio.livro.Categoria;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaPersistenciaDTO implements InputPersistenciaDTO {
    private Integer id;
    private String descricao;

    @Override
    public Class<? extends EntidadeDominio> getDominio() {
        return Categoria.class;
    }
}