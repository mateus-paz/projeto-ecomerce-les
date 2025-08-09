package strategy.impl;

import dominio.EntidadeDominio;
import dominio.cliente.Cliente;
import dominio.cliente.Endereco;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ValidarDadosCliente extends AbstractValidador {
    private ValidarDadosTelefone vTelefone;
    private ValidarDadosEndereco vEndereco;
    private ValidarDadosUsuario vUsuario;
    private ValidarUnicidadeCliente vUnicidade;

    public ValidarDadosCliente() {
        this.vTelefone = new ValidarDadosTelefone();
        this.vEndereco = new ValidarDadosEndereco();
        this.vUsuario = new ValidarDadosUsuario();
        this.vUnicidade = new ValidarUnicidadeCliente();
    }

    @Override
    public String processar(EntidadeDominio entidade) {
        Cliente cliente = (Cliente) entidade;

        if (isBlank(cliente.getNome())) {
            sb.append("E obrigatorio o cadastro do Nome; ");
        }

        if (isBlank(cliente.getCpf())) {
            sb.append("E obrigatorio o cadastro do CPF; ");
        } else if (cliente.getCpf().length() != 14) {
            sb.append("O Tamanho do CPF deve corresponder a 11 digitos (Considerando apenas numeros); ");
        }

        if (isBlank(cliente.getDtNascimento())) {
            sb.append("E obrigatorio o cadastro da Data de Nascimento; ");
        }

        if (isBlank(cliente.getGenero())) {
            sb.append("E obrigatorio a selecao do Genero; ");
        }

        String msgTelefone = null;
        msgTelefone = vTelefone.processar(cliente.getTelefone());

        if (msgTelefone != null) {
            sb.append(msgTelefone);
        }

        if (cliente.getId() == 0) {
            String msgEndereco = null;
            for (Endereco endereco : cliente.getEnderecos()) {
                msgEndereco = vEndereco.processar(endereco);
                if (msgEndereco != null) {
                    sb.append(msgEndereco);
                }
            }

            String msgUsuario = null;
            msgUsuario = vUsuario.processar(cliente.getUsuario());

            if (msgUsuario != null) {
                sb.append(msgUsuario);
            }
        }

        String msgUnicidade = null;
        msgUnicidade = vUnicidade.processar(cliente);

        if (msgUnicidade != null) {
            sb.append(msgUnicidade);
        }

        if (sb.length() > 0) {
            return sb.toString();
        }

        return null;
    }

}
