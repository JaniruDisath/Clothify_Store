import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import student.controller.CashierMainController;

public class Starter extends Application {
    public static void main(String[] args){
        launch();
    }

 @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/pages/cashier/CashierMain.fxml"));
        Parent root = loader.load();

        // Pass main stage to controller for popup ownership
        CashierMainController.setStage(stage);

        Scene scene = new Scene(root);

        stage.setFullScreenExitHint("");
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);

        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setFullScreen(true);

        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.F4 && event.isAltDown()) {
                event.consume();
            }
        });

        stage.setScene(scene);
        stage.show();
    }



}
