package controle.command.impl;

import controle.command.ICommand;
import controle.facade.IFachada;
import controle.facade.impl.Fachada;

public abstract class AbstractCommand implements ICommand {
    protected IFachada fachada = new Fachada();
    // protected AbstractCommand(IFachada fachada) {
    // this.fachada = fachada;
    // }
}
