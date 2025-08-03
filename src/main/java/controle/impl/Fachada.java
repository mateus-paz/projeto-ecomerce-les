package controle.impl;

import java.util.List;

import controle.IFachada;
import dao.IDAO;
import dao.factory.DaoFactory;
import dominio.EntidadeDominio;
import strategy.IStrategy;
import strategy.factory.RegrasNegocioFactory;

public class Fachada implements IFachada {

	@Override
	public String salvar(EntidadeDominio entidade) {
		StringBuilder sb = new StringBuilder();
		String nmEntidade = entidade.getClass().getName();
		List<IStrategy> regras = RegrasNegocioFactory.obterRegrasNegocio(nmEntidade);

		for (IStrategy s : regras) {
			String msg = s.processar(entidade);
			if (msg != null) {
				sb.append(msg);
				sb.append("\n");
			}
		}

		if (sb.length() != 0)
			return sb.toString();

		IDAO dao = DaoFactory.obterDao(nmEntidade);
		dao.salvar(entidade);
		return null;
	}

	@Override
	public String alterar(EntidadeDominio entidade) {
		StringBuilder sb = new StringBuilder();
		String nmEntidade = entidade.getClass().getName();
		List<IStrategy> regras = RegrasNegocioFactory.obterRegrasNegocio(nmEntidade);

		for (IStrategy s : regras) {
			String msg = s.processar(entidade);
			if (msg != null) {
				sb.append(msg);
				sb.append("\n");
			}
		}

		if (sb.length() != 0)
			return sb.toString();

		IDAO dao = DaoFactory.obterDao(nmEntidade);
		dao.alterar(entidade);
		return null;
	}

	@Override
	public String excluir(EntidadeDominio entidade) {
		String nmEntidade = entidade.getClass().getName();
		IDAO dao = DaoFactory.obterDao(nmEntidade);
		dao.excluir(entidade);
		return null;
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		String nmEntidade = entidade.getClass().getName();
		IDAO dao = DaoFactory.obterDao(nmEntidade);
		return dao.consultar(entidade);
	}

	@Override
	public EntidadeDominio consultarPorId(EntidadeDominio entidade) {
		String nmEntidade = entidade.getClass().getName();
		IDAO dao = DaoFactory.obterDao(nmEntidade);
		return dao.consultarPorId(entidade);
	}
}
