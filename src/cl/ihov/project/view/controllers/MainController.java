package cl.ihov.project.view.controllers;

import cl.ihov.project.common.initial.MainProject;
import cl.ihov.project.view.components.MainViewComponent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class MainController extends MainViewComponent implements Initializable {

    private MainProject mainProject;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setMainProject(MainProject mainProject) {
        this.mainProject = mainProject;
    }


}
