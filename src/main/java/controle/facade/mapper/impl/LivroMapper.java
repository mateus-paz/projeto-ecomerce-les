package controle.facade.mapper.impl;

import controle.facade.dto.input.persistencia.InputPersistenciaDTO;
import controle.facade.dto.input.persistencia.impl.LivroPersistenciaDTO;
import controle.facade.dto.output.OutputDTO;
import controle.facade.dto.output.impl.LivroOutputDTO;
import controle.facade.mapper.IMapper;
import dominio.EntidadeDominio;
import dominio.livro.Livro;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LivroMapper implements IMapper {

    @Override
    public EntidadeDominio toEntidade(InputPersistenciaDTO dto) {
        LivroPersistenciaDTO livroDTO = (LivroPersistenciaDTO) dto;
        Livro livro = Livro.builder()
                .titulo(livroDTO.getTitulo())
                .editora(livroDTO.getEditora())
                .ano(livroDTO.getEdicao()) // Assuming edicao in DTO is year
                .edicao(livroDTO.getEdicao())
                .numPaginas(livroDTO.getNumPaginas())
                .ISBN(livroDTO.getISBN())
                .codigoBarras(livroDTO.getCodigoBarras())
                .sinopse(livroDTO.getSinopse())
                // .dimensao(Dimensao.builder()
                // .altura(livroDTO.getAltura())
                // .largura(livroDTO.getLargura())
                // .peso(livroDTO.getPeso())
                // .profundidade(livroDTO.getProfundidade())
                // .build()))
                // .gpPrecificacao(GrupoPrecificacao.obterPorDescricao(livroDTO.getGrupoPrecificacao()))
                .build();

        // if (livroDTO.getAutor() != null && !livroDTO.getAutor().isEmpty()) {
        // livro.setAutores(Arrays.asList(Autor.builder().nome(livroDTO.getAutor()).build()));
        // }
        // if (livroDTO.getCategoria() != null && !livroDTO.getCategoria().isEmpty()) {
        // livro.setCategorias(Arrays.asList(Categoria.builder().descricao(livroDTO.getCategoria()).build()));
        // }

        return livro;
    }

    @Override
    public OutputDTO toDTO(EntidadeDominio entidade) {
        Livro livro = (Livro) entidade;
        LivroOutputDTO livroDTO = new LivroOutputDTO();
        livroDTO.setTitulo(livro.getTitulo());
        livroDTO.setEditora(livro.getEditora());
        livroDTO.setEdicao(livro.getEdicao());
        livroDTO.setISBN(livro.getISBN());
        livroDTO.setNumPaginas(livro.getNumPaginas());
        livroDTO.setSinopse(livro.getSinopse());
        // livroDTO.setAltura(livro.getDimensao().getAltura());
        // livroDTO.setLargura(livro.getDimensao().getLargura());
        // livroDTO.setProfundidade(livro.getDimensao().getProfundidade());
        // livroDTO.setPeso(livro.getDimensao().getPeso());
        livroDTO.setCodigoBarras(livro.getCodigoBarras());
        // livroDTO.setGrupoPrecificacao:(livro.getGpPrecificacao().getDescricao());

        // if (livro.getAutores() != null && !livro.getAutores().isEmpty()) {
        // livroDTO.setAutor(livro.getAutores().get(0).getNome());
        // }
        // if (livro.getCategorias() != null && !livro.getCategorias().isEmpty()) {
        // livroDTO.setCategoria(livro.getCategorias().get(0).getDescricao());
        // }

        return livroDTO;
    }

}
