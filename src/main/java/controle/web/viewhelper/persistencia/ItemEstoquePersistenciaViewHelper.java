package controle.web.viewhelper.persistencia;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.ItemEstoquePersistenciaDTO;
import controle.web.viewhelper.IViewHelper;

public class ItemEstoquePersistenciaViewHelper implements IViewHelper {

    @Override
    public ItemEstoquePersistenciaDTO getDTO(HttpServletRequest request) {
        ItemEstoquePersistenciaDTO itemDTO = new ItemEstoquePersistenciaDTO();
        String operacao = request.getParameter("operacao");

        if (operacao != null && operacao.equals("Alterar")) {
            String idItemParam = request.getParameter("idItem");
            if (idItemParam != null && !idItemParam.isEmpty()) {
                itemDTO.setId(Long.parseLong(idItemParam));
            }

            int qnt = 0;
            String txtQnt = request.getParameter("txtQnt");
            if (txtQnt != null && !txtQnt.equals("") && !txtQnt.equals("0")) {
                qnt = Integer.valueOf(txtQnt);
            }
            itemDTO.setQuantidade(qnt);

            String status = request.getParameter("cbbStatus");
            if (status != null) {
                itemDTO.setAtivo(status.equals("Ativo"));
            }

            String motivoInativacao = request.getParameter("txtMotivo");
            if (motivoInativacao != null) {
                itemDTO.setJustificativaStatus(motivoInativacao);
            }
        }
        return itemDTO;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // Lógica para definir a view após a persistência do item de estoque
    }
}
