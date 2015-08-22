package cl.ihov.project.managers.banco;

import cl.ihov.project.common.exception.DataException;
import cl.ihov.project.common.vo.Banco;
import java.util.List;

public interface BancoManager {

    public abstract List<Banco> findBancos() throws DataException;

}
