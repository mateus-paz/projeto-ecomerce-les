package controle.web.viewhelper.factory.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import controle.web.viewhelper.IViewHelper;
import controle.web.viewhelper.factory.IViewHelperFactory;
import controle.web.viewhelper.persistencia.ClientePersistenciaVH;
import controle.web.viewhelper.persistencia.EnderecoPersistenciaVH;
import controle.web.viewhelper.persistencia.TelefonePersistenciaVH;
import controle.web.viewhelper.persistencia.UsuarioPersistenciaVH;

public class PersistanceViewHelperFactory implements IViewHelperFactory {
    private static Map<String, IViewHelper> viewHelpers = new HashMap<>();

    static {
        UsuarioPersistenciaVH usuarioVH = new UsuarioPersistenciaVH();
        TelefonePersistenciaVH telefoneVH = new TelefonePersistenciaVH();
        EnderecoPersistenciaVH enderecoVH = new EnderecoPersistenciaVH();
        ClientePersistenciaVH clienteVH = new ClientePersistenciaVH(usuarioVH, telefoneVH, enderecoVH);
        viewHelpers.put("usuario", usuarioVH);
        viewHelpers.put("telefone", telefoneVH);
        viewHelpers.put("endereco", enderecoVH);
        viewHelpers.put("cliente", clienteVH);
    }

    @Override
    public IViewHelper create(String dominio) {
        return Optional.ofNullable(viewHelpers.get(dominio))
                .orElseThrow(() -> new IllegalArgumentException("ViewHelper not found for domain: " + dominio));
    }
}
