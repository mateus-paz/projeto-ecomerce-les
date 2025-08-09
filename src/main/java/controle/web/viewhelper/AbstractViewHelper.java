package controle.web.viewhelper;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractViewHelper implements IViewHelper {

    protected String getParameter(HttpServletRequest request, String paramName) {
        String paramValue = request.getParameter(paramName);
        return (paramValue != null && !paramValue.isEmpty()) ? paramValue : null;
    }

    protected Long getId(HttpServletRequest request) {
        String[] partes = request.getRequestURI().split("/");
        if (partes.length < 3) {
            return null;
        }
        if (!partes[2].matches("\\d+")) {
            return null;
        }
        return Long.valueOf(partes[2]);
    }
}
