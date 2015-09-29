package cl.ihov.project.view.components;

import cl.ihov.project.common.vo.Deudor;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

public class ListDebtorsViewComponent {

    @FXML
    protected TableView<Deudor> dataEmpresa;

    @FXML
    protected ComboBox<String> annioAbono;

    @FXML
    protected ComboBox<String> mesAbono;
    
    @FXML
    protected Button busca;
    
    
}
