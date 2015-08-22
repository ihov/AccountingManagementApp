package cl.ihov.project.managers.auth;

import cl.ihov.project.common.exception.DataException;
import cl.ihov.project.common.vo.UserOnline;
import cl.ihov.project.model.factory.SessionFactory;
import cl.ihov.project.model.mybatis.mapper.interfaces.UserOnlineMapper;

public class AuthManagerImpl implements AuthManager {

    private final UserOnlineMapper userOnlineMapper;

    public AuthManagerImpl() {
        userOnlineMapper = SessionFactory.getSessionFactory().getUserOnlineMapper();
    }

    @Override
    public boolean auth(UserOnline userOnline) throws DataException {
        return (userOnlineMapper.selectOneUserOnline(userOnline) instanceof UserOnline);
    }
}
