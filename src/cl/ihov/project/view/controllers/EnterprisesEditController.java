package cl.ihov.project.view.controllers;

import cl.ihov.project.common.exception.DataException;
import cl.ihov.project.common.initial.MainProject;
import cl.ihov.project.common.utils.BaseResources;
import cl.ihov.project.common.vo.Banco;
import cl.ihov.project.common.vo.Contabilidad;
import cl.ihov.project.common.vo.Cuenta;
import cl.ihov.project.common.vo.Empresa;
import cl.ihov.project.managers.banco.BancoManager;
import cl.ihov.project.managers.banco.BancoManagerImpl;
import cl.ihov.project.managers.contabilidad.ContabilidadManager;
import cl.ihov.project.managers.contabilidad.ContabilidadManagerImpl;
import cl.ihov.project.managers.cuenta.CuentaManager;
import cl.ihov.project.managers.cuenta.CuentaManagerImpl;
import cl.ihov.project.managers.empresa.EmpresaManager;
import cl.ihov.project.managers.empresa.EmpresaManagerImpl;
import cl.ihov.project.view.components.EnterprisesEditViewComponent;
import cl.ihov.project.view.utils.DialogUtils;
import cl.ihov.project.view.utils.ValidatorUtils;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 *
 * @author SVALDIVIA
 */
public class EnterprisesEditController extends EnterprisesEditViewComponent implements Initializable {

    private MainProject mainProject;
    private EmpresaManager empresaManger;
    private Empresa empresaCliente;
    private BancoManager bancoManager;
    private CuentaManager tipoCuenta;
    private ContabilidadManager conta;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idBanco.setPromptText(BaseResources.getValue("sys_config", "promptComboBanco"));
        idCuenta.setPromptText(BaseResources.getValue("sys_config", "promptComboTpoCuenta"));
        idContabilidad.setPromptText(BaseResources.getValue("sys_config", "promptComboTpoConta"));
        empresaManger = new EmpresaManagerImpl();
        empresaCliente = new Empresa();
        bancoManager = new BancoManagerImpl();
        tipoCuenta = new CuentaManagerImpl();
        conta = new ContabilidadManagerImpl();
        loadComboBanks();
        loadComboTpoCuenta();
        loadComboTpoContabilidad();
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
        rutBusca.focusedProperty().addListener((ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) -> {
            if (!newPropertyValue && !rutBusca.getText().isEmpty()) {
                if (!ValidatorUtils.rut(rutBusca.getText())) {
                    DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
                            "Error",
                            "Rut incorrecto",
                            "El rut " + rutBusca.getText() + " no es válido. \nIntente nuevamente (Ejemplo: 12345678-9).");
                    rutBusca.setText("");
                    limpiaForm();
                }
            }
        });
    }

    public void setMainProject(MainProject mainProject) {
        this.mainProject = mainProject;
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

    @FXML
    private void handleGoHome(ActionEvent event) {
        mainProject.showMainMenu();
    }

    @FXML
    private void handleEmpresas(ActionEvent event) {
        mainProject.showEmpresas();
    }

    @FXML
    private void handleEditEnterprises(ActionEvent ae) {

        if (rutEmpresa.getText() != null && !rutEmpresa.getText().isEmpty()) {
            empresaCliente.setRutAnterior(rutBusca.getText().trim());
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
                                                                empresaCliente.setActivo(String.valueOf(activo.isSelected()));
                                                                empresaManger.updateEmpresa(empresaCliente);
                                                                DialogUtils.showSimpleDialog(DialogUtils.INFORMATION_DIALOG,
                                                                        "Éxito",
                                                                        "Edición OK",
                                                                        "La información fue modificada correctamente.");
                                                                mainProject.showEmpresas();
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
    }

    @FXML
    private void handleFindEnterprise() {
        if (!rutBusca.getText().trim().isEmpty()) {
            empresaCliente.setRutEmpresa(rutBusca.getText().trim());
            try {
                Empresa empresaSerched = new Empresa();
                empresaSerched = empresaManger.findEmpresa(empresaCliente);

                if (empresaSerched != null) {
                    rutEmpresa.setText(empresaSerched.getRutEmpresa());
                    giroComercial.setText(empresaSerched.getGiroComercial());
                    comuna.setText(empresaSerched.getComuna());
                    direccion.setText(empresaSerched.getDireccion());
                    idBanco.getSelectionModel().select(Integer.valueOf(empresaSerched.getIdBanco()));
                    idCuenta.getSelectionModel().select(Integer.valueOf(empresaSerched.getIdCuenta()));
                    nroCuenta.setText(empresaSerched.getNroCuenta());
                    claveSii.setText(empresaSerched.getClaveSii());
                    clavePrevired.setText(empresaSerched.getClavePrevired());
                    claveOtro.setText(empresaSerched.getClaveOtro());
                    idContabilidad.getSelectionModel().select(Integer.valueOf(empresaSerched.getIdContabilidad()));
                    valorMensual.setText(String.valueOf(empresaSerched.getValorMensual()));
                    activo.setSelected(Boolean.parseBoolean(empresaSerched.getActivo()));
                    razonSocial.setText(empresaSerched.getRazonSocial());
                } else {
                    DialogUtils.showSimpleDialog(DialogUtils.INFORMATION_DIALOG,
                            "Información",
                            "Rut No encontrado",
                            "El rut del cliente no se encuentra en la base de datos.");
                    limpiaForm();
                }
            } catch (DataException ex) {
                DialogUtils.showExceptionDialog(
                        "Error",
                        "Se ha producido un error inesperado",
                        "El detalle de la excepción se presenta \na continuación",
                        new DataException(ex));
            }
        } else {
            DialogUtils.showSimpleDialog(DialogUtils.INFORMATION_DIALOG,
                    "Información",
                    "RUT",
                    "Debe ingresar un rut para realizar la búsqueda.");
            limpiaForm();
        }
    }

    private void limpiaForm() {
        rutEmpresa.setText("");
        giroComercial.setText("");
        comuna.setText("");
        direccion.setText("");
        idBanco.getSelectionModel().select(Integer.valueOf("0"));
        idCuenta.getSelectionModel().select(Integer.valueOf("0"));
        nroCuenta.setText("");
        claveSii.setText("");
        clavePrevired.setText("");
        claveOtro.setText("");
        idContabilidad.getSelectionModel().select(Integer.valueOf("0"));
        valorMensual.setText("");
        activo.setSelected(false);
        razonSocial.setText("");
    }
}
