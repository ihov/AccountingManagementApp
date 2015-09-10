package cl.ihov.project.managers.abono;

import cl.ihov.project.common.exception.DataException;
import cl.ihov.project.common.vo.Abono;
import cl.ihov.project.model.factory.SessionFactory;
import cl.ihov.project.model.mybatis.mapper.interfaces.AdminClientesMapper;
import java.util.Date;
import java.util.List;

public class AbonoManagerImpl implements AbonoManager {

    private final AdminClientesMapper adminClientesMapper;

    public AbonoManagerImpl() {
        adminClientesMapper = SessionFactory.getSessionFactory().getAdminClientesMapper();
    }

    @Override
    public List<Abono> findAbonosEmpresa(Abono abono) throws DataException {
        List<Abono> listAbono = adminClientesMapper.selectAbonosEmpresa(abono);
        if (!listAbono.isEmpty()) {
            for (Abono ab : listAbono) {
                ab.setIdAbono(String.valueOf(ab.getIdAbonoInt()));
                ab.setMonto(String.valueOf(ab.getMontoInt()));
            }
        }
        return listAbono;
    }

    @Override
    public void insertAbono(Abono abono) throws DataException {
        abono.setMontoInt(Integer.valueOf(abono.getMonto()));
        adminClientesMapper.insertAbono(abono);
    }

    @Override
    public void updateAbono(Abono abono) throws DataException {
        abono.setIdAbonoInt(Integer.valueOf(abono.getIdAbono()));
        abono.setMontoInt(Integer.valueOf(abono.getMonto()));
        adminClientesMapper.updateAbono(abono);
    }

    @Override
    public void deleteAbono(Abono abono) throws DataException {
        abono.setIdAbonoInt(Integer.valueOf(abono.getIdAbono()));
        adminClientesMapper.deleteAbono(abono);
    }
    @Override
    public List<Abono> findAbonos(Date ini, Date ter) throws DataException {
       List<Abono> listAbono = adminClientesMapper.selectAbonosEntreFechas(ini,ter);
        if (!listAbono.isEmpty()) {
            for (Abono ab : listAbono) {
                ab.setIdAbono(String.valueOf(ab.getIdAbonoInt()));
                ab.setMonto(String.valueOf(ab.getMontoInt()));
            }
        }
        return listAbono;
    }
}
