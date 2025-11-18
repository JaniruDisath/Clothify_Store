package student.controller.POSController.Elements;

import student.controller.CashierMainController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoyaltyCustomerController {

    @FXML
    private TextField tf_LoyaltyNumber;

    @FXML
    void onEnter(MouseEvent event) {
        //Go to services
        //Check for availability
        //If not present show a dialog box that it's not available.
        //And ask to enter again
        //If present load the POS
    }

    @FXML
    void onNotLoyaltyMemberClick(MouseEvent event) {
        //Open Add Loyalty Customer
        CashierMainController.getInstance().loadUI("/view/pages/cashier/pos/AddLoyaltyCustomer.fxml");
    }

}
