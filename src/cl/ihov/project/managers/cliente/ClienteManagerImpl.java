package cl.ihov.project.managers.cliente;

import cl.ihov.project.common.exception.DataException;
import cl.ihov.project.common.vo.Cliente;
import cl.ihov.project.model.factory.SessionFactory;
import cl.ihov.project.model.mybatis.mapper.interfaces.AdminClientesMapper;
import java.util.List;

public class ClienteManagerImpl implements ClienteManager {

    private final AdminClientesMapper adminClientesMapper;

    public ClienteManagerImpl() {
        adminClientesMapper = SessionFactory.getSessionFactory().getAdminClientesMapper();
    }

    @Override
    public Cliente findCliente(Cliente cliente) throws DataException {
        return (adminClientesMapper.selectCliente(cliente));
    }

    @Override
    public void insertCliente(Cliente cliente) throws DataException {
        adminClientesMapper.insertCliente(cliente);
    }

    @Override
    public void updateCliente(Cliente cliente) throws DataException {
        adminClientesMapper.updateCliente(cliente);
    }

    @Override
    public void deleteCliente(Cliente cliente) throws DataException {
        adminClientesMapper.deleteCliente(cliente);
    }

    @Override
    public List<Cliente> findAllClientes() throws DataException {
        return (adminClientesMapper.selectAllCliente());
    }
    
    @Override
    public List<Cliente> findAllClientesActivo() throws DataException {
        return (adminClientesMapper.selectAllClienteActivo());
    }

}
