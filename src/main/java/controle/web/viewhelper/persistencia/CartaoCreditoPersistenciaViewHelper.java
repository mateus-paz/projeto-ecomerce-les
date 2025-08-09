package controle.web.viewhelper.persistencia;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.CartaoCreditoPersistenciaDTO;
import controle.web.viewhelper.IViewHelper;
import dominio.cliente.Cliente;
import dominio.venda.Bandeira;

public class CartaoCreditoPersistenciaViewHelper implements IViewHelper {

    @Override
    public CartaoCreditoPersistenciaDTO getDTO(HttpServletRequest request) {
        CartaoCreditoPersistenciaDTO cartaoDTO = new CartaoCreditoPersistenciaDTO();

        Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");

        String txtNumero = request.getParameter("txtNumero");
        String txtCvv = request.getParameter("txtCVV");
        String txtTitular = request.getParameter("txtTitular");
        String txtValidade = request.getParameter("txtValidade");
        String txtNomeIdentificacao = request.getParameter("txtNomeIdentificacao");

        int nmBandeira = Integer.valueOf(request.getParameter("cbbBandeira"));
        Bandeira bandeira = null;
        if (nmBandeira == 1) {
            bandeira = Bandeira.VISA;
        } else if (nmBandeira == 2) {
            bandeira = Bandeira.MASTERCARD;
        }

        cartaoDTO.setNomeIdentificacao(txtNomeIdentificacao);
        cartaoDTO.setDataValidade(txtValidade);
        cartaoDTO.setTitular(txtTitular);
        cartaoDTO.setNumero(txtNumero);
        cartaoDTO.setCvv(txtCvv);
        // cartaoDTO.setBandeira(bandeira);
        // cartaoDTO.setCliente(cliente);

        return cartaoDTO;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // Lógica para definir a view após a persistência do cartão de crédito
    }
}
