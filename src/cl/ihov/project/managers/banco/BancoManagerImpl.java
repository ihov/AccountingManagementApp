package cl.ihov.project.managers.banco;

import cl.ihov.project.common.exception.DataException;
import cl.ihov.project.common.vo.Banco;
import cl.ihov.project.model.factory.SessionFactory;
import cl.ihov.project.model.mybatis.mapper.interfaces.AdminClientesMapper;
import java.util.List;

public class BancoManagerImpl implements BancoManager {

    private final AdminClientesMapper adminClientesMapper;

    public BancoManagerImpl() {
        adminClientesMapper = SessionFactory.getSessionFactory().getAdminClientesMapper();
    }
    
    @Override
    public List<Banco> findBancos() throws DataException {
        return (adminClientesMapper.selectBanco());
    }
}
