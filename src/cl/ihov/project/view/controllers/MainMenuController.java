package cl.ihov.project.view.controllers;

import cl.ihov.project.common.initial.MainProject;
import cl.ihov.project.view.components.MainMenuViewComponent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class MainMenuController extends MainMenuViewComponent implements Initializable {

    private MainProject mainProject;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setMainProject(MainProject mainProject) {
        this.mainProject = mainProject;
    }
    
    @FXML
    private void handleClientes(ActionEvent event) {
        mainProject.showClientes();
    }
    
    @FXML
    private void handleEmpresas(ActionEvent event) {
        mainProject.showEmpresas();
    }
    
    @FXML
    private void handleAbonos(ActionEvent event) {
        mainProject.showAbonos();
    }

    @FXML
    private void handleReports(ActionEvent event) {
        mainProject.showReportView();
    }
}
