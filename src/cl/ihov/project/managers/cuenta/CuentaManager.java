package cl.ihov.project.managers.cuenta;

import cl.ihov.project.common.exception.DataException;
import cl.ihov.project.common.vo.Cuenta;
import java.util.List;

public interface CuentaManager {
    
    public abstract List<Cuenta> findCuentas() throws DataException;
    
}
