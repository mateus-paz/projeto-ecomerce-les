package controle.web.viewhelper.exclusao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.PagamentoPersistenciaDTO;
import controle.web.viewhelper.IViewHelper;

public class PagamentoExclusaoViewHelper implements IViewHelper {

    @Override
    public PagamentoPersistenciaDTO getDTO(HttpServletRequest request) {
        PagamentoPersistenciaDTO pagamentoPersistenciaDTO = new PagamentoPersistenciaDTO();
        String operacao = request.getParameter("operacao");

        if (operacao != null && operacao.equals("Remover")) {
            String idCartao = request.getParameter("idCartao");
            if (idCartao != null) {
                // pagamentoPersistenciaDTO.setNovoCartao(new
                // dominio.venda.CartaoCompra(Long.parseLong(idCartao)));
            } else {
                String idCupom = request.getParameter("idCupom");
                if (idCupom != null) {
                    // pagamentoPersistenciaDTO.setNovoCupom(new
                    // dominio.venda.Cupom(Long.parseLong(idCupom)));
                }
            }
        }
        return pagamentoPersistenciaDTO;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // Lógica para definir a view após a exclusão do pagamento
    }
}
