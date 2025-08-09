package controle.facade.dto.input.persistencia.impl;

import controle.facade.dto.input.persistencia.InputPersistenciaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import dominio.EntidadeDominio;
import dominio.venda.Estoque;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstoquePersistenciaDTO implements InputPersistenciaDTO {
    private Integer id;
    private String valorBusca;
    private List<ItemEstoquePersistenciaDTO> itens;
    private List<String> parametros;

    @Override
    public Class<? extends EntidadeDominio> getDominio() {
        return Estoque.class;
    }
}