package cl.ihov.project.model.mybatis.mapper.server;

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
import cl.ihov.project.model.factory.MyBatisFactory;
import cl.ihov.project.model.mybatis.mapper.interfaces.AdminClientesMapper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class ServerAdminClientes extends MyBatisFactory implements AdminClientesMapper {

    @Override
    public List<Abono> selectAbonosEmpresa(Abono abono) throws DataException {
        List<Abono> listaAbonos;
        SqlSessionFactory sqlSessionFactory = MyBatisFactory.getConnexionSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            AdminClientesMapper mapper = session.getMapper(AdminClientesMapper.class);
            listaAbonos = mapper.selectAbonosEmpresa(abono);
        } catch (Exception ex) {
            throw new DataException(ex.getMessage(), ex.getCause());
        } finally {
            session.clearCache();
            session.close();
        }
        return listaAbonos;
    }

    @Override
    public List<Banco> selectBanco() throws DataException {
        List<Banco> listaBancos = new ArrayList<>();
        SqlSessionFactory sqlSessionFactory = MyBatisFactory.getConnexionSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            AdminClientesMapper mapper = session.getMapper(AdminClientesMapper.class);
            listaBancos = mapper.selectBanco();
        } catch (Exception ex) {
            throw new DataException(ex.getMessage(), ex.getCause());
        } finally {
            session.clearCache();
            session.close();
        }
        return listaBancos;
    }

    @Override
    public Cliente selectCliente(Cliente cliente) throws DataException {
        SqlSessionFactory sqlSessionFactory = MyBatisFactory.getConnexionSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            AdminClientesMapper mapper = session.getMapper(AdminClientesMapper.class);
            cliente = mapper.selectCliente(cliente);
        } catch (Exception ex) {
            throw new DataException(ex.getMessage(), ex.getCause());
        } finally {
            session.clearCache();
            session.close();
        }
        return cliente;
    }

    @Override
    public List<Cliente> selectAllCliente() throws DataException {
        List<Cliente> listaClientes = new ArrayList<>();
        SqlSessionFactory sqlSessionFactory = MyBatisFactory.getConnexionSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            AdminClientesMapper mapper = session.getMapper(AdminClientesMapper.class);
            listaClientes = mapper.selectAllCliente();
        } catch (Exception ex) {
            throw new DataException(ex.getMessage(), ex.getCause());
        } finally {
            session.clearCache();
            session.close();
        }
        return listaClientes;
    }

    @Override
    public List<Contabilidad> selectContabilidad() throws DataException {
        List<Contabilidad> listaContabilidad = new ArrayList<>();
        SqlSessionFactory sqlSessionFactory = MyBatisFactory.getConnexionSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            AdminClientesMapper mapper = session.getMapper(AdminClientesMapper.class);
            listaContabilidad = mapper.selectContabilidad();
        } catch (Exception ex) {
            throw new DataException(ex.getMessage(), ex.getCause());
        } finally {
            session.clearCache();
            session.close();
        }
        return listaContabilidad;
    }

    @Override
    public List<Cuenta> selectCuenta() throws DataException {
        List<Cuenta> listaCuenta = new ArrayList<>();
        SqlSessionFactory sqlSessionFactory = MyBatisFactory.getConnexionSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            AdminClientesMapper mapper = session.getMapper(AdminClientesMapper.class);
            listaCuenta = mapper.selectCuenta();
        } catch (Exception ex) {
            throw new DataException(ex.getMessage(), ex.getCause());
        } finally {
            session.clearCache();
            session.close();
        }
        return listaCuenta;
    }

    @Override
    public List<Empresa> selectEmpresaCliente(Empresa empresa) throws DataException {
        List<Empresa> listaEmpresa = new ArrayList<>();
        SqlSessionFactory sqlSessionFactory = MyBatisFactory.getConnexionSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            AdminClientesMapper mapper = session.getMapper(AdminClientesMapper.class);
            listaEmpresa = mapper.selectEmpresaCliente(empresa);
        } catch (Exception ex) {
            throw new DataException(ex.getMessage(), ex.getCause());
        } finally {
            session.clearCache();
            session.close();
        }
        return listaEmpresa;
    }

    @Override
    public List<Empresa> selectAllEmpresa() throws DataException {
        List<Empresa> listaEmpresa = new ArrayList<>();
        SqlSessionFactory sqlSessionFactory = MyBatisFactory.getConnexionSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            AdminClientesMapper mapper = session.getMapper(AdminClientesMapper.class);
            listaEmpresa = mapper.selectAllEmpresa();
        } catch (Exception ex) {
            throw new DataException(ex.getMessage(), ex.getCause());
        } finally {
            session.clearCache();
            session.close();
        }
        return listaEmpresa;
    }

    @Override
    public Empresa selectEmpresa(Empresa empresa) throws DataException {
        SqlSessionFactory sqlSessionFactory = MyBatisFactory.getConnexionSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            AdminClientesMapper mapper = session.getMapper(AdminClientesMapper.class);
            empresa = mapper.selectEmpresa(empresa);
        } catch (Exception ex) {
            throw new DataException(ex.getMessage(), ex.getCause());
        } finally {
            session.clearCache();
            session.close();
        }
        return empresa;
    }

    @Override
    public void insertCliente(Cliente cliente) throws DataException {
        SqlSessionFactory sqlSessionFactory = MyBatisFactory.getConnexionSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            AdminClientesMapper mapper = session.getMapper(AdminClientesMapper.class);
            mapper.insertCliente(cliente);
        } catch (Exception ex) {
            throw new DataException(ex.getMessage(), ex.getCause());
        } finally {
            session.clearCache();
            session.close();
        }
    }

    @Override
    public void insertEmpresa(Empresa empresa) throws DataException {
        SqlSessionFactory sqlSessionFactory = MyBatisFactory.getConnexionSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            AdminClientesMapper mapper = session.getMapper(AdminClientesMapper.class);
            mapper.insertEmpresa(empresa);
        } catch (Exception ex) {
            throw new DataException(ex.getMessage(), ex.getCause());
        } finally {
            session.clearCache();
            session.close();
        }
    }

    @Override
    public void insertAbono(Abono abono) throws DataException {
        SqlSessionFactory sqlSessionFactory = MyBatisFactory.getConnexionSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            AdminClientesMapper mapper = session.getMapper(AdminClientesMapper.class);
            mapper.insertAbono(abono);
        } catch (Exception ex) {
            throw new DataException(ex.getMessage(), ex.getCause());
        } finally {
            session.clearCache();
            session.close();
        }
    }

    @Override
    public void updateCliente(Cliente cliente) throws DataException {
        SqlSessionFactory sqlSessionFactory = MyBatisFactory.getConnexionSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            AdminClientesMapper mapper = session.getMapper(AdminClientesMapper.class);
            mapper.updateCliente(cliente);
        } catch (Exception ex) {
            throw new DataException(ex.getMessage(), ex.getCause());
        } finally {
            session.clearCache();
            session.close();
        }
    }

    @Override
    public void updateEmpresa(Empresa empresa) throws DataException {
        SqlSessionFactory sqlSessionFactory = MyBatisFactory.getConnexionSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            AdminClientesMapper mapper = session.getMapper(AdminClientesMapper.class);
            mapper.updateEmpresa(empresa);
        } catch (Exception ex) {
            throw new DataException(ex.getMessage(), ex.getCause());
        } finally {
            session.clearCache();
            session.close();
        }
    }

    @Override
    public void updateAbono(Abono abono) throws DataException {
        SqlSessionFactory sqlSessionFactory = MyBatisFactory.getConnexionSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            AdminClientesMapper mapper = session.getMapper(AdminClientesMapper.class);
            mapper.updateAbono(abono);
        } catch (Exception ex) {
            throw new DataException(ex.getMessage(), ex.getCause());
        } finally {
            session.clearCache();
            session.close();
        }
    }

    @Override
    public void deleteCliente(Cliente cliente) throws DataException {
        SqlSessionFactory sqlSessionFactory = MyBatisFactory.getConnexionSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            AdminClientesMapper mapper = session.getMapper(AdminClientesMapper.class);
            mapper.deleteCliente(cliente);
        } catch (Exception ex) {
            throw new DataException(ex.getMessage(), ex.getCause());
        } finally {
            session.clearCache();
            session.close();
        }
    }

    @Override
    public void deleteEmpresa(Empresa empresa) throws DataException {
        SqlSessionFactory sqlSessionFactory = MyBatisFactory.getConnexionSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            AdminClientesMapper mapper = session.getMapper(AdminClientesMapper.class);
            mapper.deleteEmpresa(empresa);
        } catch (Exception ex) {
            throw new DataException(ex.getMessage(), ex.getCause());
        } finally {
            session.clearCache();
            session.close();
        }
    }

    @Override
    public void deleteAbono(Abono abono) throws DataException {
        SqlSessionFactory sqlSessionFactory = MyBatisFactory.getConnexionSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            AdminClientesMapper mapper = session.getMapper(AdminClientesMapper.class);
            mapper.deleteAbono(abono);
        } catch (Exception ex) {
            throw new DataException(ex.getMessage(), ex.getCause());
        } finally {
            session.clearCache();
            session.close();
        }
    }

    @Override
    public List<Cliente> selectAllClienteActivo() throws DataException {
        List<Cliente> listaClientes = new ArrayList<>();
        SqlSessionFactory sqlSessionFactory = MyBatisFactory.getConnexionSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            AdminClientesMapper mapper = session.getMapper(AdminClientesMapper.class);
            listaClientes = mapper.selectAllClienteActivo();
        } catch (Exception ex) {
            throw new DataException(ex.getMessage(), ex.getCause());
        } finally {
            session.clearCache();
            session.close();
        }
        return listaClientes;
    }

    @Override
    public List<Mes> selectMes() throws DataException {
        List<Mes> listaMeses = new ArrayList<>();
        SqlSessionFactory sqlSessionFactory = MyBatisFactory.getConnexionSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            AdminClientesMapper mapper = session.getMapper(AdminClientesMapper.class);
            listaMeses = mapper.selectMes();
        } catch (Exception ex) {
            throw new DataException(ex.getMessage(), ex.getCause());
        } finally {
            session.clearCache();
            session.close();
        }
        return listaMeses;
    }

    @Override
    public List<Abono> selectAbonosEntreFechas(Fechas fechas) throws DataException {
        List<Abono> listaAbonos;
        SqlSessionFactory sqlSessionFactory = MyBatisFactory.getConnexionSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            AdminClientesMapper mapper = session.getMapper(AdminClientesMapper.class);
            listaAbonos = mapper.selectAbonosEntreFechas(fechas);
        } catch (Exception ex) {
            throw new DataException(ex.getMessage(), ex.getCause());
        } finally {
            session.clearCache();
            session.close();
        }
        return listaAbonos;
    }
    
    @Override
    public List<Deudor>selectDeudores(Deudor deudor) throws DataException{
        List<Deudor>lista;
        SqlSessionFactory sqlSessionFactory = MyBatisFactory.getConnexionSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            AdminClientesMapper mapper = session.getMapper(AdminClientesMapper.class);
            lista = mapper.selectDeudores(deudor);
        } catch (Exception ex) {
            throw new DataException(ex.getMessage(), ex.getCause());
        } finally {
            session.clearCache();
            session.close();
        }
        return lista;
    }
}
