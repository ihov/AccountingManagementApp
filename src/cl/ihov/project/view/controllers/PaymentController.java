package cl.ihov.project.view.controllers;

import cl.ihov.project.common.exception.DataException;
import cl.ihov.project.common.initial.MainProject;
import cl.ihov.project.common.utils.BaseResources;
import cl.ihov.project.common.vo.Abono;
import cl.ihov.project.common.vo.Empresa;
import cl.ihov.project.common.vo.Mes;
import cl.ihov.project.managers.abono.AbonoManager;
import cl.ihov.project.managers.abono.AbonoManagerImpl;
import cl.ihov.project.managers.empresa.EmpresaManager;
import cl.ihov.project.managers.empresa.EmpresaManagerImpl;
import cl.ihov.project.view.components.PaymentViewComponent;
import cl.ihov.project.view.utils.DialogUtils;
import cl.ihov.project.view.utils.ValidatorUtils;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class PaymentController extends PaymentViewComponent implements Initializable {

    private MainProject mainProject;
    private ObservableList<Empresa> empresas;
    private EmpresaManager empresaManeger;
    private AbonoManager abonoManeger;
    private Abono abonoEmpresa;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mesAbono.setPromptText(BaseResources.getValue("sys_config", "promptComboMesAbono"));
        annioAbono.setPromptText(BaseResources.getValue("sys_config", "promptComboAnnioAbono"));
        empresaManeger = new EmpresaManagerImpl();
        abonoManeger = new AbonoManagerImpl();
        abonoEmpresa = new Abono();
        loadDataEmpresas();
        loadMeses();
        cargaPeriodos();
        fecha.setEditable(false);

        monto.focusedProperty().addListener((ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) -> {
            if (!newPropertyValue && !monto.getText().isEmpty()) {
                if (!ValidatorUtils.monto(monto.getText())) {
                    DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
                            "Error",
                            "Monto incorrecto",
                            "El monto " + monto.getText() + " no es válido. \nIntente nuevamente (Ejemplo: 10000).");
                    monto.setText("");
                }
            }
        });
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
    private void handleAbonos(){
        mainProject.showAbonos();
    }

    @FXML
    private void handleDoPayment(ActionEvent event) {
        if (dataEmpresa.getSelectionModel().getSelectedItem() != null) {
            Empresa e = dataEmpresa.getSelectionModel().getSelectedItem();
            abonoEmpresa.setRutEmpresa(e.getRutEmpresa().trim());
            abonoEmpresa.setRutCliente(e.getRutCliente().trim());
            if (monto.getText() != null && !monto.getText().isEmpty()) {
                abonoEmpresa.setMonto(monto.getText().trim());
                if (observacion.getText() != null && !observacion.getText().isEmpty()) {
                    abonoEmpresa.setObservacion(observacion.getText().trim());
                    if (nrecibo.getText() != null && !nrecibo.getText().isEmpty()) {
                        abonoEmpresa.setNrecibo(nrecibo.getText().trim());
                        if (mesAbono.getValue() != null && !mesAbono.getValue().equals("0")) {
                            abonoEmpresa.setMes(String.valueOf(mesAbono.getSelectionModel().getSelectedIndex()));
                            if (annioAbono.getValue() != null && !annioAbono.getValue().equals("0")) {
                                abonoEmpresa.setAnno(annioAbono.getSelectionModel().getSelectedItem());
                                if (fecha.getValue() != null) {
                                    Calendar c = Calendar.getInstance();
                                    c.set(fecha.getValue().getYear(), fecha.getValue().getMonthValue() - 1, fecha.getValue().getDayOfMonth());
                                    abonoEmpresa.setFechaDate(c.getTime());
                                    try {
                                        abonoManeger.insertAbono(abonoEmpresa);
                                        DialogUtils.showSimpleDialog(DialogUtils.INFORMATION_DIALOG,
                                                "Éxito",
                                                "Ingreso OK",
                                                "La información fue ingresada correctamente.");
                                        mainProject.showAbonos();
                                    } catch (DataException ex) {
                                        DialogUtils.showExceptionDialog(
                                                "Error",
                                                "Se ha producido un error inesperado",
                                                "El detalle de la excepción se presenta \na continuación",
                                                new DataException(ex));
                                    }
                                } else {
                                    DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
                                            "Error",
                                            "Fecha del abono",
                                            "El registro fecha del abono no puede estar vacío. \nIntente seleccionando un elemento de la lista.");
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
                    } else {
                        DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
                                "Error",
                                "N° de recibo",
                                "El registro N° de recibo no puede estar vacío. \nIntente seleccionando un elemento de la lista.");
                    }
                } else {
                    DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
                            "Error",
                            "Observación de abono",
                            "El registro de observación de abono no puede estar vacío. \nIntente seleccionando un elemento de la lista.");
                }
            } else {
                DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
                        "Error",
                        "Valor de abono",
                        "El registro valor de abono no puede estar vacío. \nIntente seleccionando un elemento de la lista.");
            }
        } else {
            DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
                    "Error",
                    "Empresa",
                    "El registro empresa no puede estar vacío. \nIntente seleccionando un elemento de la lista.");
        }
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

    private void loadMeses() {
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

    private void loadDataEmpresas() {
        try {
            List<Empresa> lista = empresaManeger.findAllEmpresa();
            if (lista != null) {

                empresas = FXCollections.observableArrayList();

                lista.stream().forEach((c) -> {
                    Empresa e = new Empresa();
                    e.setRutCliente(c.getRutCliente());
                    e.setRazonSocial(c.getRazonSocial());
                    e.setRutEmpresa(c.getRutEmpresa());
                    e.setGiroComercial(c.getGiroComercial());
                    e.setValorMensual(c.getValorMensual());
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

                dataEmpresa.setItems(empresas);
                dataEmpresa.getColumns().addAll(
                        rutClienteCol,
                        rutEmprersaCol,
                        razonSocialCol,
                        giroComercialCol,
                        valorMensualCol
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
