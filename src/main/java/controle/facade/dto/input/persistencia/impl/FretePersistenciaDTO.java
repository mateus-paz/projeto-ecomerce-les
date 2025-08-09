package controle.facade.dto.input.persistencia.impl;

import java.math.BigDecimal;

import controle.facade.dto.input.persistencia.InputPersistenciaDTO;
import dominio.EntidadeDominio;
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
public class FretePersistenciaDTO implements InputPersistenciaDTO {
    private Integer id;
    private int prazo;
    private BigDecimal valor;
    private EnderecoEntregaPersistenciaDTO endereco;

    @Override
    public Class<? extends EntidadeDominio> getDominio() {
        // return Frete.class; Domain Object
        return EntidadeDominio.class;
    }
}