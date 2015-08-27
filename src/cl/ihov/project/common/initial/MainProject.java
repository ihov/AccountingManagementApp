package cl.ihov.project.common.initial;

import cl.ihov.project.common.exception.DataException;
import cl.ihov.project.view.controllers.AbonosController;
import cl.ihov.project.view.controllers.ClientController;
import cl.ihov.project.view.controllers.ClientEditController;
import cl.ihov.project.view.controllers.ClientesController;
import cl.ihov.project.view.controllers.EmpresasController;
import cl.ihov.project.view.controllers.EnterprisesController;
import cl.ihov.project.view.controllers.EnterprisesEditController;
import cl.ihov.project.view.controllers.EnterprisesSendMailController;
import cl.ihov.project.view.controllers.ListClientsController;
import cl.ihov.project.view.controllers.ListDebtorsController;
import cl.ihov.project.view.controllers.ListEmpresasController;
import cl.ihov.project.view.controllers.ListPaymentsController;
import cl.ihov.project.view.controllers.LoginController;
import cl.ihov.project.view.controllers.MainController;
import cl.ihov.project.view.controllers.MainMenuController;
import cl.ihov.project.view.controllers.PaymentController;
import cl.ihov.project.view.controllers.PaymentDelController;
import cl.ihov.project.view.controllers.PaymentEditController;
import cl.ihov.project.view.controllers.ReportController;
import cl.ihov.project.view.utils.DialogUtils;
import cl.ihov.project.view.utils.ViewUtils;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainProject extends Application {

    private Stage primaryStage;
    private BorderPane mainView;
    private Screen screen;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        initMainView();
        showLoginView();
    }

    private void initMainView() {
        try {
            FXMLLoader loader = ViewUtils.getFXMLLoader("MainView");
            mainView = (BorderPane) loader.load();

            Scene scene = new Scene(mainView);
            primaryStage.setScene(scene);

            MainController controller = loader.getController();
            controller.setMainProject(this);

            primaryStage.show();
        } catch (IOException e) {
            DialogUtils.showExceptionDialog(
                    "Error",
                    "Se ha producido un error inesperado",
                    "El detalle de la excepción se presenta \na continuación",
                    new DataException(e));
        }
    }

    private void showLoginView() {
        try {
            FXMLLoader loader = ViewUtils.getFXMLLoader("LoginView");
            AnchorPane loginOverview = (AnchorPane) loader.load();

            mainView.setCenter(loginOverview);

            LoginController loginController = loader.getController();
            loginController.setMainProject(this);

            doFeaturesStage("Ingreso", 250, 250);
        } catch (IOException e) {
            DialogUtils.showExceptionDialog(
                    "Error",
                    "Se ha producido un error inesperado",
                    "El detalle de la excepción se presenta \na continuación",
                    new DataException(e));
        }
    }

    public void showMainMenu() {
        try {
            FXMLLoader loader = ViewUtils.getFXMLLoader("MainMenuView");
            AnchorPane mainMenuOverview = (AnchorPane) loader.load();

            mainView.setCenter(mainMenuOverview);

            MainMenuController mainMenuController = loader.getController();
            mainMenuController.setMainProject(this);

            doFeaturesStage("Menú principal", 420, 600);
        } catch (IOException e) {
            DialogUtils.showExceptionDialog(
                    "Error",
                    "Se ha producido un error inesperado",
                    "El detalle de la excepción se presenta \na continuación",
                    new DataException(e));
        }
    }

    public void showClientView() {
        try {
            FXMLLoader loader = ViewUtils.getFXMLLoader("ClientsView");
            AnchorPane clientOverview = (AnchorPane) loader.load();

            mainView.setCenter(clientOverview);

            ClientController clientController = loader.getController();
            clientController.setMainProject(this);

            doFeaturesStage("Gestión de Clientes", 360, 650);
        } catch (IOException e) {
            DialogUtils.showExceptionDialog(
                    "Error",
                    "Se ha producido un error inesperado",
                    "El detalle de la excepción se presenta \na continuación",
                    new DataException(e));
        }
    }

    public void showClientes() {
        try {
            FXMLLoader loader = ViewUtils.getFXMLLoader("Clientes");
            AnchorPane clienteOverview = (AnchorPane) loader.load();

            mainView.setCenter(clienteOverview);

            ClientesController clienteController = loader.getController();
            clienteController.setMainProject(this);

            doFeaturesStage("Gestión de Clientes", 360, 600);
        } catch (IOException e) {
            DialogUtils.showExceptionDialog(
                    "Error",
                    "Se ha producido un error inesperado",
                    "El detalle de la excepción se presenta \na continuación",
                    new DataException(e));
        }
    }

    public void showEmpresas() {
        try {
            FXMLLoader loader = ViewUtils.getFXMLLoader("Empresas");
            AnchorPane empresasOverview = (AnchorPane) loader.load();

            mainView.setCenter(empresasOverview);

            EmpresasController empresasController = loader.getController();
            empresasController.setMainProject(this);

            doFeaturesStage("Gestión de Empresas", 360, 600);
        } catch (IOException e) {
            DialogUtils.showExceptionDialog(
                    "Error",
                    "Se ha producido un error inesperado",
                    "El detalle de la excepción se presenta \na continuación",
                    new DataException(e));
        }
    }

    public void showAbonos() {
        try {
            FXMLLoader loader = ViewUtils.getFXMLLoader("Abonos");
            AnchorPane abonosOverview = (AnchorPane) loader.load();

            mainView.setCenter(abonosOverview);

            AbonosController abonosController = loader.getController();
            abonosController.setMainProject(this);

            doFeaturesStage("Gestión de Abonos", 360, 600);
        } catch (IOException e) {
            DialogUtils.showExceptionDialog(
                    "Error",
                    "Se ha producido un error inesperado",
                    "El detalle de la excepción se presenta \na continuación",
                    new DataException(e));
        }
    }

    public void showPaymentView() {
        try {
            FXMLLoader loader = ViewUtils.getFXMLLoader("PaymentView");
            AnchorPane paymentOverview = (AnchorPane) loader.load();

            mainView.setCenter(paymentOverview);

            PaymentController paymentController = loader.getController();
            paymentController.setMainProject(this);

            doFeaturesStage("Gestión de Abonos", 620, 850);
        } catch (IOException e) {
            DialogUtils.showExceptionDialog(
                    "Error",
                    "Se ha producido un error inesperado",
                    "El detalle de la excepción se presenta \na continuación",
                    new DataException(e));
        }
    }

    public void showPaymentEditView() {
        try {
            FXMLLoader loader = ViewUtils.getFXMLLoader("PaymentEditView");
            AnchorPane paymentOverview = (AnchorPane) loader.load();

            mainView.setCenter(paymentOverview);

            PaymentEditController paymentController = loader.getController();
            paymentController.setMainProject(this);

            doFeaturesStage("Gestión de Abonos", 620, 850);
        } catch (IOException e) {
            DialogUtils.showExceptionDialog(
                    "Error",
                    "Se ha producido un error inesperado",
                    "El detalle de la excepción se presenta \na continuación",
                    new DataException(e));
        }
    }
    public void showPaymentDelView() {
        try {
            FXMLLoader loader = ViewUtils.getFXMLLoader("PaymentDelView");
            AnchorPane paymentDelOverview = (AnchorPane) loader.load();

            mainView.setCenter(paymentDelOverview);

            PaymentDelController paymentDelController = loader.getController();
            paymentDelController.setMainProject(this);

            doFeaturesStage("Gestión de Abonos", 420, 850);
        } catch (IOException e) {
            DialogUtils.showExceptionDialog(
                    "Error",
                    "Se ha producido un error inesperado",
                    "El detalle de la excepción se presenta \na continuación",
                    new DataException(e));
        }
    }

    public void showReportView() {
        try {
            FXMLLoader loader = ViewUtils.getFXMLLoader("ReportView");
            AnchorPane reportOverview = (AnchorPane) loader.load();

            mainView.setCenter(reportOverview);

            ReportController reportController = loader.getController();
            reportController.setMainProject(this);

            doFeaturesStage("Gestión de Reportes", 420, 660);
        } catch (IOException e) {
            DialogUtils.showExceptionDialog(
                    "Error",
                    "Se ha producido un error inesperado",
                    "El detalle de la excepción se presenta \na continuación",
                    new DataException(e));
        }
    }

    public void showEnterpriseEditView() {
        try {
            FXMLLoader loader = ViewUtils.getFXMLLoader("EnterprisesEditView");
            AnchorPane reportOverview = (AnchorPane) loader.load();

            mainView.setCenter(reportOverview);

            EnterprisesEditController enterpriseController = loader.getController();
            enterpriseController.setMainProject(this);

            doFeaturesStage("Gestión de Empresas", 520, 860);
        } catch (IOException e) {
            DialogUtils.showExceptionDialog(
                    "Error",
                    "Se ha producido un error inesperado",
                    "El detalle de la excepción se presenta \na continuación",
                    new DataException(e));
        }
    }
    
    public void showEnvioCorreo() {
        try {
            FXMLLoader loader = ViewUtils.getFXMLLoader("EnterprisesSendMailView");
            AnchorPane reportOverview = (AnchorPane) loader.load();

            mainView.setCenter(reportOverview);

            EnterprisesSendMailController enterpriseController = loader.getController();
            enterpriseController.setMainProject(this);

            doFeaturesStage("Gestión de Empresas", 520, 860);
        } catch (IOException e) {
            DialogUtils.showExceptionDialog(
                    "Error",
                    "Se ha producido un error inesperado",
                    "El detalle de la excepción se presenta \na continuación",
                    new DataException(e));
        }
    }

    public void showEnterpriseView() {
        try {
            FXMLLoader loader = ViewUtils.getFXMLLoader("EnterprisesView");
            AnchorPane reportOverview = (AnchorPane) loader.load();

            mainView.setCenter(reportOverview);

            EnterprisesController enterpriseController = loader.getController();
            enterpriseController.setMainProject(this);

            doFeaturesStage("Gestión de Empresas", 720, 860);
        } catch (IOException e) {
            DialogUtils.showExceptionDialog(
                    "Error",
                    "Se ha producido un error inesperado",
                    "El detalle de la excepción se presenta \na continuación",
                    new DataException(e));
        }
    }

    public void showEditClientView() {
        try {
            FXMLLoader loader = ViewUtils.getFXMLLoader("ClientsEditView");
            AnchorPane reportOverview = (AnchorPane) loader.load();

            mainView.setCenter(reportOverview);

            ClientEditController enterpriseController = loader.getController();
            enterpriseController.setMainProject(this);

            doFeaturesStage("Gestión de Clientes", 360, 650);
        } catch (IOException e) {
            DialogUtils.showExceptionDialog(
                    "Error",
                    "Se ha producido un error inesperado",
                    "El detalle de la excepción se presenta \na continuación",
                    new DataException(e));
        }
    }

    public void showListReportClientsView() {
        try {
            FXMLLoader loader = ViewUtils.getFXMLLoader("ListClientsView");
            AnchorPane reportOverview = (AnchorPane) loader.load();

            mainView.setCenter(reportOverview);

            ListClientsController listClientsController = loader.getController();
            listClientsController.setMainProject(this);

            doFeaturesStage("Lista de Clientes", 660, 850);
        } catch (IOException e) {
            DialogUtils.showExceptionDialog(
                    "Error",
                    "Se ha producido un error inesperado",
                    "El detalle de la excepción se presenta \na continuación",
                    new DataException(e));
        }
    }
    
    public void showListReportEmpresasView() {
        try {
            FXMLLoader loader = ViewUtils.getFXMLLoader("ListEmpresasView");
            AnchorPane reportOverview = (AnchorPane) loader.load();

            mainView.setCenter(reportOverview);

            ListEmpresasController listEmpresasController = loader.getController();
            listEmpresasController.setMainProject(this);

            doFeaturesStage("Lista de Empresas", 660, 850);
        } catch (IOException e) {
            DialogUtils.showExceptionDialog(
                    "Error",
                    "Se ha producido un error inesperado",
                    "El detalle de la excepción se presenta \na continuación",
                    new DataException(e));
        }
    }

    public void showListReportPaymentsView() {
        try {
            FXMLLoader loader = ViewUtils.getFXMLLoader("ListPaymentsView");
            AnchorPane reportOverview = (AnchorPane) loader.load();

            mainView.setCenter(reportOverview);

            ListPaymentsController listPaymentsController = loader.getController();
            listPaymentsController.setMainProject(this);

            doFeaturesStage("Lista de Abonos", 420, 660);
        } catch (IOException e) {
            DialogUtils.showExceptionDialog(
                    "Error",
                    "Se ha producido un error inesperado",
                    "El detalle de la excepción se presenta \na continuación",
                    new DataException(e));
        }
    }

    public void showListReportDebtorsView() {
        try {
            FXMLLoader loader = ViewUtils.getFXMLLoader("ListDebtorsView");
            AnchorPane reportOverview = (AnchorPane) loader.load();

            mainView.setCenter(reportOverview);

            ListDebtorsController listDebtorsController = loader.getController();
            listDebtorsController.setMainProject(this);

            doFeaturesStage("Lista de Deudores", 420, 660);
        } catch (IOException e) {
            DialogUtils.showExceptionDialog(
                    "Error",
                    "Se ha producido un error inesperado",
                    "El detalle de la excepción se presenta \na continuación",
                    new DataException(e));
        }
    }

    private void doFeaturesStage(String title, int height, int width) {
        this.primaryStage.setTitle(title);
        this.primaryStage.setMinHeight(height);
        this.primaryStage.setMinWidth(width);
        this.primaryStage.setMaxHeight(this.primaryStage.getMinHeight());
        this.primaryStage.setMaxWidth(this.primaryStage.getMinWidth());
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
