package cl.ihov.project.common.utils;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class BaseResources {

    public static String getValue(String path, String key) {
        return PropertyResourceBundle.getBundle(BaseResources.getPropertiesPath(path)).getString(key);
    }

    public static String getPropertiesPath(String key) {
        return PropertyResourceBundle.getBundle("cl.ihov.project.common.properties.path").getString(key);
    }

    public static ResourceBundle getPageList(String path) {
        return PropertyResourceBundle.getBundle(BaseResources.getPropertiesPath(path));
    }
}
