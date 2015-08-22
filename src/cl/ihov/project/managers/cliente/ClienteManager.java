package cl.ihov.project.managers.cliente;

import cl.ihov.project.common.exception.DataException;
import cl.ihov.project.common.vo.Cliente;
import java.util.List;

public interface ClienteManager {

    public abstract Cliente findCliente(Cliente cliente) throws DataException;

    public abstract List<Cliente> findAllClientes() throws DataException;

    public abstract List<Cliente> findAllClientesActivo() throws DataException;

    public abstract void insertCliente(Cliente cliente) throws DataException;

    public abstract void updateCliente(Cliente cliente) throws DataException;

    public abstract void deleteCliente(Cliente cliente) throws DataException;

}
