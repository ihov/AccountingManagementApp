package cl.ihov.project.view.controllers;

import cl.ihov.project.common.exception.DataException;
import cl.ihov.project.common.initial.MainProject;
import cl.ihov.project.common.utils.BaseJasperReports;
import cl.ihov.project.common.vo.Abono;
import cl.ihov.project.common.vo.Fechas;
import cl.ihov.project.managers.abono.AbonoManager;
import cl.ihov.project.managers.abono.AbonoManagerImpl;
import cl.ihov.project.view.components.ListPaymentsViewComponent;
import cl.ihov.project.view.utils.DateUtils;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import net.sf.jasperreports.engine.JRException;

public class ListPaymentsController extends ListPaymentsViewComponent implements Initializable {

    private MainProject mainProject;
    private AbonoManager abonoManager;
    private ObservableList<Abono> abonos;
    private Fechas fechas;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        abonoManager = new AbonoManagerImpl();
        fechas = new Fechas();
    }

    public void setMainProject(MainProject mainProject) {
        this.mainProject = mainProject;
        fechaTermino.setEditable(false);
        fechaInicio.setEditable(false);

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
            hm = new HashMap();
            hm.put("P_FECHA_DESDE", DateUtils.date2string(fechas.getFechaInicio()));
            hm.put("P_FECHA_HASTA", DateUtils.date2string(fechas.getFechaTermino()));
            hm.put("P_TOTAL", total.getText());
            BaseJasperReports.createReport("listadoAbonosEmpresa", hm);
        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleFindAbonos() {
        if (fechaInicio.getValue() != null) {
            Calendar c = Calendar.getInstance();
            c.set(fechaInicio.getValue().getYear(), fechaInicio.getValue().getMonthValue() - 1, fechaInicio.getValue().getDayOfMonth());
            if (fechaTermino.getValue() != null && !fechaInicio.getValue().isAfter(fechaTermino.getValue())) {
                Calendar ca = Calendar.getInstance();
                ca.set(fechaTermino.getValue().getYear(), fechaTermino.getValue().getMonthValue() - 1, fechaTermino.getValue().getDayOfMonth());
                fechas.setFechaInicio(c.getTime());
                fechas.setFechaTermino(ca.getTime());
                if (abonos != null) {
                    abonos.clear();
                    dataAbono.getColumns().clear();
                }
                try {
                    List<Abono> lista = abonoManager.findAbonos(fechas);
                    if (lista != null) {
                        int cont = 0;
                        for (Abono l : lista) {
                            cont = cont + l.getMontoInt();
                        }
                        total.setText(String.valueOf(cont));
                        abonos = FXCollections.observableArrayList();
                        lista.stream().forEach((l) -> {
                            abonos.add(
                                    new Abono(
                                            l.getRutCliente(),
                                            l.getRutEmpresa(),
                                            l.getIdAbono(),
                                            l.getMonto(),
                                            l.getObservacion(),
                                            l.getNrecibo(),
//                                            DateUtils.intMonth2stringMonth(Integer.valueOf(l.getMes())),
//                                            l.getAnno(),
                                            DateUtils.date2string(l.getFechaDate())));
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

//                        TableColumn mesCol = new TableColumn("Mes de abono");
//                        mesCol.setMinWidth(70);
//                        mesCol.setCellValueFactory(
//                                new PropertyValueFactory<>("mes")
//                        );
//
//                        TableColumn annoCol = new TableColumn("Año de abono");
//                        annoCol.setMinWidth(30);
//                        annoCol.setCellValueFactory(
//                                new PropertyValueFactory<>("anno")
//                        );

                        TableColumn fechaCol = new TableColumn("Fecha movimiento");
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
//                                mesCol,
//                                annoCol,
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
                DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
                        "Error",
                        "Fecha de término",
                        "El registro fecha de término no puede estar vacío y no debe ser menor que la fecha de inicio.");
            }
        } else {
            DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
                    "Error",
                    "Fecha de inicio",
                    "El registro fecha de inicio no puede estar vacío.");
        }
    }
}
