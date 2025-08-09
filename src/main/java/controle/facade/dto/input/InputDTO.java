package controle.facade.dto.input;

import dominio.EntidadeDominio;

public interface InputDTO {
    Integer getId();

    Class<? extends EntidadeDominio> getDominio();
}
