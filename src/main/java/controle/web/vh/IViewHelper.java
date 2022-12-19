package controle.web.vh;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dominio.EntidadeDominio;

public interface IViewHelper {
	public EntidadeDominio getEntidade(HttpServletRequest request);
	
	public void setEntidade(HttpServletResponse response, 
			HttpServletRequest request, Object msg);
}
