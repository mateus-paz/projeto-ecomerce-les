package controle.web.vh.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.web.vh.IViewHelper;
import dominio.EntidadeDominio;
import dominio.venda.Carrinho;
import dominio.venda.ItemCarrinho;
import dominio.venda.ItemEstoque;

public class ItemCarrinhoVH implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		ItemCarrinho item = null;

		CarrinhoVH cartVh = new CarrinhoVH();
		Carrinho carrinho = (Carrinho) cartVh.getEntidade(request);

		String operacao = request.getParameter("operacao");

		if (operacao.equals("Adicionar")) {

			ItemEstoque itEstoque = (ItemEstoque) request.getSession().getAttribute("item");

			int qnt = 1;
			String txtQnt = request.getParameter("txtQnt");
			if (!txtQnt.equals("") && !txtQnt.equals("0"))
				qnt = Integer.valueOf(txtQnt);

			item = new ItemCarrinho(carrinho);

			item.setLivro(itEstoque.getLivro());
			item.setQuantidade(qnt);

			item.setValorVenda(itEstoque.getValorVenda());

			if (carrinho.getItens().contains(item))
				for (ItemCarrinho it : carrinho.getItens())
					if (it.getLivro().getId() == item.getLivro().getId()) {
						qnt += it.getQuantidade();
						item.setQuantidade(qnt);
						item.setId(it.getId());
						break;
					}

		} else if (operacao.equals("Editar")) {

			int qnt = 1;
			int idItem = Integer.valueOf(request.getParameter("idItem"));

			String txtQnt = request.getParameter("txtQnt");

			if (!txtQnt.equals("") && !txtQnt.equals("0"))
				qnt = Integer.valueOf(txtQnt);

			for (ItemCarrinho it : carrinho.getItens())
				if (it.getId() == idItem) {
					item = new ItemCarrinho();

					item.setLivro(it.getLivro());
					item.setQuantidade(qnt);
					item.setId(idItem);

					break;
				}

			item.setCarrinho(carrinho);

		} else if (operacao.equals("Remover")) {
			int idItem = Integer.valueOf(request.getParameter("idItem"));

			for (ItemCarrinho it : carrinho.getItens())
				if (it.getId() == idItem) {
					item = it;
					break;
				}
		}

		return item;
	}

	@Override
	public void setEntidade(HttpServletResponse response, HttpServletRequest request, Object msg) {
		// TODO Auto-generated method stub

	}

}
