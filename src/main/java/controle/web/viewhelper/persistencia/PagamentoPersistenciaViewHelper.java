package controle.web.viewhelper.persistencia;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.PagamentoPersistenciaDTO;
import controle.web.viewhelper.IViewHelper;
import dominio.venda.Carrinho;
import dominio.venda.CartaoCompra;
import dominio.venda.CartaoCredito;
import dominio.venda.Cupom;
import dominio.venda.EnderecoEntrega;

public class PagamentoPersistenciaViewHelper implements IViewHelper {

    @Override
    public PagamentoPersistenciaDTO getDTO(HttpServletRequest request) {
        PagamentoPersistenciaDTO pagamentoPersistenciaDTO = new PagamentoPersistenciaDTO();

        String operacao = request.getParameter("operacao");
        String valorParam = request.getParameter("txtValor");

        if (operacao != null) {
            if (operacao.equals("Alterar")) {
                // Assuming the existing payment object is in session
                // For a DTO-based approach, we'd typically pass the ID and new values
                // Here, we'll simulate by setting the new card info
                String idCartao = request.getParameter("idCartao");
                if (idCartao != null && valorParam != null) {
                    CartaoCompra cartaoCompra = new CartaoCompra();
                    // cartaoCompra.setId(Long.parseLong(idCartao));
                    cartaoCompra.setValor(new BigDecimal(valorParam));
                    // pagamentoPersistenciaDTO.setNovoCartao(cartaoCompra);
                }
            } else if (operacao.equals("Remover")) {
                String idCartao = request.getParameter("idCartao");
                if (idCartao != null) {
                    CartaoCompra cartaoCompra = new CartaoCompra();
                    // cartaoCompra.setId(Long.parseLong(idCartao));
                    // pagamentoPersistenciaDTO.setNovoCartao(cartaoCompra);
                } else {
                    String idCupom = request.getParameter("idCupom");
                    if (idCupom != null) {
                        Cupom cupom = new Cupom();
                        // cupom.setId(Long.parseLong(idCupom));
                        // pagamentoPersistenciaDTO.setNovoCupom(cupom);
                    }
                }
            } else if (operacao.equals("Adicionar") || operacao.equals("Selecionar")) {
                if (valorParam != null) {
                    // Assuming CartaoCreditoDTO is handled by its own VH or constructed here
                    CartaoCredito cartaoCredito = new CartaoCredito();
                    cartaoCredito.setNumero(request.getParameter("txtNumero"));
                    cartaoCredito.setCvv(request.getParameter("txtCVV"));
                    cartaoCredito.setTitular(request.getParameter("txtTitular"));
                    cartaoCredito.setDataValidade(request.getParameter("txtValidade"));
                    cartaoCredito.setNomeIdentificacao(request.getParameter("txtNomeIdentificacao"));
                    // Handle Bandeira conversion if needed

                    CartaoCompra cartaoCompra = new CartaoCompra();
                    cartaoCompra.setCartao(cartaoCredito);
                    cartaoCompra.setValor(new BigDecimal(valorParam));

                    String isSalvar = request.getParameter("swtSalvarCartao");
                    if (isSalvar != null) {
                        cartaoCompra.setRegistrar(true);
                    }
                    // pagamentoPersistenciaDTO.setNovoCartao(cartaoCompra);
                } else {
                    // Assuming ClienteDTO is handled by its own VH or constructed here
                    Cupom cupom = new Cupom();
                    cupom.setCodigo(request.getParameter("txtCodigo"));
                    // Set client if available
                    // pagamentoPersistenciaDTO.setNovoCupom(cupom);
                }
            }
        } else {
            // Initial creation of PagamentoPersistenciaDTO
            // Assuming Carrinho and EnderecoEntrega are already in session or passed as IDs
            Carrinho carrinho = (Carrinho) request.getSession().getAttribute("carrinho");
            EnderecoEntrega endereco = (EnderecoEntrega) request.getSession().getAttribute("enderecoSelecionado");
            // pagamentoPersistenciaDTO.setCarrinho(carrinho);
            // pagamentoPersistenciaDTO.setEndereco(endereco);
        }

        return pagamentoPersistenciaDTO;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // Lógica para definir a view após a persistência do pagamento
    }
}
