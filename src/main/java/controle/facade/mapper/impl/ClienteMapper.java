package controle.facade.mapper.impl;

import java.util.ArrayList;

import controle.facade.dto.input.persistencia.InputPersistenciaDTO;
import controle.facade.dto.input.persistencia.impl.ClientePersistenciaDTO;
import controle.facade.dto.output.OutputDTO;
import controle.facade.dto.output.impl.ClienteOutputDTO;
import controle.facade.dto.output.impl.TelefoneOutputDTO;
import controle.facade.dto.output.impl.UsuarioOutputDTO;
import controle.facade.mapper.IMapper;
import dominio.EntidadeDominio;
import dominio.cliente.Cliente;
import dominio.cliente.Genero;
import dominio.cliente.Telefone;
import dominio.cliente.Usuario;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ClienteMapper implements IMapper {

    private final UsuarioMapper usuarioMapper;
    private final TelefoneMapper telefoneMapper;
    private final EnderecoMapper enderecoMapper;

    @Override
    public EntidadeDominio toEntidade(InputPersistenciaDTO dto) {
        ClientePersistenciaDTO clienteDTO = (ClientePersistenciaDTO) dto;
        Cliente cliente = Cliente.builder()
                .nome(clienteDTO.getNome())
                .dtNascimento(clienteDTO.getDtNascimento())
                .cpf(clienteDTO.getCpf())
                .genero(Genero.obterPorDescricao(clienteDTO.getGenero()))
                .usuario((Usuario) usuarioMapper.toEntidade(clienteDTO.getUsuario()))
                .telefone((Telefone) telefoneMapper.toEntidade(clienteDTO.getTelefone()))
                .build();

        cliente.setEnderecos(new ArrayList<>());

        // if (clienteDTO.getEnderecoResidencial() != null) {
        // cliente.getEnderecos().add((Endereco)
        // enderecoMapper.toEntidade(clienteDTO.getEnderecoResidencial()));
        // }
        // if (clienteDTO.getEnderecoCobranca() != null) {
        // cliente.getEnderecos().add((Endereco)
        // enderecoMapper.toEntidade(clienteDTO.getEnderecoCobranca()));
        // }
        // if (clienteDTO.getEnderecoEntrega() != null) {
        // cliente.getEnderecos().add((Endereco)
        // enderecoMapper.toEntidade(clienteDTO.getEnderecoEntrega()));
        // }

        return cliente;
    }

    @Override
    public OutputDTO toDTO(EntidadeDominio entidade) {
        Cliente cliente = (Cliente) entidade;
        ClienteOutputDTO clienteDTO = new ClienteOutputDTO();
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setDtNascimento(cliente.getDtNascimento());
        clienteDTO.setCpf(cliente.getCpf());
        clienteDTO.setGenero(cliente.getGenero().getDescricao());
        clienteDTO.setUsuario(
                (UsuarioOutputDTO) usuarioMapper.toDTO(cliente.getUsuario()));
        clienteDTO.setTelefone(
                (TelefoneOutputDTO) telefoneMapper.toDTO(cliente.getTelefone()));

        // if (cliente.getEnderecos() != null) {
        // for (Endereco endereco : cliente.getEnderecos()) {
        // EnderecoOutputDTO mappedEndereco = (EnderecoOutputDTO)
        // enderecoMapper.toDTO(endereco);
        // if ("Residencial".equalsIgnoreCase(endereco.getNomeIdentificacao())) {
        // clienteDTO.setEnderecoResidencial(mappedEndereco);
        // } else if ("Cobranca".equalsIgnoreCase(endereco.getNomeIdentificacao())) {
        // clienteDTO.setEnderecoCobranca(mappedEndereco);
        // } else if ("Entrega".equalsIgnoreCase(endereco.getNomeIdentificacao())) {
        // clienteDTO.setEnderecoEntrega(mappedEndereco);
        // }
        // }
        // }

        return clienteDTO;
    }

}
