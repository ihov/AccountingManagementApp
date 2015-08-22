package cl.ihov.project.view.controllers;

import cl.ihov.project.common.exception.DataException;
import cl.ihov.project.common.initial.MainProject;
import cl.ihov.project.common.vo.Cliente;
import cl.ihov.project.managers.cliente.ClienteManager;
import cl.ihov.project.managers.cliente.ClienteManagerImpl;
import cl.ihov.project.view.components.ClientViewComponent;
import cl.ihov.project.view.utils.DialogUtils;
import cl.ihov.project.view.utils.ValidatorUtils;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class ClientController extends ClientViewComponent implements Initializable {

    private ClienteManager clienteManager;
    private MainProject mainProject;
    private Cliente cliente;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cliente = new Cliente();
        clienteManager = new ClienteManagerImpl();
        rut.focusedProperty().addListener((ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) -> {
            if (!newPropertyValue && !rut.getText().isEmpty()) {
                if (!ValidatorUtils.rut(rut.getText())) {
                    DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
                            "Error",
                            "Rut incorrecto",
                            "El rut " + rut.getText() + " no es válido. \nIntente nuevamente (Ejemplo: 12345678-9).");
                    rut.setText("");
                }
            }
        });
        email.focusedProperty().addListener((ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) -> {
            if (!newPropertyValue && !email.getText().isEmpty()) {
                if (!ValidatorUtils.email(email.getText())) {
                    DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
                            "Error",
                            "Email incorrecto",
                            "El Email " + email.getText() + " no es válido. \nIntente nuevamente (Ejemplo: victoria@dominio.cl).");
                    email.setText("");
                }
            }
        });
        telefono.focusedProperty().addListener((ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) -> {
            if (!newPropertyValue && !telefono.getText().isEmpty()) {
                if (!ValidatorUtils.telefono(telefono.getText())) {
                    DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
                            "Error",
                            "Teléfono incorrecto",
                            "El teléfono " + telefono.getText() + " no es válido. \nIntente nuevamente (Ejemplo: (32)2345678).");
                    telefono.setText("");
                }
            }
        });
        celular.focusedProperty().addListener((ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) -> {
            if (!newPropertyValue && !celular.getText().isEmpty()) {
                if (!ValidatorUtils.celular(celular.getText())) {
                    DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
                            "Error",
                            "Celular incorrecto",
                            "El celular " + celular.getText() + " no es válido. \nIntente nuevamente (Ejemplo: (9)65478912).");
                    celular.setText("");
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
    private void handleCliente(ActionEvent event) {
        mainProject.showClientes();
    }

    @FXML
    private void handleSaveClient(ActionEvent ae) {
        if (nombres.getText() != null && !nombres.getText().isEmpty()) {
            cliente.setNombres(nombres.getText().trim());
            if (paterno.getText() != null && !paterno.getText().isEmpty()) {
                cliente.setPaterno(paterno.getText().trim());
                if (materno.getText() != null && !materno.getText().isEmpty()) {
                    cliente.setMaterno(materno.getText().trim());
                    if (rut.getText() != null && !rut.getText().isEmpty()) {
                        cliente.setRutCliente(rut.getText().trim());
                        if (email.getText() != null && !email.getText().isEmpty()) {
                            cliente.setEmail(email.getText().trim());
                            if (telefono.getText() != null && !telefono.getText().isEmpty()) {
                                cliente.setTelFijo(telefono.getText().trim());
                                if (celular.getText() != null && !celular.getText().isEmpty()) {
                                    cliente.setCelular(celular.getText().trim());
                                    try {
                                        cliente.setActivo(String.valueOf(true));
                                        clienteManager.insertCliente(cliente);
                                        DialogUtils.showSimpleDialog(DialogUtils.INFORMATION_DIALOG,
                                                "Éxito",
                                                "Ingreso OK",
                                                "La información fue ingresada correctamente.");
                                        mainProject.showClientes();

                                    } catch (DataException ex) {
                                        DialogUtils.showExceptionDialog(
                                                "Error",
                                                "Se ha producido un error inesperado",
                                                "El detalle de la excepción se presenta \na continuación",
                                                new DataException(ex));
                                    } catch (Exception ex) {
                                        DialogUtils.showExceptionDialog(
                                                "Error",
                                                "Se ha producido un error",
                                                "El El rut ya se encuentra registrado en la base de datos",
                                                null);//new DataException(ex));
                                    }
                                } else {
                                    DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
                                            "Error",
                                            "Celular",
                                            "El registro no puede estar vacío. \nIntente nuevamente.");
                                }
                            } else {
                                DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
                                        "Error",
                                        "Teléfono fijo",
                                        "El registro no puede estar vacío. \nIntente nuevamente.");
                            }
                        } else {
                            DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
                                    "Error",
                                    "Email",
                                    "El registro no puede estar vacío. \nIntente nuevamente.");
                        }
                    } else {
                        DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
                                "Error",
                                "Rut",
                                "El registro no puede estar vacío. \nIntente nuevamente.");
                    }
                } else {
                    DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
                            "Error",
                            "Apellido Materno",
                            "El registro no puede estar vacío. \nIntente nuevamente.");

                }
            } else {
                DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
                        "Error",
                        "Apellido Paterno",
                        "El registro no puede estar vacío. \nIntente nuevamente.");
            }
        } else {
            DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
                    "Error",
                    "Nombres",
                    "El registro no puede estar vacío. \nIntente nuevamente.");
        }
    }
}
