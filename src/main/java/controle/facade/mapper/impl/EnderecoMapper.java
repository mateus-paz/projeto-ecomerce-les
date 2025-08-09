package controle.facade.mapper.impl;

import controle.facade.dto.input.persistencia.InputPersistenciaDTO;
import controle.facade.dto.input.persistencia.impl.EnderecoPersistenciaDTO;
import controle.facade.dto.output.OutputDTO;
import controle.facade.dto.output.impl.EnderecoOutputDTO;
import controle.facade.mapper.IMapper;
import dominio.EntidadeDominio;
import dominio.cliente.Cidade;
import dominio.cliente.Endereco;
import dominio.cliente.Estado;
import dominio.cliente.Pais;
import dominio.cliente.TipoLogradouro;
import dominio.cliente.TipoResidencia;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EnderecoMapper implements IMapper {

    @Override
    public EntidadeDominio toEntidade(InputPersistenciaDTO dto) {
        EnderecoPersistenciaDTO enderecoDTO = (EnderecoPersistenciaDTO) dto;

        Pais pais = Pais.builder().nome(enderecoDTO.getCidade().getEstado().getPais().getNome()).build();
        Estado estado = Estado.builder().nome(enderecoDTO.getCidade().getEstado().getNome()).pais(pais).build();
        Cidade cidade = Cidade.builder().nome(enderecoDTO.getCidade().getNome()).estado(estado).build();

        return Endereco.builder()
                .logradouro(enderecoDTO.getLogradouro())
                .numero(enderecoDTO.getNumero())
                .bairro(enderecoDTO.getBairro())
                .cep(enderecoDTO.getCep())
                .complemento(enderecoDTO.getComplemento())
                .nomeIdentificacao(enderecoDTO.getNomeIdentificacao())
                .tpResidencia(TipoResidencia.obterPorDescricao(enderecoDTO.getTpResidencia()))
                .tpLogradouro(TipoLogradouro.obterPorDescricao(enderecoDTO.getTpLogradouro()))
                .cidade(cidade)
                .build();
    }

    @Override
    public OutputDTO toDTO(EntidadeDominio entidade) {
        Endereco endereco = (Endereco) entidade;
        EnderecoOutputDTO enderecoDTO = new EnderecoOutputDTO();
        enderecoDTO.setLogradouro(endereco.getLogradouro());
        enderecoDTO.setNumero(endereco.getNumero());
        enderecoDTO.setBairro(endereco.getBairro());
        enderecoDTO.setCep(endereco.getCep());
        enderecoDTO.setComplemento(endereco.getComplemento());
        enderecoDTO.setNomeIdentificacao(endereco.getNomeIdentificacao());
        enderecoDTO.setTpResidencia(endereco.getTpResidencia().getDescricao());
        enderecoDTO.setTpLogradouro(endereco.getTpLogradouro().getDescricao());
        // enderecoDTO.setNomeCidade(endereco.getCidade().getNome());
        // enderecoDTO.setNomeEstado(endereco.getCidade().getEstado().getNome());
        // enderecoDTO.setNomePais(endereco.getCidade().getEstado().getPais().getNome());
        return enderecoDTO;
    }

}
