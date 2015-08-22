package cl.ihov.project.managers.contabilidad;

import cl.ihov.project.common.exception.DataException;
import cl.ihov.project.common.vo.Contabilidad;
import java.util.List;

public interface ContabilidadManager {
    
    public abstract List<Contabilidad> findContabilidad() throws DataException;
    
}
