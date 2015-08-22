package cl.ihov.project.view.controllers;

import cl.ihov.project.common.exception.DataException;
import cl.ihov.project.common.initial.MainProject;
import cl.ihov.project.common.vo.Abono;
import cl.ihov.project.common.vo.Cliente;
import cl.ihov.project.common.vo.Empresa;
import cl.ihov.project.common.vo.UserOnline;
import cl.ihov.project.managers.abono.AbonoManager;
import cl.ihov.project.managers.abono.AbonoManagerImpl;
import cl.ihov.project.managers.auth.AuthManager;
import cl.ihov.project.managers.auth.AuthManagerImpl;
import cl.ihov.project.managers.cliente.ClienteManager;
import cl.ihov.project.managers.cliente.ClienteManagerImpl;
import cl.ihov.project.managers.empresa.EmpresaManager;
import cl.ihov.project.managers.empresa.EmpresaManagerImpl;
import cl.ihov.project.view.components.LoginViewComponent;
import cl.ihov.project.view.utils.DialogUtils;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class LoginController extends LoginViewComponent implements Initializable {

    private AuthManager authManager;
    private AbonoManager abonoManager;
    private EmpresaManager empresaManager;
    private ClienteManager clienteManager;
    private UserOnline userOnline;
    private MainProject mainProject;
    private Empresa empresa;
    private Abono abono;
    private Cliente cliente;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        authManager = new AuthManagerImpl();
        abonoManager = new AbonoManagerImpl();
        empresaManager = new EmpresaManagerImpl();
        clienteManager = new ClienteManagerImpl();
    }

    public void setMainProject(MainProject mainProject) {
        this.mainProject = mainProject;
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        userOnline = new UserOnline();
        userOnline.setUserId(username.getText());
        userOnline.setPassword(password.getText());
        
//        cliente=new Cliente();
//        cliente.setRutCliente("12345678-9");
//        cliente.setCelular("56983701764");
//        cliente.setEmail("update.correo@asd.cl");
//        cliente.setMaterno("updateMaterno");
//        cliente.setNombres("update nombres");
//        cliente.setPaterno("updatePaterno");
//        cliente.setTelFijo("0322986565");
//        
//        empresa = new Empresa();
//        empresa.setRutCliente("12345678-9");
//        empresa.setRutEmpresa("76224125-6");
//        empresa.setIdContabilidad(1);
//        empresa.setIdBanco(8);
//        empresa.setIdCuenta(3);  
//        //empresa.setActivo(1);
//        empresa.setClaveOtro("");
//        empresa.setClavePrevired("");
//        empresa.setClaveSii("");
//        empresa.setComuna("");
//        empresa.setDireccion("");
//        empresa.setDireccion("");
//        empresa.setGiroComercial("");
//        empresa.setNroCuenta("");
//        empresa.setValorMensual(0);
//
//        Calendar cal = Calendar.getInstance();
//        Date fecha = cal.getTime();
//        abono = new Abono();
//        abono.setRutCliente("12345678-9");
//        abono.setRutEmpresa("76224125-6");
//        abono.setIdAbono(129);
//        abono.setMonto(100);
//        abono.setFecha(new java.sql.Date(fecha.getTime()));
//        abono.setObservacion("update observacion");

        if (auth()) {
            mainProject.showMainMenu();
        } else {
            showAuthError();
        }
    }

    private boolean auth() {
        try {
            //clienteManager.deleteCliente(cliente);
            //System.out.println("delete cliente");

            return authManager.auth(userOnline);
            //return true;
        } catch (DataException ex) {
            DialogUtils.showExceptionDialog(
                    "Error",
                    "Se ha producido un error inesperado",
                    "El detalle de la excepción se presenta \na continuación",
                    ex);
            return false;
        }
    }

    private void showAuthError() {
        DialogUtils.showSimpleDialog(DialogUtils.ERROR_DIALOG,
                "Error",
                "Usuario o clave incorrecto",
                "La información ingresada no es correcta. \nIntente nuevamente.");
    }
}
