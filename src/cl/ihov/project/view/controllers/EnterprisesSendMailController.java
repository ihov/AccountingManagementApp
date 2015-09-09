package cl.ihov.project.view.controllers;

import cl.ihov.project.common.exception.DataException;
import cl.ihov.project.common.initial.MainProject;
import cl.ihov.project.common.vo.Cliente;
import cl.ihov.project.common.vo.Empresa;
import cl.ihov.project.managers.cliente.ClienteManager;
import cl.ihov.project.managers.cliente.ClienteManagerImpl;
import cl.ihov.project.managers.empresa.EmpresaManager;
import cl.ihov.project.managers.empresa.EmpresaManagerImpl;
import cl.ihov.project.view.components.EnterprisesSendMailViewComponent;
import cl.ihov.project.view.utils.DialogUtils;
import cl.ihov.project.view.utils.SendMailUtils;
import java.net.URL;
import java.util.ArrayList;
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

public class EnterprisesSendMailController extends EnterprisesSendMailViewComponent implements Initializable {

    private MainProject mainProject;
    private EmpresaManager empresaManeger;
    private ClienteManager clienteManeger;
    private ObservableList<Empresa> empresas;
    private List<Empresa> empresasSelected;
    private List<Cliente> clientesDeudores;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        empresaManeger = new EmpresaManagerImpl();
        clienteManeger = new ClienteManagerImpl();
        loadDataEmpresas();
    }

    public void setMainProject(MainProject mainProject) {
        this.mainProject = mainProject;
    }

    @FXML
    private void handleEmpresas(ActionEvent event) {
        mainProject.showEmpresas();
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
                giroComercialCol.setMinWidth(200);
                giroComercialCol.setCellValueFactory(
                        new PropertyValueFactory<>("giroComercial")
                );

                TableColumn valorMensualCol = new TableColumn("Valor Mensual");
                valorMensualCol.setMinWidth(100);
                valorMensualCol.setCellValueFactory(
                        new PropertyValueFactory<>("valorMensual")
                );

                dataEmpresas.setItems(empresas);
                dataEmpresas.getColumns().addAll(
                        rutClienteCol,
                        rutEmprersaCol,
                        razonSocialCol,
                        giroComercialCol,
                        valorMensualCol
                );

                dataEmpresas.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
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
    private void sendMail(ActionEvent event) {
        if (DialogUtils.showConfirmDialog("Confirmación", "Envío de correo masivo", "¿Desea enviar los correos?")) {
            goHome.setDisable(true);
            dataEmpresas.setDisable(true);
            sendMail.setDisable(true);
            progreso.setProgress(-1.0f);
            empresasSelected = new ArrayList<>();
            dataEmpresas.getSelectionModel().getSelectedItems().stream().forEach((empresa) -> {
                try {
                    empresasSelected.add(empresaManeger.findEmpresa(empresa));
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

            final String from = "correo@gmail.com";
            final String password = "password";
            final String subject = "DEDUDORES CULIAOS";
            final String body = "Estimados CTM<strong><em>Alguna wea</em></strong><strong><em>Alguna otra wea</em></strong>";
            final HashMap<Object, Object> proBar = new HashMap<>();

            Task<Void> task = new Task<Void>() {
                @Override
                public Void call() {
                    dataEmpresas.setDisable(true);
                    int c = 0;
                    for (int i = 0; i < clientesDeudores.size(); i++) {
                        if (!SendMailUtils.send(clientesDeudores.get(i).getEmail().trim(), from, subject, body, password)) {
                            proBar.put(c, clientesDeudores.get(i).getEmail());
                            c++;
                        }
                        updateProgress(i, clientesDeudores.size() - 1);
                    }
                    goHome.setDisable(false);
                    dataEmpresas.setDisable(false);
                    sendMail.setDisable(false);
                    return null;
                }
            };

            progreso.progressProperty().bind(task.progressProperty());
            Thread th = new Thread(task);
            th.setDaemon(true);
            th.start();
            dataEmpresas.getSelectionModel().clearSelection();
        }

    }

}
