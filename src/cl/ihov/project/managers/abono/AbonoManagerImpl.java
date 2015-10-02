package cl.ihov.project.managers.abono;

import cl.ihov.project.common.exception.DataException;
import cl.ihov.project.common.vo.Abono;
import cl.ihov.project.common.vo.Deudor;
import cl.ihov.project.common.vo.Fechas;
import cl.ihov.project.model.factory.SessionFactory;
import cl.ihov.project.model.mybatis.mapper.interfaces.AdminClientesMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public List<Abono> findAbonos(Fechas fechas) throws DataException {
        List<Abono> listAbono = adminClientesMapper.selectAbonosEntreFechas(fechas);
        if (!listAbono.isEmpty()) {
            for (Abono ab : listAbono) {
                ab.setIdAbono(String.valueOf(ab.getIdAbonoInt()));
                ab.setMonto(String.valueOf(ab.getMontoInt()));
            }
        }
        return listAbono;
    }

    @Override
    public List<Deudor> findDeudores(Deudor deudor) throws DataException {
        List<Deudor> lista = adminClientesMapper.selectDeudores(deudor);
        List<Deudor> listaDeudores = new ArrayList<>();

        if (lista != null && !lista.isEmpty()) {
            Deudor di = null, dj = null, d;
            long suma = 0;
            for (int i = 0; i < lista.size(); i++) {
                di = (Deudor) lista.get(i);
                suma += Long.valueOf(di.getMontoAbono());
                for (int j = i + 1; j < lista.size(); j++) {
                    dj = (Deudor) lista.get(j);
                    if (di.getRutEmpresa().equals(dj.getRutEmpresa())) {
                        suma += Long.valueOf(dj.getMontoAbono());
                        if ((j + 1) == lista.size()) {
                            long montoDebe = di.getValorMensualInt() - suma;
                            if (montoDebe > 0) {
                                d = new Deudor();
                                d.setGiroComercial(di.getGiroComercial());
                                d.setRazonSocial(di.getRazonSocial());
                                d.setMontoDebe(String.valueOf(montoDebe));
                                d.setMontoAbono(String.valueOf(suma));
                                d.setRutEmpresa(di.getRutEmpresa());
                                d.setValorMensual(String.valueOf(di.getValorMensualInt()));
                                d.setRutCliente(di.getRutCliente());
                                listaDeudores.add(d);
                            }
                            suma = 0;
                            i = j;
                            break;
                        }
                    } else {
                        long montoDebe = di.getValorMensualInt() - suma;
                        if (montoDebe > 0) {
                            d = new Deudor();
                            d.setGiroComercial(di.getGiroComercial());
                            d.setRazonSocial(di.getRazonSocial());
                            d.setMontoDebe(String.valueOf(montoDebe));
                            d.setMontoAbono(String.valueOf(suma));
                            d.setRutEmpresa(di.getRutEmpresa());
                            d.setValorMensual(String.valueOf(di.getValorMensualInt()));
                            d.setRutCliente(di.getRutCliente());
                            listaDeudores.add(d);
                        }
                        suma = 0;
                        i = j - 1;
                        break;
                    }
                }
            }
        }

        return listaDeudores;
    }

    @Override
    public void insertDeudores(List<Deudor> listaDeudores) {
        try {
            for (Deudor deudor : listaDeudores) {
                deudor.setTotalAbono(Integer.valueOf(deudor.getMontoAbono()));
                deudor.setMontoDebeInt(Integer.valueOf(deudor.getMontoDebe()));
                deudor.setIntMes(Integer.valueOf(deudor.getMes()));
                deudor.setIntAnno(Integer.valueOf(deudor.getAnno()));
                adminClientesMapper.insertDeudor(deudor);
            }
        } catch (DataException ex) {
            Logger.getLogger(AbonoManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
