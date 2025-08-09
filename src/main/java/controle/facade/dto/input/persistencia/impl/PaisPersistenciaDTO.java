package controle.facade.dto.input.persistencia.impl;

import controle.facade.dto.input.persistencia.InputPersistenciaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import dominio.EntidadeDominio;
import dominio.cliente.Pais;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaisPersistenciaDTO implements InputPersistenciaDTO {
    private Integer id;
    private String nome;

    @Override
    public Class<? extends EntidadeDominio> getDominio() {
        return Pais.class;
    }
}