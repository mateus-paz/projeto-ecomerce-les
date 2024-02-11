package controle.web.vh;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.EntidadeDominio;
import dominio.Resultado;

public interface IViewHelper {
	public EntidadeDominio getEntidade(HttpServletRequest request);

	public void setEntidade(HttpServletResponse response, HttpServletRequest request, Resultado resultado);
}
