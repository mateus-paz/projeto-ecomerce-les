package controle.facade.mapper;

import controle.facade.dto.input.persistencia.InputPersistenciaDTO;
import controle.facade.dto.output.OutputDTO;
import dominio.EntidadeDominio;

public interface IMapper {
    EntidadeDominio toEntidade(InputPersistenciaDTO dto);

    OutputDTO toDTO(EntidadeDominio entidade);
}
