package controle.facade.dto.input.consulta;

import controle.facade.dto.input.InputDTO;

public interface InputConsultaDTO extends InputDTO {
    Integer getPaginaAtual();

    Integer getItensPorPagina();

    String getCampoOrdenacao();
}
