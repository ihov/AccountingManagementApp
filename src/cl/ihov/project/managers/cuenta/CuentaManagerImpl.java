package cl.ihov.project.managers.cuenta;

import cl.ihov.project.common.exception.DataException;
import cl.ihov.project.common.vo.Cuenta;
import cl.ihov.project.model.factory.SessionFactory;
import cl.ihov.project.model.mybatis.mapper.interfaces.AdminClientesMapper;
import java.util.List;

public class CuentaManagerImpl implements CuentaManager {

    private final AdminClientesMapper adminClientesMapper;

    public CuentaManagerImpl() {
        adminClientesMapper = SessionFactory.getSessionFactory().getAdminClientesMapper();
    }

    @Override
    public List<Cuenta> findCuentas() throws DataException {
        return (adminClientesMapper.selectCuenta());
    }
}
