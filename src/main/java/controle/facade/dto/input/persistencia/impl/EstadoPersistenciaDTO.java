package controle.facade.dto.input.persistencia.impl;

import controle.facade.dto.input.persistencia.InputPersistenciaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import dominio.EntidadeDominio;
import dominio.cliente.Estado;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstadoPersistenciaDTO implements InputPersistenciaDTO {
    private Integer id;
    private String nome;
    private PaisPersistenciaDTO pais; // Usar o DTO de persistência para Pais

    @Override
    public Class<? extends EntidadeDominio> getDominio() {
        return Estado.class;
    }
}