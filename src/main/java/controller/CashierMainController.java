package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import lombok.Getter;

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

    // --- Initialize and set singleton instance ---
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instance = this; // store reference when controller is created
        loadUI("/view/pages/cashier/pos/LoyaltyCustomer.fxml");
    }
}
