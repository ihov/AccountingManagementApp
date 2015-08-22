package cl.ihov.project.view.controllers;

import cl.ihov.project.common.exception.DataException;
import cl.ihov.project.common.initial.MainProject;
import cl.ihov.project.common.utils.BaseJasperReports;
import cl.ihov.project.common.vo.Cliente;
import cl.ihov.project.managers.cliente.ClienteManager;
import cl.ihov.project.managers.cliente.ClienteManagerImpl;
import cl.ihov.project.view.components.ListClientsViewComponent;
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

public class ListClientsController extends ListClientsViewComponent implements Initializable {

    private MainProject mainProject;
    private ClienteManager cliente;
    private ObservableList<Cliente> clientes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cliente = new ClienteManagerImpl();
        loadDataCliente();
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
    private void handleReportAllClient(ActionEvent event) {
        HashMap hm = null;
        try {
            BaseJasperReports.createReport("listadoClientes", hm);
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

    private void loadDataCliente() {
        try {
            List<Cliente> lista = cliente.findAllClientes();
            if (lista != null) {

                clientes = FXCollections.observableArrayList();

                lista.stream().forEach((c) -> {
                    clientes.add(
                            new Cliente(
                                    c.getRutCliente(),
                                    c.getRutAnterior(),
                                    c.getNombres(),
                                    c.getPaterno(),
                                    c.getMaterno(),
                                    c.getEmail(),
                                    c.getTelFijo(),
                                    c.getCelular(),
                                    c.getActivo().equals("true") ? "Si" : "No"));
                });

                TableColumn rutClienteCol = new TableColumn("Rut Cliente");
                rutClienteCol.setMinWidth(100);
                rutClienteCol.setCellValueFactory(
                        new PropertyValueFactory<>("rutCliente")
                );
//                TableColumn rutAnteriorCol = new TableColumn("Rut Anterior");
//                rutAnteriorCol.setCellValueFactory(
//                        new PropertyValueFactory<>("rutAnterior")
//                );
                TableColumn nombresCol = new TableColumn("Nombres");
                nombresCol.setCellValueFactory(
                        new PropertyValueFactory<>("nombres")
                );
                TableColumn paternoCol = new TableColumn("Paterno");
                paternoCol.setCellValueFactory(
                        new PropertyValueFactory<>("paterno")
                );
                TableColumn maternoCol = new TableColumn("Materno");
                maternoCol.setCellValueFactory(
                        new PropertyValueFactory<>("materno")
                );
                TableColumn emailCol = new TableColumn("Email");
                emailCol.setCellValueFactory(
                        new PropertyValueFactory<>("email")
                );
                TableColumn telFijoCol = new TableColumn("Telefono Fijo");
                telFijoCol.setMinWidth(100);
                telFijoCol.setCellValueFactory(
                        new PropertyValueFactory<>("telFijo")
                );
                TableColumn celularCol = new TableColumn("Celular");
                celularCol.setMinWidth(100);
                celularCol.setCellValueFactory(
                        new PropertyValueFactory<>("celular")
                );

                TableColumn activoCol = new TableColumn("Activo");
                activoCol.setCellValueFactory(
                        new PropertyValueFactory<>("activo")
                );

                dataCliente.setItems(clientes);
                dataCliente.getColumns().addAll(
                        rutClienteCol,
                        //rutAnteriorCol, 
                        nombresCol,
                        paternoCol,
                        maternoCol,
                        emailCol,
                        telFijoCol,
                        celularCol,
                        activoCol);
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
