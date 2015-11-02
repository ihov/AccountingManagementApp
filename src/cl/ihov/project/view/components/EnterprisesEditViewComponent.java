package cl.ihov.project.view.components;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;

public class EnterprisesEditViewComponent {


    @FXML
    protected TextField rutEmpresa;

    @FXML
    protected TextField giroComercial;

    @FXML
    protected TextField direccion;

    @FXML
    protected TextField comuna;

    @FXML
    protected TextField nroCuenta;

    @FXML
    protected TextField claveSii;

    @FXML
    protected TextField clavePrevired;

    @FXML
    protected TextField claveOtro;

    @FXML
    protected TextField valorMensual;
    
    @FXML
    protected ComboBox <String> idContabilidad;
    
    @FXML
    protected ComboBox <String> idBanco;
    
    @FXML
    protected ComboBox <String> idCuenta;

    @FXML
    protected TextField rutBusca;
    
    @FXML
    protected CheckBox activo;
    
    @FXML
    protected TextField razonSocial;
}
