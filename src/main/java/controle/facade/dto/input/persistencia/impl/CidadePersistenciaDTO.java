package controle.facade.dto.input.persistencia.impl;

import controle.facade.dto.input.persistencia.InputPersistenciaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import dominio.EntidadeDominio;
import dominio.cliente.Cidade;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CidadePersistenciaDTO implements InputPersistenciaDTO {
    private Integer id;
    private String nome;
    private EstadoPersistenciaDTO estado; // Usar o DTO de persistência para Estado

    @Override
    public Class<? extends EntidadeDominio> getDominio() {
        return Cidade.class;
    }
}