package controle.web.viewhelper.persistencia;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.persistencia.impl.EnderecoEntregaPersistenciaDTO;
import controle.web.viewhelper.IViewHelper;
import dominio.cliente.Endereco;

public class EnderecoEntregaPersistenciaViewHelper implements IViewHelper {

    @Override
    public EnderecoEntregaPersistenciaDTO getDTO(HttpServletRequest request) {
        EnderecoEntregaPersistenciaDTO enderecoEntregaPersistenciaDTO = new EnderecoEntregaPersistenciaDTO();

        String operacao = request.getParameter("operacao");

        if (operacao != null && (operacao.equals("Salvar") || operacao.equals("Selecionar"))) {
            // Assuming EnderecoVH logic is translated or a new EnderecoDTO is used here
            // For simplicity, directly extracting parameters for Endereco
            Endereco endereco = new Endereco();
            // Populate endereco fields from request parameters, similar to EnderecoVH
            // This part would ideally use an EnderecoPersistenciaViewHelper
            endereco.setLogradouro(request.getParameter("txtLogradouro"));
            endereco.setNumero(request.getParameter("txtNumero"));
            endereco.setBairro(request.getParameter("txtBairro"));
            endereco.setCep(request.getParameter("txtCep"));
            endereco.setComplemento(request.getParameter("txtComplemento"));
            endereco.setNomeIdentificacao(request.getParameter("txtObservacao"));

            // enderecoEntregaPersistenciaDTO.setEndereco(endereco);

            String isSalvar = request.getParameter("swtSalvarEndereco");
            if (isSalvar != null) {
                enderecoEntregaPersistenciaDTO.setSalvar(true);
            }
        }

        return enderecoEntregaPersistenciaDTO;
    }

    @Override
    public void setView(HttpServletRequest request, HttpServletResponse response, InputDTO dto) {
        // Lógica para definir a view após a persistência do endereço de entrega
    }
}
