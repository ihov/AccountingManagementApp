package cl.ihov.project.model.factory;

import cl.ihov.project.model.mybatis.mapper.interfaces.AdminClientesMapper;
import cl.ihov.project.model.mybatis.mapper.interfaces.UserOnlineMapper;

public abstract class SessionFactory {

    public static SessionFactory getSessionFactory() {
        return new Factory();
    }

    public abstract UserOnlineMapper getUserOnlineMapper();

    public abstract AdminClientesMapper getAdminClientesMapper();
}
