package cl.ihov.project.managers.contabilidad;

import cl.ihov.project.common.exception.DataException;
import cl.ihov.project.common.vo.Contabilidad;
import cl.ihov.project.model.factory.SessionFactory;
import cl.ihov.project.model.mybatis.mapper.interfaces.AdminClientesMapper;
import java.util.List;

public class ContabilidadManagerImpl implements ContabilidadManager {

    private final AdminClientesMapper adminClientesMapper;

    public ContabilidadManagerImpl() {
        adminClientesMapper = SessionFactory.getSessionFactory().getAdminClientesMapper();
    }

    @Override
    public List<Contabilidad> findContabilidad() throws DataException {
        return (adminClientesMapper.selectContabilidad());
    }

}
