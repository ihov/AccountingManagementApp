package cl.ihov.project.model.mybatis.mapper.interfaces;

import cl.ihov.project.common.exception.DataException;
import cl.ihov.project.common.vo.Abono;
import cl.ihov.project.common.vo.Banco;
import cl.ihov.project.common.vo.Cliente;
import cl.ihov.project.common.vo.Contabilidad;
import cl.ihov.project.common.vo.Cuenta;
import cl.ihov.project.common.vo.Deudor;
import cl.ihov.project.common.vo.Empresa;
import cl.ihov.project.common.vo.Fechas;
import cl.ihov.project.common.vo.Mes;
import java.util.List;

public interface AdminClientesMapper {

    public List<Abono> selectAbonosEmpresa(Abono abono) throws DataException;

    public List<Banco> selectBanco() throws DataException;

    public Cliente selectCliente(Cliente cliente) throws DataException;

    public List<Cliente> selectAllCliente() throws DataException;

    public List<Cliente> selectAllClienteActivo() throws DataException;

    public List<Contabilidad> selectContabilidad() throws DataException;

    public List<Cuenta> selectCuenta() throws DataException;

    public List<Empresa> selectEmpresaCliente(Empresa empresa) throws DataException;

    public List<Empresa> selectAllEmpresa() throws DataException;

    public Empresa selectEmpresa(Empresa empresa) throws DataException;

    public void insertCliente(Cliente cliente) throws DataException;

    public void insertEmpresa(Empresa empresa) throws DataException;

    public void insertAbono(Abono abono) throws DataException;

    public void updateCliente(Cliente cliente) throws DataException;

    public void updateEmpresa(Empresa empresa) throws DataException;

    public void updateAbono(Abono abono) throws DataException;

    public void deleteCliente(Cliente cliente) throws DataException;

    public void deleteEmpresa(Empresa empresa) throws DataException;

    public void deleteAbono(Abono abono) throws DataException;
    
    public List<Mes> selectMes() throws DataException;

    public List<Abono> selectAbonosEntreFechas(Fechas fechas) throws DataException;

    public List<Deudor> selectDeudores(Deudor deudor) throws DataException;
    
    public void insertDeudor(Deudor deudor) throws DataException;

    public void deleteDeudorReporte()throws DataException;

}
