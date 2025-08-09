package controle.web.viewhelper.exclusao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.CarrinhoPersistenciaDTO;
import controle.web.viewhelper.IViewHelper;

public class CarrinhoExclusaoViewHelper implements IViewHelper {

    @Override
    public CarrinhoPersistenciaDTO getDTO(HttpServletRequest request) {
        CarrinhoPersistenciaDTO CarrinhoPersistenciaDTO = new CarrinhoPersistenciaDTO();
        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            CarrinhoPersistenciaDTO.setId(Long.parseLong(idParam));
        }
        return CarrinhoPersistenciaDTO;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // Lógica para definir a view após a exclusão do carrinho
    }
}
