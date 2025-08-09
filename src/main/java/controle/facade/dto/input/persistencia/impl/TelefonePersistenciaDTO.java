package controle.facade.dto.input.persistencia.impl;

import controle.facade.dto.input.persistencia.InputPersistenciaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import dominio.EntidadeDominio;
import dominio.cliente.Telefone;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TelefonePersistenciaDTO implements InputPersistenciaDTO {
    private Integer id;
    private String tpTelefone; // Representar enum como String
    private String ddd;
    private String numero;

    @Override
    public Class<? extends EntidadeDominio> getDominio() {
        return Telefone.class;
    }
}