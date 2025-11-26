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

    // 🌟 Reference counter (how many popups are open)
    private static int popupCount = 0;

    public static void show(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(WindowLoader.class.getResource(fxmlPath));
            Parent root = loader.load();

            Stage popup = new Stage();
            popup.setScene(new Scene(root));

            popup.initStyle(StageStyle.UNDECORATED);
            popup.initModality(Modality.WINDOW_MODAL);
            popup.initOwner(CashierMainController.getStage());
            popup.setAlwaysOnTop(true);
            popup.setResizable(false);

            // 🔥 INCREASE POPUP COUNT AND APPLY BLUR
            popupCount++;
            applyBlur();

            // When popup closes
            popup.setOnHidden(e -> {
                popupCount--;
                if (popupCount <= 0) {
                    removeBlur();
                }
            });

            popup.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void applyBlur() {
        CashierMainController.getInstance()
                .getContentArea()
                .setEffect(blurEffect);
    }

    private static void removeBlur() {
        CashierMainController.getInstance()
                .getContentArea()
                .setEffect(null);
        popupCount = 0;
    }
}
