package dao;

import java.util.List;

import dominio.EntidadeDominio;

public interface IDAO {
    public void salvar(EntidadeDominio entidade);

    public void alterar(EntidadeDominio entidade);

    public void excluirPorId(Integer id);

    public List<EntidadeDominio> consultar(EntidadeDominio entidade);

    public EntidadeDominio consultarPorId(EntidadeDominio entidade);
}
