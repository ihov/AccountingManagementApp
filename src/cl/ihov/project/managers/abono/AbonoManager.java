package cl.ihov.project.managers.abono;

import cl.ihov.project.common.exception.DataException;
import cl.ihov.project.common.vo.Abono;
import cl.ihov.project.common.vo.Fechas;
import java.util.List;

public interface AbonoManager {

    public abstract List<Abono> findAbonosEmpresa(Abono abono) throws DataException;

    public abstract void insertAbono(Abono abono) throws DataException;

    public abstract void updateAbono(Abono abono) throws DataException;

    public abstract void deleteAbono(Abono abono) throws DataException;
    
    public abstract List<Abono> findAbonos(Fechas fecha) throws DataException;
}
