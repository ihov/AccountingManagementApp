package cl.ihov.project.view.controllers;

import cl.ihov.project.common.exception.DataException;
import cl.ihov.project.common.initial.MainProject;
import cl.ihov.project.common.utils.BaseJasperReports;
import cl.ihov.project.common.vo.Empresa;
import cl.ihov.project.managers.empresa.EmpresaManager;
import cl.ihov.project.managers.empresa.EmpresaManagerImpl;
import cl.ihov.project.view.components.ListEmpresasViewComponent;
import cl.ihov.project.view.utils.DialogUtils;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import net.sf.jasperreports.engine.JRException;

public class ListEmpresasController extends ListEmpresasViewComponent implements Initializable {

    private MainProject mainProject;
    private EmpresaManager empresaManager;
    private ObservableList<Empresa> empresas;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        empresaManager = new EmpresaManagerImpl();
        loadDataEmpresas();
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
    private void handleReportAllEmpresas(ActionEvent event) {
        HashMap hm = null;
        try {
            BaseJasperReports.createReport("listadoAllEmpresas", hm);
        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleReportClientEnable(ActionEvent event) {
        HashMap hm = null;
        try {
            hm = new HashMap();
            hm.put("P_ACTIVO", "true");
            BaseJasperReports.createReport("listadoClientesActivos", hm);
        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleReportClientDisable(ActionEvent event) {
        HashMap hm = null;
        try {
            hm = new HashMap();
            hm.put("P_ACTIVO", "false");
            BaseJasperReports.createReport("listadoClientesActivos", hm);
        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    private void loadDataEmpresas() {
        try {
            List<Empresa> lista = empresaManager.findAllEmpresa();
            if (lista != null) {

                empresas = FXCollections.observableArrayList();

                lista.stream().forEach((c) -> {
                    Empresa e = new Empresa();
                    e.setRutCliente(c.getRutCliente());
                    e.setRazonSocial(c.getRazonSocial());
                    e.setRutEmpresa(c.getRutEmpresa());
                    e.setGiroComercial(c.getGiroComercial());
                    e.setValorMensual(c.getValorMensual());
                    e.setActivo(c.getActivo());
                    empresas.add(e);
                });

                TableColumn rutClienteCol = new TableColumn("Rut Cliente");
                rutClienteCol.setMinWidth(100);
                rutClienteCol.setCellValueFactory(
                        new PropertyValueFactory<>("rutCliente")
                );

                TableColumn rutEmprersaCol = new TableColumn("Rut Empresa");
                rutEmprersaCol.setMinWidth(100);
                rutEmprersaCol.setCellValueFactory(
                        new PropertyValueFactory<>("rutEmpresa")
                );

                TableColumn razonSocialCol = new TableColumn("Razón Social");
                razonSocialCol.setMinWidth(100);
                razonSocialCol.setCellValueFactory(
                        new PropertyValueFactory<>("razonSocial")
                );

                TableColumn giroComercialCol = new TableColumn("Giro Comercial");
                giroComercialCol.setMinWidth(100);
                giroComercialCol.setCellValueFactory(
                        new PropertyValueFactory<>("giroComercial")
                );

                TableColumn valorMensualCol = new TableColumn("Valor Mensual");
                valorMensualCol.setMinWidth(100);
                valorMensualCol.setCellValueFactory(
                        new PropertyValueFactory<>("valorMensual")
                );

                TableColumn activoCol = new TableColumn("Activo");
                activoCol.setMinWidth(100);
                activoCol.setCellValueFactory(
                        new PropertyValueFactory<>("activo")
                );

                dataEmpresa.setItems(empresas);
                dataEmpresa.getColumns().addAll(
                        rutClienteCol,
                        rutEmprersaCol,
                        razonSocialCol,
                        giroComercialCol,
                        valorMensualCol,
                        activoCol
                );
            } else {
                DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
                        "Error",
                        "Ocurrió un problema",
                        "Problema . \nIntente nuevamente.");
            }
        } catch (DataException ex) {
            DialogUtils.showExceptionDialog(
                    "Error",
                    "Se ha producido un error inesperado",
                    "El detalle de la excepción se presenta \na continuación",
                    new DataException(ex));
        }
    }
}
