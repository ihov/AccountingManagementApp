package cl.ihov.project.managers.empresa;

import cl.ihov.project.common.exception.DataException;
import cl.ihov.project.common.vo.Empresa;
import cl.ihov.project.common.vo.Mes;
import cl.ihov.project.model.factory.SessionFactory;
import cl.ihov.project.model.mybatis.mapper.interfaces.AdminClientesMapper;
import java.util.List;

public class EmpresaManagerImpl implements EmpresaManager {

    private final AdminClientesMapper adminClientesMapper;

    public EmpresaManagerImpl() {
        adminClientesMapper = SessionFactory.getSessionFactory().getAdminClientesMapper();
    }

    @Override
    public List<Empresa> findEmpresaCliente(Empresa empresa) throws DataException {
        List<Empresa> listaEmpresa = adminClientesMapper.selectEmpresaCliente(empresa);
        if (!listaEmpresa.isEmpty()) {
            for (Empresa lista : listaEmpresa) {
                lista.setIdBanco(String.valueOf(lista.getIdBancoInt()));
                lista.setIdContabilidad(String.valueOf(lista.getIdContabilidadInt()));
                lista.setIdCuenta(String.valueOf(lista.getIdCuentaInt()));
                lista.setValorMensual(String.valueOf(lista.getValorMensualInt()));
            }
        }
        return listaEmpresa;
    }

    @Override
    public List<Empresa> findAllEmpresa() throws DataException {
        List<Empresa> listaEmpresa = adminClientesMapper.selectAllEmpresa();
        if (!listaEmpresa.isEmpty()) {
            for (Empresa lista : listaEmpresa) {
                lista.setIdBanco(String.valueOf(lista.getIdBancoInt()));
                lista.setIdContabilidad(String.valueOf(lista.getIdContabilidadInt()));
                lista.setIdCuenta(String.valueOf(lista.getIdCuentaInt()));
                lista.setValorMensual(String.valueOf(lista.getValorMensualInt()));
                if (lista.getActivo().equals("true")) {
                    lista.setActivo("Si");
                } else {
                    lista.setActivo("No");
                }
            }
        }
        return listaEmpresa;
    }

    @Override
    public Empresa findEmpresa(Empresa empresa) throws DataException {
        Empresa emp = adminClientesMapper.selectEmpresa(empresa);
        if (emp != null) {
            emp.setIdBanco(String.valueOf(emp.getIdBancoInt()));
            emp.setIdContabilidad(String.valueOf(emp.getIdContabilidadInt()));
            emp.setIdCuenta(String.valueOf(emp.getIdCuentaInt()));
            emp.setValorMensual(String.valueOf(emp.getValorMensualInt()));
        }

        return emp;
    }

    @Override
    public void insertEmpresa(Empresa empresa) throws DataException {
        empresa.setIdContabilidadInt(Integer.valueOf(empresa.getIdContabilidad()));
        empresa.setIdBancoInt(Integer.valueOf(empresa.getIdBanco()));
        empresa.setIdCuentaInt(Integer.valueOf(empresa.getIdCuenta()));
        empresa.setValorMensualInt(Integer.valueOf(empresa.getValorMensual()));
        adminClientesMapper.insertEmpresa(empresa);
    }

    @Override
    public void updateEmpresa(Empresa empresa) throws DataException {
        empresa.setIdContabilidadInt(Integer.valueOf(empresa.getIdContabilidad()));
        empresa.setIdBancoInt(Integer.valueOf(empresa.getIdBanco()));
        empresa.setIdCuentaInt(Integer.valueOf(empresa.getIdCuenta()));
        empresa.setValorMensualInt(Integer.valueOf(empresa.getValorMensual()));
        adminClientesMapper.updateEmpresa(empresa);
    }

    @Override
    public void deleteEmpresa(Empresa empresa) throws DataException {
        adminClientesMapper.deleteEmpresa(empresa);
    }

    @Override
    public List<Mes> findMeses() throws DataException {
        return (adminClientesMapper.selectMes());
    }

    @Override
    public List<Empresa> findDeudores() throws DataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
