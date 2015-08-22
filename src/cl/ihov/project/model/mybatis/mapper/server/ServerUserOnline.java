package cl.ihov.project.model.mybatis.mapper.server;

import cl.ihov.project.common.exception.DataException;
import cl.ihov.project.common.vo.UserOnline;
import cl.ihov.project.model.factory.MyBatisFactory;
import cl.ihov.project.model.mybatis.mapper.interfaces.UserOnlineMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class ServerUserOnline extends MyBatisFactory implements UserOnlineMapper {

    @Override
    public UserOnline selectOneUserOnline(UserOnline userOnline) throws DataException {
        UserOnline userOnlineResturn;
        SqlSessionFactory sqlSessionFactory = MyBatisFactory.getConnexionSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            UserOnlineMapper mapper = session.getMapper(UserOnlineMapper.class);
            userOnlineResturn = mapper.selectOneUserOnline(userOnline);
        } catch (Exception ex) {
            throw new DataException(ex.getMessage(), ex.getCause());
        } finally {
            session.clearCache();
            session.close();
        }
        return userOnlineResturn;
    }
}
