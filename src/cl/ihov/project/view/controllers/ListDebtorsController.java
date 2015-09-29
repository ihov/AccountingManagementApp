package cl.ihov.project.view.controllers;

import cl.ihov.project.common.exception.DataException;
import cl.ihov.project.common.initial.MainProject;
import cl.ihov.project.common.utils.BaseJasperReports;
import cl.ihov.project.common.utils.BaseResources;
import cl.ihov.project.common.vo.Deudor;
import cl.ihov.project.common.vo.Empresa;
import cl.ihov.project.common.vo.Mes;
import cl.ihov.project.managers.abono.AbonoManager;
import cl.ihov.project.managers.abono.AbonoManagerImpl;
import cl.ihov.project.managers.empresa.EmpresaManager;
import cl.ihov.project.managers.empresa.EmpresaManagerImpl;
import cl.ihov.project.view.components.ListDebtorsViewComponent;
import cl.ihov.project.view.utils.DialogUtils;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import net.sf.jasperreports.engine.JRException;

public class ListDebtorsController extends ListDebtorsViewComponent implements Initializable {

    private MainProject mainProject;
    private EmpresaManager empresaManeger;
    private AbonoManager abonoManager;
    private Deudor deudor;
    private ObservableList<Deudor>listaDeudores;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        empresaManeger = new EmpresaManagerImpl();
        abonoManager = new AbonoManagerImpl();
        mesAbono.setPromptText(BaseResources.getValue("sys_config", "promptComboMesAbono"));
        annioAbono.setPromptText(BaseResources.getValue("sys_config", "promptComboAnnioAbono"));
        deudor=new Deudor();
        cargaPeriodos();
        cargaMeses();

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

    private void cargaPeriodos() {
        annioAbono.getItems().add(0, BaseResources.getValue("sys_config", "promptComboAnnioAbono"));
        int cont = 1;
        int initialYear = 2010;
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int year = currentYear; year >= initialYear; year--) {
            annioAbono.getItems().add(cont, String.valueOf(year));
            cont++;
        }
    }

    private void cargaMeses() {
        try {
            List<Mes> lista = empresaManeger.findMeses();
            if (lista != null) {
                mesAbono.getItems().add(0, BaseResources.getValue("sys_config", "promptComboMesAbono"));
                lista.stream().forEach((mes) -> {
                    mesAbono.getItems().add(mes.getIdMes(), mes.getDescripcion());
                });
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

    @FXML
    private void handleGoReports(ActionEvent event) {
        mainProject.showReportView();
    }

    @FXML
    private void handleReportAllDeudores(ActionEvent event) {
        HashMap hm = null;
        try {
            BaseJasperReports.createReport("listadoAllEmpresas", hm);
        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleBuscaDeudores(ActionEvent event) {
        if (mesAbono.getValue() != null && !mesAbono.getValue().equals("0")) {
            deudor.setMes(String.valueOf(mesAbono.getSelectionModel().getSelectedIndex()));
            if (annioAbono.getValue() != null && !annioAbono.getValue().equals("0")) {
                deudor.setAnno(annioAbono.getSelectionModel().getSelectedItem());
                
                try {
                    List<Deudor>lista=abonoManager.findDeudores(deudor);
                    
                }catch (DataException ex) {
                    DialogUtils.showExceptionDialog(
                            "Error",
                            "Se ha producido un error inesperado",
                            "El detalle de la excepción se presenta \na continuación",
                            new DataException(ex));
                }
            } else {
                DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
                        "Error",
                        "Año de abono",
                        "El registro año de abono no puede estar vacío. \nIntente seleccionando un elemento de la lista.");
            }
        } else {
            DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
                    "Error",
                    "Mes de abono",
                    "El registro mes de abono no puede estar vacío. \nIntente seleccionando un elemento de la lista.");

        }
    }

//    private void loadDataEmpresas() {
//        try {
//            List<Empresa> lista = empresaManeger.findAllEmpresa();
//            if (lista != null) {
//
//                empresas = FXCollections.observableArrayList();
//
//                lista.stream().forEach((c) -> {
//                    Empresa e = new Empresa();
//                    e.setRutCliente(c.getRutCliente());
//                    e.setRazonSocial(c.getRazonSocial());
//                    e.setRutEmpresa(c.getRutEmpresa());
//                    e.setGiroComercial(c.getGiroComercial());
//                    e.setValorMensual(c.getValorMensual());
//                    empresas.add(e);
//                });
//
//                TableColumn rutClienteCol = new TableColumn("Rut Cliente");
//                rutClienteCol.setMinWidth(100);
//                rutClienteCol.setCellValueFactory(
//                        new PropertyValueFactory<>("rutCliente")
//                );
//
//                TableColumn rutEmprersaCol = new TableColumn("Rut Empresa");
//                rutEmprersaCol.setMinWidth(100);
//                rutEmprersaCol.setCellValueFactory(
//                        new PropertyValueFactory<>("rutEmpresa")
//                );
//
//                TableColumn razonSocialCol = new TableColumn("Razón Social");
//                razonSocialCol.setMinWidth(100);
//                razonSocialCol.setCellValueFactory(
//                        new PropertyValueFactory<>("razonSocial")
//                );
//
//                TableColumn giroComercialCol = new TableColumn("Giro Comercial");
//                giroComercialCol.setMinWidth(200);
//                giroComercialCol.setCellValueFactory(
//                        new PropertyValueFactory<>("giroComercial")
//                );
//
//                TableColumn valorMensualCol = new TableColumn("Valor Mensual");
//                valorMensualCol.setMinWidth(100);
//                valorMensualCol.setCellValueFactory(
//                        new PropertyValueFactory<>("valorMensual")
//                );
//
//                dataEmpresas.setItems(empresas);
//                dataEmpresas.getColumns().addAll(
//                        rutClienteCol,
//                        rutEmprersaCol,
//                        razonSocialCol,
//                        giroComercialCol,
//                        valorMensualCol
//                );
//
//                dataEmpresas.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//            } else {
//                DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
//                        "Error",
//                        "Ocurrió un problema",
//                        "Problema . \nIntente nuevamente.");
//            }
//        } catch (DataException ex) {
//            DialogUtils.showExceptionDialog(
//                    "Error",
//                    "Se ha producido un error inesperado",
//                    "El detalle de la excepción se presenta \na continuación",
//                    new DataException(ex));
//        }
//    }
}
