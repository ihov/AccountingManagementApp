package cl.ihov.project.view.controllers;

import cl.ihov.project.common.initial.MainProject;
import cl.ihov.project.view.components.ReportViewComponent;
import cl.ihov.project.view.utils.DialogUtils;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class ReportController extends ReportViewComponent implements Initializable {

    private MainProject mainProject;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setMainProject(MainProject mainProject) {
        this.mainProject = mainProject;
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
    private void handleGoListClient(ActionEvent event) {
        mainProject.showListReportClientsView();
    }
    
    @FXML
    private void handleGoListDebtor(ActionEvent event) {
        mainProject.showListReportDebtorsView();
    }
    
    @FXML
    private void handleGoListPayment(ActionEvent event) {
        mainProject.showListReportPaymentsView();
    }
}
