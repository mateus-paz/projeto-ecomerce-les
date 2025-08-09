package controle.facade.dto.input.persistencia.impl;

import java.util.List;

import controle.facade.dto.input.persistencia.InputPersistenciaDTO;
import dominio.EntidadeDominio;
import dominio.livro.Livro;
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
public class LivroPersistenciaDTO implements InputPersistenciaDTO {
    private Integer id;
    private String titulo;
    private String editora;
    private int ano;
    private int edicao;
    private int numPaginas;
    private String ISBN;
    private String codigoBarras;
    private String sinopse;
    private String urlCapa;
    private DimensaoPersistenciaDTO dimensao;
    private String gpPrecificacao; // Representar enum como String
    private List<AutorPersistenciaDTO> autores;
    private List<CategoriaPersistenciaDTO> categorias;

    @Override
    public Class<? extends EntidadeDominio> getDominio() {
        return Livro.class;
    }
}