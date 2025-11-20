package student.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import lombok.Getter;
import student.controller.POSController.PosControllerImpl;
import student.model.dto.LoyaltyCustomer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CashierMainController implements Initializable {

    // --- Singleton instance ---
    @Getter
    private static CashierMainController instance;

    // --- FXML Fields ---
    @FXML
    private StackPane contentArea;

    @FXML
    private HBox dashboardElement;

    @FXML
    private HBox inventoryElement;

    @FXML
    private HBox orderElement;

    @FXML
    private HBox exitProgramElement;


    // --- FXML Event Handlers ---
    @FXML
    void onPOSElementClicked(MouseEvent event) {
        loadUI("/view/pages/cashier/pos/LoyaltyCustomer.fxml");
    }

    @FXML
    void onCheckHistoryElementClicked(MouseEvent event) {
        loadUI("/view/pages/cashier/CheckHistory.fxml");
    }

    @FXML
    void onInventoryElementClicked(MouseEvent event) {
        loadUI("/view/pages/cashier/Inventory.fxml");
    }

    // --- Load new content into contentArea ---
    public void loadUI(String fxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            contentArea.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadPOSUI(LoyaltyCustomer loyaltyCustomer) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/pages/cashier/pos/POS.fxml"));
        Parent root = loader.load();

        PosControllerImpl controller = loader.getController();
        controller.setLoyaltyCustomer(loyaltyCustomer);

        contentArea.getChildren().setAll(root);
    }


    // --- Initialize and set singleton instance ---
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instance = this; // store reference when controller is created
        loadUI("/view/pages/cashier/pos/LoyaltyCustomer.fxml");
    }


    @FXML
    void onExitProgramElement(MouseEvent event) {
        Platform.exit();
    }
}
