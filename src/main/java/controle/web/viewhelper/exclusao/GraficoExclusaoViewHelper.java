package controle.web.viewhelper.exclusao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.web.viewhelper.IViewHelper;

public class GraficoExclusaoViewHelper implements IViewHelper {

    @Override
    public InputDTO getDTO(HttpServletRequest request) {
        // GraficoVH does not have a direct exclusion operation.
        return null;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // No specific view setting for exclusion of a chart.
    }
}
