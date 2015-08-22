package cl.ihov.project.view.components;

import cl.ihov.project.common.vo.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

/**
 *
 * @author SVALDIVIA
 */
public class EnterprisesViewComponent {
    
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
    protected TableView<Cliente> dataCliente;
    
    @FXML
    protected  TextField razonSocial;
}
