package cl.ihov.project.model.mybatis.mapper.interfaces;

import cl.ihov.project.common.exception.DataException;
import cl.ihov.project.common.vo.UserOnline;

public interface UserOnlineMapper {

    public UserOnline selectOneUserOnline(UserOnline userOnline) throws DataException;
}
