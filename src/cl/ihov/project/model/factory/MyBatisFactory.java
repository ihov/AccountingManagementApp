package cl.ihov.project.model.factory;

import cl.ihov.project.common.utils.BaseResources;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisFactory {

    private static final String RESOURCE;
    private static final String CONNEXION_ENVIROMENT;
    private static final String CONEXION_KEY;
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;
    private static final Map<String, SqlSessionFactory> mapSqlSessionFactory;
    private static final Logger logger;

    static {
        RESOURCE
                = BaseResources.getValue("db_config", "myBatisConfigResource");
        CONNEXION_ENVIROMENT
                = BaseResources.getValue("db_config", "environment");
        CONEXION_KEY
                = BaseResources.getValue("db_config", "connexionKey");
        mapSqlSessionFactory
                = new HashMap<>();
        logger 
                = Logger.getLogger(MyBatisFactory.class.getName());
    }

    private static SqlSessionFactory getSqlSessionFactory(String enviroment) {
        try {
            reader = Resources.getResourceAsReader(RESOURCE);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(
                    reader, enviroment);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return sqlSessionFactory;
    }

    protected static SqlSessionFactory getConnexionSqlSessionFactory() {
        if (!mapSqlSessionFactory.containsKey(CONEXION_KEY)) {
            mapSqlSessionFactory.put(CONEXION_KEY, 
                    getSqlSessionFactory(CONNEXION_ENVIROMENT));
        }
        return mapSqlSessionFactory.get(CONEXION_KEY);
    }
}
