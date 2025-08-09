package controle.facade.dto.input.exclusao.impl;

import controle.facade.dto.input.exclusao.InputExclusaoDTO;
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
public class TelefoneExclusaoDTO implements InputExclusaoDTO {
    private Integer id;

    @Override
    public Class<? extends EntidadeDominio> getDominio() {
        return Telefone.class;
    }
}