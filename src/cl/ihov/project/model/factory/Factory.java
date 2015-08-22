package cl.ihov.project.model.factory;

import cl.ihov.project.model.mybatis.mapper.interfaces.AdminClientesMapper;
import cl.ihov.project.model.mybatis.mapper.interfaces.UserOnlineMapper;
import cl.ihov.project.model.mybatis.mapper.server.ServerAdminClientes;
import cl.ihov.project.model.mybatis.mapper.server.ServerUserOnline;

public class Factory extends SessionFactory {

    @Override
    public UserOnlineMapper getUserOnlineMapper() {
        return new ServerUserOnline();
    }

    @Override
    public AdminClientesMapper getAdminClientesMapper() {
        return new ServerAdminClientes();
    }
}
