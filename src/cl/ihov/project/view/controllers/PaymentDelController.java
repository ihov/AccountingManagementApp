package cl.ihov.project.view.controllers;

import cl.ihov.project.common.exception.DataException;
import cl.ihov.project.common.initial.MainProject;
import cl.ihov.project.common.vo.Abono;
import cl.ihov.project.managers.abono.AbonoManager;
import cl.ihov.project.managers.abono.AbonoManagerImpl;
import cl.ihov.project.view.components.PaymentDelViewComponent;
import cl.ihov.project.view.utils.DateUtils;
import cl.ihov.project.view.utils.DialogUtils;
import cl.ihov.project.view.utils.ValidatorUtils;

import java.net.URL;
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

public class PaymentDelController extends PaymentDelViewComponent implements Initializable {

    private MainProject mainProject;
    private ObservableList<Abono> abonos;
    private AbonoManager abonoManeger;
    private Abono abonoEmpresa;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        abonoManeger = new AbonoManagerImpl();
        abonoEmpresa = new Abono();
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

    public void loadDataAbono(MouseEvent event) {
        if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {
            if (dataAbono.getSelectionModel().getSelectedItem() != null) {
                abonoEmpresa.setIdAbono(dataAbono.getSelectionModel().getSelectedItem().getIdAbono().trim());
                abonoEmpresa.setRutEmpresa(dataAbono.getSelectionModel().getSelectedItem().getRutEmpresa().trim());
                registroElimina.setText(
                        "ID: "
                        + dataAbono.getSelectionModel().getSelectedItem().getIdAbono()
                        + "| Rut Empresa:"
                        + dataAbono.getSelectionModel().getSelectedItem().getRutEmpresa()
                        + " | N° Recibo: "
                        + dataAbono.getSelectionModel().getSelectedItem().getNrecibo()
                        + " | Monto: "
                        + dataAbono.getSelectionModel().getSelectedItem().getMonto()
//                        + " | Mes: "
//                        + dataAbono.getSelectionModel().getSelectedItem().getMes()
//                        + " | Año: "
//                        + dataAbono.getSelectionModel().getSelectedItem().getAnno()
                        + " | Fecha: "
                        + dataAbono.getSelectionModel().getSelectedItem().getFecha()
                );
            }
        }
    }

    public void handleDeleteAbono(ActionEvent event) {
        try {
            if (dataAbono.getSelectionModel() != null && dataAbono.getSelectionModel().getSelectedItem() != null) {
                if (DialogUtils.showConfirmDialog(
                        "Confirmación",
                        "¿Desea eliminar este registro?",
                        "Esta acción no podrá ser revertida.")) {
                    abonoManeger.deleteAbono(abonoEmpresa);
                    buscaAbonosEditar(event);
                    registroElimina.setText("");
                    DialogUtils.showSimpleDialog(DialogUtils.INFORMATION_DIALOG,
                            "Éxito",
                            "Eliminación OK",
                            "La información fue eliminada correctamente.");
                }
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
