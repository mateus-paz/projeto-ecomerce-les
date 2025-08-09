package controle.web.viewhelper.busca;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.web.viewhelper.IViewHelper;

public class GraficoBuscaViewHelper implements IViewHelper {

    @Override
    public InputDTO getDTO(HttpServletRequest request) {
        // GraficoVH does not have a direct search operation for a specific entity.
        // It processes dates for chart generation.
        return null;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // No specific view setting for search of a chart.
    }
}
