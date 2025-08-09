package controle.facade.dto.input.persistencia.impl;

import controle.facade.dto.input.persistencia.InputPersistenciaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import dominio.EntidadeDominio;
import dominio.venda.EnderecoEntrega;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoEntregaPersistenciaDTO implements InputPersistenciaDTO {
    private Integer id;
    private boolean salvar;
    private EnderecoPersistenciaDTO endereco;
    private FretePersistenciaDTO frete;

    @Override
    public Class<? extends EntidadeDominio> getDominio() {
        return EnderecoEntrega.class;
    }
}