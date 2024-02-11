package controle.web.vh.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.web.vh.IViewHelper;
import dominio.EntidadeDominio;
import dominio.Resultado;
import dominio.cliente.Cliente;
import dominio.cliente.Endereco;
import dominio.cliente.Genero;
import dominio.cliente.Telefone;
import dominio.cliente.TipoTelefone;
import dominio.cliente.Usuario;

public class ClienteVH implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		Cliente cliente = null;

		String nmOperacao = request.getParameter("operacao");

		if (nmOperacao != null) {
			if (nmOperacao.equals("Salvar")) {
				UsuarioVH usrVH = new UsuarioVH();
				Usuario usuario = (Usuario) usrVH.getEntidade(request);

				List<Endereco> enderecos = new ArrayList<>();

				EnderecoVH endVH = new EnderecoVH();
				Endereco endereco = (Endereco) endVH.getEntidade(request);

				enderecos.add(endereco);

				int nmTpTelefone = Integer.valueOf(request.getParameter("cbbTpTelefone"));
				String nmDdd = request.getParameter("txtDDD");
				String nmNumTelefone = request.getParameter("txtTelefone");
				TipoTelefone tpTelefone = null;
				if (nmTpTelefone == 1) {
					tpTelefone = TipoTelefone.FIXO;
				} else if (nmTpTelefone == 2) {
					tpTelefone = TipoTelefone.CELULAR;
				}

				Telefone telefone = new Telefone(tpTelefone, nmDdd, nmNumTelefone);

				String nmNome = request.getParameter("txtNome");
				String nmDtNascimento = request.getParameter("txtDtNasc");
				String nmCpf = request.getParameter("txtCPF");

				int nmGenero = Integer.valueOf(request.getParameter("cbbGenero"));
				Genero genero = null;
				if (nmGenero == 1) {
					genero = Genero.MASCULINO;
				} else if (nmGenero == 2) {
					genero = Genero.FEMININO;
				} else if (nmGenero == 3) {
					genero = Genero.NAOBINARIO;
				}

				cliente = Cliente.builder().genero(genero).nome(nmNome).dtNascimento(nmDtNascimento).cpf(nmCpf)
						.telefone(telefone).enderecos(enderecos).usuario(usuario).build();

				return cliente;

			} else if (nmOperacao.equals("Alterar")) {

				cliente = (Cliente) request.getSession().getAttribute("cliente");

				Usuario usr = (Usuario) request.getSession().getAttribute("usuario");
				if (!usr.isAdmin()) {

					int nmTpTelefone = Integer.valueOf(request.getParameter("cbbTpTelefone"));
					String nmDdd = request.getParameter("txtDDD");
					String nmNumTelefone = request.getParameter("txtTelefone");
					TipoTelefone tpTelefone = null;

					if (nmTpTelefone == 1) {
						tpTelefone = TipoTelefone.FIXO;
					} else if (nmTpTelefone == 2) {
						tpTelefone = TipoTelefone.CELULAR;
					}

					Telefone telefone = new Telefone(tpTelefone, nmDdd, nmNumTelefone);

					String nmNome = request.getParameter("txtNome");
					String nmDtNascimento = request.getParameter("txtDtNasc");
					String nmCpf = request.getParameter("txtCPF");

					int nmGenero = Integer.valueOf(request.getParameter("cbbGenero"));
					Genero genero = null;
					if (nmGenero == 1) {
						genero = Genero.MASCULINO;
					} else if (nmGenero == 2) {
						genero = Genero.FEMININO;
					} else if (nmGenero == 3) {
						genero = Genero.NAOBINARIO;
					}

					cliente.setNome(nmNome);
					cliente.setCpf(nmCpf);
					cliente.setDtNascimento(nmDtNascimento);
					cliente.setGenero(genero);
					cliente.setTelefone(telefone);
					cliente.setUsuario(usr);

				}
				return cliente;

			} else if (nmOperacao.equals("ConsultarPorId")) {
				cliente = new Cliente();

				Usuario usr = (Usuario) request.getSession().getAttribute("usuario");

				if (!usr.isAdmin()) {
					cliente.setId(usr.getCliente().getId());
					cliente.setUsuario(usr);

					return cliente;
				}

				String txtIdCliente = request.getParameter("idCliente");
				cliente.setId(Integer.valueOf(txtIdCliente));

			} else if (nmOperacao.equals("Consultar")) {
				cliente = new Cliente();

				Usuario usr = (Usuario) request.getSession().getAttribute("usuario");

				if (!usr.isAdmin()) {
					cliente.setId(usr.getCliente().getId());
					cliente.setUsuario(usr);

					return cliente;
				}

				String txtIdCliente = request.getParameter("idCliente");
				cliente.setId(Integer.valueOf(txtIdCliente));

			}
		} else {
			cliente = new Cliente();
		}

		return cliente;

	}

	@Override
	public void setEntidade(HttpServletResponse response, HttpServletRequest request, Resultado resultado) {

			request.getSession().setAttribute("cliente", (Cliente) resultado.getEntidades().get(0));

	}

}
