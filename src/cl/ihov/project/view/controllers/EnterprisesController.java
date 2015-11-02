package cl.ihov.project.view.controllers;

import cl.ihov.project.common.exception.DataException;
import cl.ihov.project.common.initial.MainProject;
import cl.ihov.project.common.utils.BaseResources;
import cl.ihov.project.common.vo.Banco;
import cl.ihov.project.common.vo.Cliente;
import cl.ihov.project.common.vo.Contabilidad;
import cl.ihov.project.common.vo.Cuenta;
import cl.ihov.project.common.vo.Empresa;
import cl.ihov.project.managers.banco.BancoManager;
import cl.ihov.project.managers.banco.BancoManagerImpl;
import cl.ihov.project.managers.cliente.ClienteManager;
import cl.ihov.project.managers.cliente.ClienteManagerImpl;
import cl.ihov.project.managers.contabilidad.ContabilidadManager;
import cl.ihov.project.managers.contabilidad.ContabilidadManagerImpl;
import cl.ihov.project.managers.cuenta.CuentaManager;
import cl.ihov.project.managers.cuenta.CuentaManagerImpl;
import cl.ihov.project.managers.empresa.EmpresaManager;
import cl.ihov.project.managers.empresa.EmpresaManagerImpl;
import cl.ihov.project.view.components.EnterprisesViewComponent;
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

public class EnterprisesController extends EnterprisesViewComponent implements Initializable {

    private MainProject mainProject;
    private BancoManager bancoManager;
    private CuentaManager tipoCuenta;
    private ContabilidadManager conta;
    private ClienteManager cliente;
    private ObservableList<Cliente> clientes;
    private EmpresaManager empresa;
    private Empresa empresaCliente;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idBanco.setPromptText(BaseResources.getValue("sys_config", "promptComboBanco"));
        idCuenta.setPromptText(BaseResources.getValue("sys_config", "promptComboTpoCuenta"));
        idContabilidad.setPromptText(BaseResources.getValue("sys_config", "promptComboTpoConta"));
        bancoManager = new BancoManagerImpl();
        tipoCuenta = new CuentaManagerImpl();
        conta = new ContabilidadManagerImpl();
        cliente = new ClienteManagerImpl();
        empresa = new EmpresaManagerImpl();
        empresaCliente = new Empresa();

        loadComboBanks();
        loadComboTpoCuenta();
        loadComboTpoContabilidad();
        loadDataCliente();

