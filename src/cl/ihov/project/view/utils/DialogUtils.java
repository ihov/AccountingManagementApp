package cl.ihov.project.view.utils;

import cl.ihov.project.common.exception.DataException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class DialogUtils {

    public static final int INFORMATION_DIALOG = 1;
    public static final int WARNING_DIALOG = 2;
    public static final int ERROR_DIALOG = 3;

    public static void showSimpleDialog(int dialogType, String title,
            String headerText, String contentText) {

        Alert alert;
        switch (dialogType) {
            case INFORMATION_DIALOG:
                alert = new Alert(AlertType.INFORMATION);
                break;
            case WARNING_DIALOG:
                alert = new Alert(AlertType.WARNING);
                break;
            case ERROR_DIALOG:
                alert = new Alert(AlertType.ERROR);
                break;
            default:
                alert = new Alert(AlertType.INFORMATION);

        }
        alert.setTitle(title);
        alert.setHeaderText(headerText + "\n");
        alert.setContentText(contentText + "\n");

        alert.showAndWait();
    }

    public static void showExceptionDialog(String title, String headerText,
            String contentText, DataException ex) {

        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText + "\n");
        alert.setContentText(contentText + "\n");

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(textArea, 0, 1);

        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }

    public static boolean showConfirmDialog(String title, String headerText,
            String contentText) {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText + "\n");
        alert.setContentText(contentText + "\n");

        Optional<ButtonType> result = alert.showAndWait();
        return (result.get() == ButtonType.OK);
    }
}
