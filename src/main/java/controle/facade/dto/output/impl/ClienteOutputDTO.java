package controle.facade.dto.output.impl;

import java.util.List;

import controle.facade.dto.output.OutputDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteOutputDTO implements OutputDTO {
    private Integer id;
    private String nome;
    private String dtNascimento;
    private String cpf;
    private String genero;
    private UsuarioOutputDTO usuario;
    private TelefoneOutputDTO telefone;
    private List<EnderecoOutputDTO> enderecos;
    private List<CartaoCreditoOutputDTO> cartoes;
    private List<CupomOutputDTO> cupons;
}
