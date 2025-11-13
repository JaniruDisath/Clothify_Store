package controller.POSController.Elements;

import controller.CashierMainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddLoyaltyCustomer {

    @FXML
    private TextField tf_LoyaltyEmail;

    @FXML
    private TextField tf_LoyaltyName;

    @FXML
    private TextField tf_LoyaltyNumber;

    @FXML
    void onButtonLater(ActionEvent event) {
        CashierMainController.getInstance().loadUI("/view/pages/cashier/pos/POS.fxml");
    }

    @FXML
    void onEnter(ActionEvent event) {
        //We will check for available customers with similar data
        //Then we add it to the system.
        //Then we pass the user data to the POS system and then perform the task.
        CashierMainController.getInstance().loadUI("/view/pages/cashier/pos/POS.fxml");
    }

}
