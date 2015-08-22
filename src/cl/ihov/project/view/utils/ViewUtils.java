package cl.ihov.project.view.utils;

import javafx.fxml.FXMLLoader;

public class ViewUtils {

    public static FXMLLoader getFXMLLoader(String FXMLView) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(
                ViewUtils.class.getResource(
                        "/cl/ihov/project/view/resources/faces/" + FXMLView + ".fxml"));
        return loader;
    }

    public static String getCSS(String cssView) {
        return ViewUtils.class.getClassLoader().getResource(
                "cl/ihov/project/view/resources/css/" + cssView + ".css").toExternalForm();
    }
}
