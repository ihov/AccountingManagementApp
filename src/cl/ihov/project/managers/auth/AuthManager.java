package cl.ihov.project.managers.auth;

import cl.ihov.project.common.exception.DataException;
import cl.ihov.project.common.vo.UserOnline;

public interface AuthManager {

    public abstract boolean auth(UserOnline userOnline) throws DataException;

}
