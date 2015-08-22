package cl.ihov.project.view.controllers;

import cl.ihov.project.common.initial.MainProject;
import cl.ihov.project.view.utils.DialogUtils;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class EmpresasController implements Initializable {

    private MainProject mainProject;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setMainProject(MainProject mainProject) {
        this.mainProject = mainProject;
    }
    
    @FXML
    private void handleEmpresas(ActionEvent event) {
        mainProject.showEmpresas();
    }

    public void showAuthError() {
        DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
                "Error",
                "Usuario o clave incorrecto",
                "La información ingresada no es correcta. \nIntente nuevamente.");
    }

    @FXML
    private void handleGoHome(ActionEvent event) {
        mainProject.showMainMenu();
    }
    
   @FXML
    private void handleEnterprise(ActionEvent event) {
        mainProject.showEnterpriseView();
    }
    
   @FXML
    private void handleGoEditEnterprise(ActionEvent event) {
        mainProject.showEnterpriseEditView();
    }
}
