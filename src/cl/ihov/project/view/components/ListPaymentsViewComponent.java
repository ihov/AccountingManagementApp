package cl.ihov.project.view.components;

import cl.ihov.project.common.vo.Abono;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;

public class ListPaymentsViewComponent {

    @FXML
    protected TableView<Abono> dataAbono;

    @FXML
    protected DatePicker fechaInicio;

    @FXML
    protected DatePicker fechaTermino;
    
    @FXML
    protected Button buscaAbonos;
}
