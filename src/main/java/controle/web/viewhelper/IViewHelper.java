package controle.web.viewhelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;

public interface IViewHelper {
    InputDTO getDTO(HttpServletRequest request);

    void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto);
}
