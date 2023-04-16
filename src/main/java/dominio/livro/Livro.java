package dominio.livro;

import java.util.List;

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
public class Livro extends EntidadeDominio {
    private String titulo;
    private String editora;
    private int ano;
    private int edicao;
    private int numPaginas;
    private String ISBN;
    private String codigoBarras;
    private String sinopse;
    private String urlCapa;

    private Dimensao dimensao;
    private GrupoPrecificacao gpPrecificacao;

    private List<Autor> autores;
    private List<Categoria> categorias;

}