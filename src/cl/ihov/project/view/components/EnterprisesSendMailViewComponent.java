/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.ihov.project.view.components;

import cl.ihov.project.common.vo.Empresa;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableView;

public class EnterprisesSendMailViewComponent {

    @FXML
    protected TableView<Empresa> dataEmpresas;

    @FXML
    protected Button goHome;

    @FXML
    protected Button sendMail;
    
    @FXML
    protected ProgressBar progreso;
    
    @FXML
    protected Label progresoLabel;
}
