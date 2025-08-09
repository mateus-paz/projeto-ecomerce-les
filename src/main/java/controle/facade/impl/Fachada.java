package controle.facade.impl;

import java.util.List;

import controle.facade.IFachada;
import controle.facade.dto.input.InputDTO;
import controle.facade.dto.input.exclusao.InputExclusaoDTO;
import controle.facade.dto.input.persistencia.InputPersistenciaDTO;
import controle.facade.dto.output.OutputDTO;
import controle.facade.mapper.IMapper;
import controle.facade.mapper.factory.MapperFactory;
import dao.IDAO;
import dao.factory.DaoFactory;
import dominio.EntidadeDominio;
import strategy.IStrategy;
import strategy.factory.RegrasNegocioFactory;

public class Fachada implements IFachada {

    @Override
    public void salvar(InputDTO input) {
        if (!(input instanceof InputPersistenciaDTO))
            throw new RuntimeException("InputDTO must be an instance of InputPersistenciaDTO");
        InputPersistenciaDTO inputPersistencia = (InputPersistenciaDTO) input;
        String nomeEntidadeDominio = inputPersistencia.getDominio().getName();
        IMapper mapper = MapperFactory.obterMapper(nomeEntidadeDominio);
        EntidadeDominio entidade = mapper.toEntidade(inputPersistencia);
        StringBuilder sb = new StringBuilder();
        List<IStrategy> regras = RegrasNegocioFactory.obterRegrasNegocio(nomeEntidadeDominio);
        for (IStrategy s : regras) {
            String msg = s.processar(entidade);
            if (msg != null) {
                sb.append(msg);
                sb.append("\n");
            }
        }
        if (sb.length() != 0)
            throw new RuntimeException(sb.toString());
        IDAO dao = DaoFactory.obterDao(nomeEntidadeDominio);
        dao.salvar(entidade);
    }

    @Override
    public void alterar(InputDTO input) {
        if (!(input instanceof InputPersistenciaDTO))
            throw new RuntimeException("InputDTO must be an instance of InputPersistenciaDTO");
        InputPersistenciaDTO inputPersistencia = (InputPersistenciaDTO) input;
        String nomeEntidadeDominio = inputPersistencia.getDominio().getName();
        IMapper mapper = MapperFactory.obterMapper(nomeEntidadeDominio);
        EntidadeDominio entidade = mapper.toEntidade(inputPersistencia);
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
            throw new RuntimeException(sb.toString());
        IDAO dao = DaoFactory.obterDao(nmEntidade);
        dao.alterar(entidade);
    }

    @Override
    public void excluir(InputDTO input) {
        if (!(input instanceof InputExclusaoDTO))
            throw new RuntimeException("InputDTO must be an instance of InputExclusaoDTO");
        InputExclusaoDTO inputExclusaoDTO = (InputExclusaoDTO) input;
        String nomeEntidadeDominio = inputExclusaoDTO.getDominio().getName();
        IDAO dao = DaoFactory.obterDao(nomeEntidadeDominio);
        dao.excluirPorId(inputExclusaoDTO.getId());
    }

    @Override
    public List<OutputDTO> consultar(InputDTO input) {
        String nmEntidade = entidade.getClass().getName();
        IDAO dao = DaoFactory.obterDao(nmEntidade);
        return dao.consultar(entidade);
    }

    @Override
    public OutputDTO consultarPorId(InputDTO input) {
        String nmEntidade = entidade.getClass().getName();
        IDAO dao = DaoFactory.obterDao(nmEntidade);
        return dao.consultarPorId(entidade);
    }
}
