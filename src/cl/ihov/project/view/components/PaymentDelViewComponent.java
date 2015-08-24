package cl.ihov.project.view.components;

import cl.ihov.project.common.vo.Abono;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PaymentDelViewComponent {

    @FXML
    protected TableView<Abono> dataAbono;
    
    @FXML
    protected Button goHomeP;
    
    @FXML
    protected TextField rutBusca;
    
    @FXML
    protected TextField registroElimina;
   
}
