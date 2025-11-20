import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Starter extends Application {
    public static void main(String[] args){
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/view/pages/cashier/CashierMain.fxml"));

        Scene scene = new Scene(root);

        // Disable default ESC exit fullscreen
        stage.setFullScreenExitHint("");      // remove hint text
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH); // disable ESC

        // Remove window decorations (close, minimize, resize buttons)
        stage.initStyle(StageStyle.UNDECORATED);

        // Disable resizing
        stage.setResizable(false);

        // Kiosk-like fullscreen mode
        stage.setFullScreen(true);

        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.F4 && event.isAltDown()) {
                event.consume();// Block ALT+F4
            }
        });

        stage.setScene(scene);
        stage.show();
    }

}
