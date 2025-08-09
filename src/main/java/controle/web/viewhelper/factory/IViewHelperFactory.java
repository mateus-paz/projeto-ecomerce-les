package controle.web.viewhelper.factory;

import controle.web.viewhelper.IViewHelper;

public interface IViewHelperFactory {
    IViewHelper create(String dominio);
}
