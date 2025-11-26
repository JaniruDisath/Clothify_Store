package student.controller.POSController.Elements.paymentGate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import student.controller.CashierMainController;
import student.model.dto.transaction.CardTransaction;
import student.model.dto.transaction.CashTransaction;
import student.model.dto.transaction.PaymentTransactionDTO;
import student.services.newWindow.WindowLoader;
import student.services.orderTransaction.OrderTransactionService;
import student.services.orderTransaction.OrderTransactionServiceImpl;
import student.singleton.CartManager;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CardGate implements Initializable {

    private OrderTransactionService service = new OrderTransactionServiceImpl();

    @FXML
    private ComboBox<String> selectABankComboBox;

    private final List<String> BANK_LIST = List.of(
            "Commercial Bank",
            "Sampath Bank",
            "HNB (Hatton National Bank)",
            "BOC (Bank of Ceylon)",
            "People's Bank"
    );


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
        if (!isCardNumberValid || !isBankSelected ) {
            return;
        }
        exit(event);
        CashierMainController.getInstance().loadUI("/view/pages/cashier/pos/LoyaltyCustomer.fxml");
        service.addOrder(null, new CardTransaction("", tf_CustomerNumber.getText(), getSelectedBank(), "Approved"));
    }

    private void exit(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private boolean isCardNumberValid = false;

    private void setupCardNumberValidation() {

        tf_CustomerNumber.textProperty().addListener((obs, oldVal, newVal) -> {

            // Remove all non-digits
            String digitsOnly = newVal.replaceAll("[^0-9]", "");

            // Limit to max 16 digits
            if (digitsOnly.length() > 16) {
                digitsOnly = digitsOnly.substring(0, 16);
            }

            // Auto-format: group by 4 digits → "#### #### #### ####"
            StringBuilder formatted = new StringBuilder();
            for (int i = 0; i < digitsOnly.length(); i++) {
                if (i > 0 && i % 4 == 0) {
                    formatted.append(" ");
                }
                formatted.append(digitsOnly.charAt(i));
            }

            // Avoid infinite loop: only update if changed
            if (!formatted.toString().equals(newVal)) {
                int caretPos = tf_CustomerNumber.getCaretPosition();
                tf_CustomerNumber.setText(formatted.toString());

                // Restore caret to end
                tf_CustomerNumber.positionCaret(formatted.length());
            }

            // Validation: Check if exactly 16 digits
            if (digitsOnly.length() == 16) {
                tf_CustomerNumber.setStyle("-fx-border-color: green;");
                isCardNumberValid = true;
            } else {
                tf_CustomerNumber.setStyle("-fx-border-color: red;");
                isCardNumberValid = false;
            }
        });
    }

    private boolean isBankSelected = false;

    private void setupBankSelection() {

        // Load banks into the ComboBox
        selectABankComboBox.getItems().setAll(BANK_LIST);

        // Listener for selection changes
        selectABankComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null || newVal.isEmpty()) {
                selectABankComboBox.setStyle("-fx-border-color: red;");
                isBankSelected = false;
            } else {
                selectABankComboBox.setStyle("-fx-border-color: green;");
                isBankSelected = true;
            }
        });
    }

    public String getSelectedBank() {
        return selectABankComboBox.getValue(); // returns null if nothing selected
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupCardNumberValidation();
        setupBankSelection();
        totalLabel.setText(String.valueOf((CartManager.getInstance().getTotal()-CartManager.getInstance().getTotalDiscount())));
    }
}
