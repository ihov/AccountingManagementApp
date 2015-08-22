package cl.ihov.project.view.components;

import cl.ihov.project.common.vo.Empresa;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PaymentViewComponent {

    @FXML
    protected DatePicker fecha;

    @FXML
    protected TextField monto;

    @FXML
    protected TextField nrecibo;

    @FXML
    protected TextField observacion;

    @FXML
    protected TableView<Empresa> dataEmpresa;
    
    @FXML
    protected ComboBox <String> annioAbono;
    
    @FXML
    protected ComboBox <String> mesAbono;
    
    @FXML
    protected Button goHomeP;
}
