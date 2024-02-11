package controle.web.vh.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.web.vh.IViewHelper;
import dominio.EntidadeDominio;
import dominio.Resultado;
import dominio.cliente.Endereco;
import dominio.venda.EnderecoEntrega;

public class EnderecoEntregaVH implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        EnderecoEntrega end = null;

        String operacao = request.getParameter("operacao");
        end = (EnderecoEntrega) request.getSession().getAttribute("enderecoSelecionado");

        if (end == null)
            end = new EnderecoEntrega();

        if (operacao != null)
            if (operacao.equals("Remover")) {
                end = (EnderecoEntrega) request.getSession().getAttribute("enderecoSelecionado");

            } else if (operacao.equals("Salvar") || operacao.equals("Selecionar")) {

                EnderecoVH endVh = new EnderecoVH();
                Endereco endereco = (Endereco) endVh.getEntidade(request);

                end = EnderecoEntrega.builder()
                        .endereco(endereco).build();

                String isSalvar = request.getParameter("swtSalvarEndereco");

                if (isSalvar != null) {
                    end.setSalvar(true);
                }
            }

        return end;
    }

    @Override
    public void setEntidade(HttpServletResponse response, HttpServletRequest request, Resultado resultado) {
        request.getSession().setAttribute("enderecoSelecionado", (EnderecoEntrega) resultado.getEntidades().get(0));
    }

}
