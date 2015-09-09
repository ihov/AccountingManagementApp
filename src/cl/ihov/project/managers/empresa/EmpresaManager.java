package cl.ihov.project.managers.empresa;

import cl.ihov.project.common.exception.DataException;
import cl.ihov.project.common.vo.Empresa;
import cl.ihov.project.common.vo.Mes;
import java.util.List;

public interface EmpresaManager {

    public abstract List<Empresa> findEmpresaCliente(Empresa empresa) throws DataException;

    public abstract List<Empresa> findAllEmpresa() throws DataException;

    public abstract Empresa findEmpresa(Empresa empresa) throws DataException;

    public abstract void insertEmpresa(Empresa empresa) throws DataException;

    public abstract void updateEmpresa(Empresa empresa) throws DataException;
    
    public abstract void deleteEmpresa(Empresa empresa) throws DataException;

    public abstract List<Mes> findMeses() throws DataException;
    
    public abstract List<Empresa> findDeudores() throws DataException;
}
