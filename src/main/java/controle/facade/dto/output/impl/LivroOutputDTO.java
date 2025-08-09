package controle.facade.dto.output.impl;

import java.util.List;

import controle.facade.dto.output.OutputDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LivroOutputDTO implements OutputDTO {
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
    private DimensaoOutputDTO dimensao;
    private String gpPrecificacao;
    private List<AutorOutputDTO> autores;
    private List<CategoriaOutputDTO> categorias;
}