        rutEmpresa.focusedProperty().addListener((ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) -> {
            if (!newPropertyValue && !rutEmpresa.getText().isEmpty()) {
                if (!ValidatorUtils.rut(rutEmpresa.getText())) {
                    DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
                            "Error",
                            "Rut de la empresa incorrecto",
                            "El rut " + rutEmpresa.getText() + " no es válido. \nIntente nuevamente (Ejemplo: 12345678-9).");
                    rutEmpresa.setText("");
                }
            }
        });
        nroCuenta.focusedProperty().addListener((ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) -> {
            if (!newPropertyValue && !nroCuenta.getText().isEmpty()) {
                if (!ValidatorUtils.nroCuenta(nroCuenta.getText())) {
                    DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
                            "Error",
                            "Número de cuenta incorrecto",
                            "El N° de cuenta " + nroCuenta.getText() + " no es válido. \nIntente nuevamente (Ejemplo: 0123456789).");
                    nroCuenta.setText("");
                }
            }
        });
        valorMensual.focusedProperty().addListener((ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) -> {
            if (!newPropertyValue && !valorMensual.getText().isEmpty()) {
                if (!ValidatorUtils.monto(valorMensual.getText())) {
                    DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
                            "Error",
                            "Valor menusal incorrecto",
                            "El valor " + valorMensual.getText() + " no es válido. \nIntente nuevamente (Ejemplo: 10000).");
                    valorMensual.setText("");
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
    private void handleEmpresas(ActionEvent event) {
        mainProject.showEmpresas();
    }

    private void loadComboBanks() {
        try {
            List<Banco> lista = bancoManager.findBancos();
            if (lista != null) {
                idBanco.getItems().add(0, BaseResources.getValue("sys_config", "promptComboBanco"));
                lista.stream().forEach((banco) -> {
                    idBanco.getItems().add(Integer.valueOf(banco.getIdBanco()), banco.getNombre());
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

    private void loadComboTpoCuenta() {
        try {
            List<Cuenta> lista = tipoCuenta.findCuentas();
            if (lista != null) {
                idCuenta.getItems().add(0, BaseResources.getValue("sys_config", "promptComboTpoCuenta"));
                lista.stream().forEach((cuenta) -> {
                    idCuenta.getItems().add(Integer.valueOf(cuenta.getIdCuenta()), cuenta.getDescripcion());
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

    private void loadComboTpoContabilidad() {
        try {
            List<Contabilidad> lista = conta.findContabilidad();
            if (lista != null) {
                idContabilidad.getItems().add(0, BaseResources.getValue("sys_config", "promptComboTpoConta"));
                lista.stream().forEach((conta) -> {
                    idContabilidad.getItems().add(Integer.valueOf(conta.getInContabilidad()), conta.getDescripcion());
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

    private void loadDataCliente() {
        try {
            List<Cliente> lista = cliente.findAllClientesActivo();
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
                                    c.getActivo()));
                });

                TableColumn rutClienteCol = new TableColumn("Rut Cliente");
                rutClienteCol.setMinWidth(100);
                rutClienteCol.setCellValueFactory(
                        new PropertyValueFactory<>("rutCliente")
                );

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

                dataCliente.setItems(clientes);
                dataCliente.getColumns().addAll(
                        rutClienteCol,
                        nombresCol,
                        paternoCol,
                        maternoCol,
                        emailCol,
                        telFijoCol,
                        celularCol);
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
    private void handleSaveEnterprises(ActionEvent ae) {
        if (dataCliente.getSelectionModel().getSelectedItem() != null) {
            Cliente c = dataCliente.getSelectionModel().getSelectedItem();
            empresaCliente.setRutCliente(c.getRutCliente().trim());
            if (rutEmpresa.getText() != null && !rutEmpresa.getText().isEmpty()) {
                empresaCliente.setRutEmpresa(rutEmpresa.getText().trim());
                if (razonSocial.getText() != null && !razonSocial.getText().isEmpty()) {
                    empresaCliente.setRazonSocial(razonSocial.getText().trim());
                    if (giroComercial.getText() != null && !giroComercial.getText().isEmpty()) {
                        empresaCliente.setGiroComercial(giroComercial.getText().trim());
                        if (comuna.getText() != null && !comuna.getText().isEmpty()) {
                            empresaCliente.setComuna(comuna.getText().trim());
                            if (direccion.getText() != null && !direccion.getText().isEmpty()) {
                                empresaCliente.setDireccion(direccion.getText().trim());
                                if (idBanco.getValue() != null && !idBanco.getValue().equals("0")) {
                                    empresaCliente.setIdBanco(String.valueOf(idBanco.getSelectionModel().getSelectedIndex()));
                                    if (idCuenta.getValue() != null && idCuenta.getSelectionModel().getSelectedIndex() != 0) {
                                        empresaCliente.setIdCuenta(String.valueOf(idCuenta.getSelectionModel().getSelectedIndex()));
                                        if (nroCuenta.getText() != null && !nroCuenta.getText().isEmpty()) {
                                            empresaCliente.setNroCuenta(nroCuenta.getText().trim());
                                            if (claveSii.getText() != null && !claveSii.getText().isEmpty()) {
                                                empresaCliente.setClaveSii(claveSii.getText().trim());
                                                if (clavePrevired.getText() != null && !clavePrevired.getText().isEmpty()) {
                                                    empresaCliente.setClavePrevired(clavePrevired.getText().trim());
                                                    if (claveOtro.getText() != null && !claveOtro.getText().isEmpty()) {
                                                        empresaCliente.setClaveOtro(claveOtro.getText().trim());
                                                        if (idContabilidad.getValue() != null && idContabilidad.getSelectionModel().getSelectedIndex() != 0) {
                                                            empresaCliente.setIdContabilidad(String.valueOf(idContabilidad.getSelectionModel().getSelectedIndex()));
                                                            if (valorMensual.getText() != null && !valorMensual.getText().isEmpty()) {
                                                                empresaCliente.setValorMensual(valorMensual.getText().trim());
                                                                try {
                                                                    empresaCliente.setActivo(String.valueOf(true));
                                                                    empresa.insertEmpresa(empresaCliente);
                                                                    DialogUtils.showSimpleDialog(DialogUtils.INFORMATION_DIALOG,
                                                                            "Éxito",
                                                                            "Ingreso OK",
                                                                            "La información fue ingresada correctamente.");
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
                                                                        "Valor mensual",
                                                                        "El registro no puede estar vacío. \nIntente nuevamente.");
                                                            }
                                                        } else {
                                                            DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
                                                                    "Error",
                                                                    "Tipo contabilidad",
                                                                    "El registro no puede estar vacío. \nIntente nuevamente.");
                                                        }
                                                    } else {
                                                        DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
                                                                "Error",
                                                                "Otra clave",
                                                                "El registro no puede estar vacío. \nIntente nuevamente.");
                                                    }
                                                } else {
                                                    DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
                                                            "Error",
                                                            "Clave previred",
                                                            "El registro no puede estar vacío. \nIntente nuevamente.");
                                                }
                                            } else {
                                                DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
                                                        "Error",
                                                        "Clave S.I.I.",
                                                        "El registro no puede estar vacío. \nIntente nuevamente.");
                                            }
                                        } else {
                                            DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
                                                    "Error",
                                                    "Número de cuenta",
                                                    "El registro no puede estar vacío. \nIntente nuevamente.");
                                        }
                                    } else {
                                        DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
                                                "Error",
                                                "Tipo cuenta",
                                                "Debe seleccionar un registro. \nIntente nuevamente.");
                                    }
                                } else {
                                    DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
                                            "Error",
                                            "Banco",
                                            "Debe seleccionar un registro. \nIntente nuevamente.");
                                }
                            } else {
                                DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
                                        "Error",
                                        "Dirección",
                                        "El registro no puede estar vacío. \nIntente nuevamente.");
                            }
                        } else {
                            DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
                                    "Error",
                                    "Comuna",
                                    "El registro no puede estar vacío. \nIntente nuevamente.");
                        }
                    } else {
                        DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
                                "Error",
                                "Giro comercial",
                                "El registro no puede estar vacío. \nIntente nuevamente.");
                    }
                } else {
                    DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
                            "Error",
                            "Razón Social",
                            "El registro no puede estar vacío. \nIntente nuevamente.");
                }
            } else {
                DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
                        "Error",
                        "Rut Empresa",
                        "El registro no puede estar vacío. \nIntente nuevamente.");
            }
        } else {
            DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
                    "Error",
                    "Cliente",
                    "El registro cliente no puede estar vacío. \nIntente seleccionando un elemento de la lista.");
        }
    }
}
