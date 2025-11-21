package student.controller.POSController.Elements.paymentGate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import student.controller.CashierMainController;
import student.services.newWindow.WindowLoader;
import student.singleton.CartManager;

public class CardGate {

    @FXML
    private ComboBox<String> selectABankComboBox;

    @FXML
    private TextField tf_CustomerNumber;

    @FXML
    private Text totalLabel;

    @FXML
    void onCancel(ActionEvent event) {
        exit(event);
    }

    @FXML
    void onCardCliked(ActionEvent event) {
        WindowLoader.show("/view/pages/cashier/pos/paymentGate/CashGate.fxml");
        exit(event);
    }

    @FXML
    void onCompleteCheckout(ActionEvent event) {
        exit(event);
        CashierMainController.getInstance().loadUI("/view/pages/cashier/pos/LoyaltyCustomer.fxml");
        CartManager.getInstance().resetCart();
    }

    private void exit(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
