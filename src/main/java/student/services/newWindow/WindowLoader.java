package student.services.newWindow;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import student.controller.CashierMainController;

public class WindowLoader {

    private static final BoxBlur blurEffect = new BoxBlur(10, 10, 3);

    public static void show(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(WindowLoader.class.getResource(fxmlPath));
            Parent root = loader.load();

            // Apply blur
            CashierMainController.getInstance().getContentArea().setEffect(blurEffect);

            Stage popup = new Stage();
            popup.setScene(new Scene(root));

            popup.initStyle(StageStyle.UNDECORATED);
            popup.initModality(Modality.WINDOW_MODAL);

            // 🔥 THE FIX — Set owner
            popup.initOwner(CashierMainController.getStage());

            popup.setAlwaysOnTop(true);
            popup.setResizable(false);

            popup.setOnCloseRequest(e -> CashierMainController.getInstance().getContentArea().setEffect(null));
            popup.setOnHidden(e -> CashierMainController.getInstance().getContentArea().setEffect(null));

            popup.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
