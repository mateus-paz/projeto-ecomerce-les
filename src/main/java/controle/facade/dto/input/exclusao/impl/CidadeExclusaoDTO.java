package controle.facade.dto.input.exclusao.impl;

import controle.facade.dto.input.exclusao.InputExclusaoDTO;
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
public class CidadeExclusaoDTO implements InputExclusaoDTO {
    private Integer id; // Assumindo que a exclusão é por ID, como EntidadeDominio

    @Override
    public Class<? extends EntidadeDominio> getDominio() {
        return Cidade.class;
    }
}