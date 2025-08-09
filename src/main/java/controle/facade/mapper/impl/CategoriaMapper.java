package controle.facade.mapper.impl;

import controle.facade.dto.input.persistencia.InputPersistenciaDTO;
import controle.facade.dto.input.persistencia.impl.CategoriaPersistenciaDTO;
import controle.facade.dto.output.OutputDTO;
import controle.facade.dto.output.impl.CategoriaOutputDTO;
import controle.facade.mapper.IMapper;
import dominio.EntidadeDominio;
import dominio.livro.Categoria;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoriaMapper implements IMapper {

    @Override
    public EntidadeDominio toEntidade(InputPersistenciaDTO dto) {
        // Assuming dto is a simple string for category description
        CategoriaPersistenciaDTO categoriaDTO = (CategoriaPersistenciaDTO) dto;
        String categoriaDescricao = (String) categoriaDTO.getDescricao(); // Assuming getConteudo() returns the string
        return Categoria.builder().descricao(categoriaDescricao).build();
    }

    @Override
    public OutputDTO toDTO(EntidadeDominio entidade) {
        Categoria categoria = (Categoria) entidade;
        // Assuming OutputDTO can be constructed with a string
        // This might need adjustment based on the actual OutputDTO structure
        return new CategoriaOutputDTO(categoria.getId(), categoria.getDescricao());
    }

}
