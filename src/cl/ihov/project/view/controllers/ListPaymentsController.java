package cl.ihov.project.view.controllers;

import cl.ihov.project.common.initial.MainProject;
import cl.ihov.project.common.utils.BaseJasperReports;
import cl.ihov.project.view.components.ListPaymentsViewComponent;
import cl.ihov.project.view.utils.DialogUtils;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import net.sf.jasperreports.engine.JRException;

public class ListPaymentsController extends ListPaymentsViewComponent implements Initializable {

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
    private void handleGoReports(ActionEvent event) {
        mainProject.showReportView();
    }
    
    @FXML
    private void handleReportAllAbonos(ActionEvent event) {
        HashMap hm = null;
        try {
            BaseJasperReports.createReport("listadoAllEmpresas", hm);
        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }
}
