package cl.ihov.project.view.components;

import cl.ihov.project.common.vo.Deudor;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableView;

public class ListDebtorsViewComponent {

    @FXML
    protected TableView<Deudor> dataEmpresa;

//    @FXML
//    protected ComboBox<String> annioAbono;
//
//    @FXML
//    protected ComboBox<String> mesAbono;
    
    @FXML
    protected Button buscar;
    
    @FXML
    protected Button goReportes;
    
    @FXML
    protected Button deudoresAll;

    @FXML
    protected Button sendMail;
    
    @FXML
    protected ProgressBar progreso;
    
    @FXML
    protected Label progresoLabel;
    
    @FXML
    protected DatePicker fechaIni;
    
    @FXML
    protected DatePicker fechaTer;
    
       
}
