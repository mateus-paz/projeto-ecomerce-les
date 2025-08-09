package controle.facade.dto.input.persistencia.impl;

import controle.facade.dto.input.persistencia.InputPersistenciaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import dominio.EntidadeDominio;
import dominio.cliente.Cliente;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientePersistenciaDTO implements InputPersistenciaDTO {
    private Integer id;
    private String nome;
    private String dtNascimento;
    private String cpf;
    private String genero; // Representar enum como String
    private UsuarioPersistenciaDTO usuario;
    private TelefonePersistenciaDTO telefone;
    private List<EnderecoPersistenciaDTO> enderecos;
    private List<CartaoCreditoPersistenciaDTO> cartoes;
    private List<CupomPersistenciaDTO> cupons;

    @Override
    public Class<? extends EntidadeDominio> getDominio() {
        return Cliente.class;
    }
}