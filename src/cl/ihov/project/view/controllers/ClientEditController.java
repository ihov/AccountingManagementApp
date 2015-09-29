package cl.ihov.project.view.controllers;

import cl.ihov.project.common.exception.DataException;
import cl.ihov.project.common.initial.MainProject;
import cl.ihov.project.common.vo.Cliente;
import cl.ihov.project.managers.cliente.ClienteManager;
import cl.ihov.project.managers.cliente.ClienteManagerImpl;
import cl.ihov.project.view.components.ClientEditViewComponent;
import cl.ihov.project.view.utils.DialogUtils;
import cl.ihov.project.view.utils.ValidatorUtils;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class ClientEditController extends ClientEditViewComponent implements Initializable {

    private ClienteManager clienteManager;
    private MainProject mainProject;
    private Cliente cliente;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cliente = new Cliente();
        clienteManager = new ClienteManagerImpl();
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
    
    @FXML
    private void handleCliente(ActionEvent event) {
        mainProject.showClientes();
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
    private void handleFindClient(ActionEvent ae) {
        cliente.setRutCliente(rutBusca.getText().trim());
        try {
            Cliente clientSerched = new Cliente();
            clientSerched = clienteManager.findCliente(cliente);
            if (clientSerched != null) {
                email.setText(clientSerched.getEmail());
                nombres.setText(clientSerched.getNombres());
                paterno.setText(clientSerched.getPaterno());
                materno.setText(clientSerched.getMaterno());
                rut.setText(clientSerched.getRutCliente());
                telefono.setText(clientSerched.getTelFijo());
                celular.setText(clientSerched.getCelular());
                //activo.setSelected(Boolean.parseBoolean(clientSerched.getActivo()));
               cliente.setActivo(String.valueOf(true));
            } else {
                DialogUtils.showSimpleDialog(DialogUtils.INFORMATION_DIALOG,
                        "Información",
                        "Rut No encontrado",
                        "El rut del cliente no se encuentra en la base de datos.");
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
    private void handleEditClient(ActionEvent ae) {
        if (nombres.getText() != null && !nombres.getText().isEmpty()) {
            cliente.setNombres(nombres.getText().trim());
            if (paterno.getText() != null && !paterno.getText().isEmpty()) {
                cliente.setPaterno(paterno.getText().trim());
                if (materno.getText() != null && !materno.getText().isEmpty()) {
                    cliente.setMaterno(materno.getText().trim());
                    if (rut.getText() != null && !rut.getText().isEmpty()) {
                        cliente.setRutCliente(rut.getText().trim());
                        cliente.setRutAnterior(rutBusca.getText().trim());
                        if (email.getText() != null && !email.getText().isEmpty()) {
                            cliente.setEmail(email.getText().trim());
                            if (telefono.getText() != null && !telefono.getText().isEmpty()) {
                                cliente.setTelFijo(telefono.getText().trim());
                                if (celular.getText() != null && !celular.getText().isEmpty()) {
                                    cliente.setCelular(celular.getText().trim());
                                    try {
                                        //cliente.setActivo(String.valueOf(activo.isSelected()));
                                        cliente.setActivo(String.valueOf(true));
                                        clienteManager.updateCliente(cliente);
                                        DialogUtils.showSimpleDialog(DialogUtils.INFORMATION_DIALOG,
                                                "Éxito",
                                                "Edición OK",
                                                "La información fue modificada correctamente.");
                                        mainProject.showMainMenu();
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
