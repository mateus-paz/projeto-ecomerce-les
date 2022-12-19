package controle.web;

import controle.IFachada;
import controle.impl.Fachada;

public abstract class AbstractCommand implements ICommand{
	protected IFachada fachada = new Fachada();
}
