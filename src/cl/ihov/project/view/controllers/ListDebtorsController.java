package cl.ihov.project.view.controllers;

import cl.ihov.project.common.exception.DataException;
import cl.ihov.project.common.initial.MainProject;
import cl.ihov.project.common.utils.BaseJasperReports;
import cl.ihov.project.common.utils.BaseResources;
import cl.ihov.project.common.vo.Cliente;
import cl.ihov.project.common.vo.Deudor;
import cl.ihov.project.common.vo.Empresa;
import cl.ihov.project.common.vo.Mes;
import cl.ihov.project.managers.abono.AbonoManager;
import cl.ihov.project.managers.abono.AbonoManagerImpl;
import cl.ihov.project.managers.cliente.ClienteManager;
import cl.ihov.project.managers.cliente.ClienteManagerImpl;
import cl.ihov.project.managers.empresa.EmpresaManager;
import cl.ihov.project.managers.empresa.EmpresaManagerImpl;
import cl.ihov.project.view.components.ListDebtorsViewComponent;
import cl.ihov.project.view.utils.DialogUtils;
import cl.ihov.project.view.utils.SendMailUtils;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
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
    private ClienteManager clienteManeger;
    private AbonoManager abonoManager;
    private Deudor deudor;
    private ObservableList<Deudor> listaDeudores;
    private List<Empresa> empresasSelected;
    private List<Cliente> clientesDeudores;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        empresaManeger = new EmpresaManagerImpl();
        abonoManager = new AbonoManagerImpl();
        clienteManeger = new ClienteManagerImpl();
        mesAbono.setPromptText(BaseResources.getValue("sys_config", "promptComboMesAbono"));
        annioAbono.setPromptText(BaseResources.getValue("sys_config", "promptComboAnnioAbono"));
        sendMail.setDisable(true);
        deudoresAll.setDisable(true);
        deudor = new Deudor();
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
                    List<Deudor> lista = abonoManager.findDeudores(deudor);
                    if (lista != null) {
                        listaDeudores = FXCollections.observableArrayList();
                        lista.stream().forEach((c) -> {
                            Deudor e = new Deudor();
                            e.setRazonSocial(c.getRazonSocial());
                            e.setRutEmpresa(c.getRutEmpresa());
                            e.setGiroComercial(c.getGiroComercial());
                            e.setValorMensual(c.getValorMensual());
                            e.setMontoAbono(c.getMontoAbono());
                            e.setMontoDebe(c.getMontoDebe());
                            listaDeudores.add(e);
                        });

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
                        giroComercialCol.setMinWidth(200);
                        giroComercialCol.setCellValueFactory(
                                new PropertyValueFactory<>("giroComercial")
                        );

                        TableColumn valorMensualCol = new TableColumn("Valor Mensual");
                        valorMensualCol.setMinWidth(100);
                        valorMensualCol.setCellValueFactory(
                                new PropertyValueFactory<>("valorMensual")
                        );

                        TableColumn montoAbonoCol = new TableColumn("Monto abono");
                        montoAbonoCol.setMinWidth(100);
                        montoAbonoCol.setCellValueFactory(
                                new PropertyValueFactory<>("montoAbono")
                        );

                        TableColumn montoDebeCol = new TableColumn("Monto debe");
                        montoDebeCol.setMinWidth(100);
                        montoDebeCol.setCellValueFactory(
                                new PropertyValueFactory<>("montoDebe")
                        );
                        dataEmpresa.setItems(listaDeudores);
                        dataEmpresa.getColumns().addAll(
                                rutEmprersaCol,
                                razonSocialCol,
                                giroComercialCol,
                                montoAbonoCol,
                                montoDebeCol,
                                valorMensualCol
                        );
                        dataEmpresa.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                        if (!lista.isEmpty()) {
                            sendMail.setDisable(false);
                            deudoresAll.setDisable(false);
                        }
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

    @FXML
    private void sendMail(ActionEvent event) {
        if (DialogUtils.showConfirmDialog("Confirmación", "Envío de correo masivo", "¿Desea enviar los correos?")) {
            goReportes.setDisable(true);
            dataEmpresa.setDisable(true);
            sendMail.setDisable(true);
            annioAbono.setDisable(true);
            mesAbono.setDisable(true);
            buscar.setDisable(true);
            deudoresAll.setDisable(true);
            progreso.setProgress(-1.0f);
            empresasSelected = new ArrayList<>();
            dataEmpresa.getSelectionModel().getSelectedItems().stream().forEach((empresa) -> {
                try {
                    Empresa empresaBuscar = new Empresa();
                    empresaBuscar.setRutEmpresa(empresa.getRutEmpresa());
                    empresasSelected.add(empresaManeger.findEmpresa(empresaBuscar));
                } catch (DataException ex) {
                    DialogUtils.showExceptionDialog(
                            "Error",
                            "Se ha producido un error inesperado",
                            "El detalle de la excepción se presenta \na continuación",
                            new DataException(ex));
                }
            });

            Cliente cliente = new Cliente();
            clientesDeudores = new ArrayList<>();
            empresasSelected.stream().map((empresa) -> {
                cliente.setRutCliente(empresa.getRutCliente());
                return empresa;
            }).forEach((_item) -> {
                try {
                    clientesDeudores.add(clienteManeger.findCliente(cliente));
                } catch (DataException ex) {
                    DialogUtils.showExceptionDialog(
                            "Error",
                            "Se ha producido un error inesperado",
                            "El detalle de la excepción se presenta \na continuación",
                            new DataException(ex));
                }
            });

            final String from = "ihovlimitada@gmail.com";
            final String password = "12345678ihov";
            final String subject = "MENSUALIDAD PENDIENTE";
            final String body = "Estimado Cliente<strong><br/><br/><em>Le recuerdo que usted tiene una cuota impaga a la fecha de hoy, favor contactarme para saldar la deuda.<br/></em></strong><strong><br/><em>Saludos cordiales</em></strong>";
            final HashMap<Object, Object> proBar = new HashMap<>();

            Task<Void> task = new Task<Void>() {
                @Override
                public Void call() {
                    dataEmpresa.setDisable(true);
                    int c = 0;
                    for (int i = 0; i < clientesDeudores.size(); i++) {
                        if (!SendMailUtils.send(clientesDeudores.get(i).getEmail().trim(), from, subject, body, password)) {
                            proBar.put(c, clientesDeudores.get(i).getEmail());
                            c++;
                        }
                        updateProgress(i, clientesDeudores.size() - 1);
                    }
                    goReportes.setDisable(false);
                    dataEmpresa.setDisable(false);
                    sendMail.setDisable(false);
                    annioAbono.setDisable(false);
                    mesAbono.setDisable(false);
                    buscar.setDisable(false);
                    deudoresAll.setDisable(false);
                    return null;
                }
            };

            progreso.progressProperty().bind(task.progressProperty());
            Thread th = new Thread(task);
            th.setDaemon(true);
            th.start();
            dataEmpresa.getSelectionModel().clearSelection();
        }

    }

}
