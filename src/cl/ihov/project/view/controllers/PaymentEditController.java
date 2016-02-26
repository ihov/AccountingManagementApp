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
import cl.ihov.project.view.components.PaymentEditViewComponent;
import cl.ihov.project.view.utils.DateUtils;
import cl.ihov.project.view.utils.DialogUtils;
import cl.ihov.project.view.utils.ValidatorUtils;

import java.net.URL;
import java.time.ZoneId;
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
import javafx.scene.input.MouseEvent;

public class PaymentEditController extends PaymentEditViewComponent implements Initializable {

    private MainProject mainProject;
    private ObservableList<Empresa> empresas;
    private ObservableList<Abono> abonos;
    private EmpresaManager empresaManeger;
    private AbonoManager abonoManeger;
    private Abono abonoEmpresa;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        mesAbono.setPromptText(BaseResources.getValue("sys_config", "promptComboMesAbono"));
//        annioAbono.setPromptText(BaseResources.getValue("sys_config", "promptComboAnnioAbono"));
        empresaManeger = new EmpresaManagerImpl();
        abonoManeger = new AbonoManagerImpl();
        abonoEmpresa = new Abono();
//        loadMeses();
//        cargaPeriodos();

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
        rutBusca.focusedProperty().addListener((ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) -> {
            if (!newPropertyValue && !rutBusca.getText().isEmpty()) {
                if (!ValidatorUtils.rut(rutBusca.getText())) {
                    DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
                            "Error",
                            "Rut incorrecto",
                            "El rut " + rutBusca.getText() + " no es válido. \nIntente nuevamente (Ejemplo: 12345678-9).");
                    rutBusca.setText("");
                }
            }
        });
        fechaAbono.setEditable(false);
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
    private void handleAbonos(ActionEvent event) {
        mainProject.showAbonos();
    }

    @FXML
    private void handleDoEditPayment(ActionEvent event) {
        if (dataAbono.getSelectionModel() != null && dataAbono.getSelectionModel().getSelectedItem() != null) {
            if (rutBusca != null && !rutBusca.getText().isEmpty()) {
                abonoEmpresa.setRutEmpresa(rutBusca.getText().trim());
                abonoEmpresa.setIdAbono(dataAbono.getSelectionModel().getSelectedItem().getIdAbono());
                if (monto.getText() != null && !monto.getText().isEmpty()) {
                    abonoEmpresa.setMonto(monto.getText().trim());
                    if (observacion.getText() != null && !observacion.getText().isEmpty()) {
                        abonoEmpresa.setObservacion(observacion.getText().trim());
                        if (nrecibo.getText() != null && !nrecibo.getText().isEmpty()) {
                            abonoEmpresa.setNrecibo(nrecibo.getText().trim());
//                            if (mesAbono.getValue() != null && !mesAbono.getValue().equals("0")) {
//                                abonoEmpresa.setMes(String.valueOf(mesAbono.getSelectionModel().getSelectedIndex()));
//                                if (annioAbono.getValue() != null && !annioAbono.getValue().equals("0")) {
//                                    abonoEmpresa.setAnno(annioAbono.getSelectionModel().getSelectedItem());
                                    if (fechaAbono.getValue() != null) {
                                        Calendar c = Calendar.getInstance();
                                        c.set(fechaAbono.getValue().getYear(), fechaAbono.getValue().getMonthValue() - 1, fechaAbono.getValue().getDayOfMonth());
                                        abonoEmpresa.setFechaDate(c.getTime());
                                        try {
                                            abonoManeger.updateAbono(abonoEmpresa);
                                            DialogUtils.showSimpleDialog(DialogUtils.INFORMATION_DIALOG,
                                                    "Éxito",
                                                    "Edición OK",
                                                    "La información fue Editada correctamente.");
                                            buscaAbonosEditar(event);
                                            clean();
                                            //mainProject.showAbonos();
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
//                                } else {
//                                    DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
//                                            "Error",
//                                            "Año de abono",
//                                            "El registro año de abono no puede estar vacío. \nIntente seleccionando un elemento de la lista.");
//                                }
//                            } else {
//                                DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
//                                        "Error",
//                                        "Mes de abono",
//                                        "El registro mes de abono no puede estar vacío. \nIntente seleccionando un elemento de la lista.");
//                            }
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
                        "Rut ",
                        "El registro Rut no puede estar vacío. \nIntente seleccionando un elemento de la lista.");
            }
        } else {
            DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
                    "Error",
                    "Selección ",
                    "Debe seleccionar un registro. \nIntente seleccionando un elemento de la lista.");
        }
    }

//    private void cargaPeriodos() {
//        annioAbono.getItems().clear();
//        annioAbono.getItems().add(0, BaseResources.getValue("sys_config", "promptComboAnnioAbono"));
//        int cont = 1;
//        int initialYear = 2010;
//        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
//        for (int year = currentYear; year >= initialYear; year--) {
//            annioAbono.getItems().add(cont, String.valueOf(year));
//            cont++;
//        }
//    }
//
//    private void loadMeses() {
//        try {
//            List<Mes> lista = empresaManeger.findMeses();
//            if (lista != null) {
//                mesAbono.getItems().clear();
//                mesAbono.getItems().add(0, BaseResources.getValue("sys_config", "promptComboMesAbono"));
//                lista.stream().forEach((mes) -> {
//                    mesAbono.getItems().add(mes.getIdMes(), mes.getDescripcion());
//                });
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

    @FXML
    private void buscaAbonosEditar(ActionEvent event) {
        if (!rutBusca.getText().isEmpty()) {
            abonoEmpresa.setRutEmpresa(rutBusca.getText().trim());
            try {
                List<Abono> lista = abonoManeger.findAbonosEmpresa(abonoEmpresa);
                if (lista != null) {

                    abonos = FXCollections.observableArrayList();

                    lista.stream().forEach((c) -> {
                        abonos.add(
                                new Abono(
                                        c.getRutCliente(),
                                        c.getRutEmpresa(),
                                        c.getIdAbono(),
                                        c.getMonto(),
                                        c.getObservacion(),
                                        c.getNrecibo(),
//                                        DateUtils.intMonth2stringMonth(Integer.valueOf(c.getMes())),
//                                        c.getAnno(),
                                        DateUtils.date2string(c.getFechaDate())));
                    });

                    TableColumn rutempresaCol = new TableColumn("Rut Empresa");
                    rutempresaCol.setMinWidth(70);
                    rutempresaCol.setCellValueFactory(
                            new PropertyValueFactory<>("rutEmpresa")
                    );

                    TableColumn nreciboCol = new TableColumn("N° recibo");
                    nreciboCol.setMinWidth(100);
                    nreciboCol.setCellValueFactory(
                            new PropertyValueFactory<>("nrecibo")
                    );

                    TableColumn montoCol = new TableColumn("Monto");
                    montoCol.setMinWidth(50);
                    montoCol.setCellValueFactory(
                            new PropertyValueFactory<>("monto")
                    );

                    TableColumn observacionCol = new TableColumn("Observación");
                    observacionCol.setMinWidth(300);
                    observacionCol.setCellValueFactory(
                            new PropertyValueFactory<>("observacion")
                    );

//                    TableColumn mesCol = new TableColumn("Mes");
//                    mesCol.setMinWidth(70);
//                    mesCol.setCellValueFactory(
//                            new PropertyValueFactory<>("mes")
//                    );
//
//                    TableColumn annoCol = new TableColumn("Año");
//                    annoCol.setMinWidth(30);
//                    annoCol.setCellValueFactory(
//                            new PropertyValueFactory<>("anno")
//                    );

                    TableColumn fechaCol = new TableColumn("Fecha");
                    fechaCol.setMinWidth(70);
                    fechaCol.setCellValueFactory(
                            new PropertyValueFactory<>("fecha")
                    );

                    TableColumn idAbonoCol = new TableColumn("Identificador");
                    idAbonoCol.setMinWidth(10);
                    idAbonoCol.setCellValueFactory(
                            new PropertyValueFactory<>("idAbono")
                    );

                    dataAbono.setItems(abonos);
                    dataAbono.getColumns().addAll(
                            rutempresaCol,
                            nreciboCol,
                            montoCol,
//                            mesCol,
//                            annoCol,
                            fechaCol,
                            observacionCol,
                            idAbonoCol
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
        } else {

        }

    }

    private void clean() {
        monto.setText("");
        observacion.setText("");
        nrecibo.setText("");
//        cargaPeriodos();
//        loadMeses();
        fechaAbono.setValue(null);
    }

    @FXML
    private void loadDataAbono(MouseEvent event) {
        if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {
            if (dataAbono.getSelectionModel().getSelectedItem() != null) {
                monto.setText(dataAbono.getSelectionModel().getSelectedItem().getMonto());
                observacion.setText(dataAbono.getSelectionModel().getSelectedItem().getObservacion());
                nrecibo.setText(dataAbono.getSelectionModel().getSelectedItem().getNrecibo());
//                annioAbono.setValue(dataAbono.getSelectionModel().getSelectedItem().getAnno());
//                mesAbono.setValue(dataAbono.getSelectionModel().getSelectedItem().getMes());
                fechaAbono.setValue(DateUtils.string2date(dataAbono.getSelectionModel().getSelectedItem().getFecha()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            }
        } else {
        }
    }

}
