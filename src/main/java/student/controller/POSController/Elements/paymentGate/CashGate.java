package student.controller.POSController.Elements.paymentGate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import student.controller.CashierMainController;
import student.services.newWindow.WindowLoader;
import student.singleton.CartManager;

import java.net.URL;
import java.util.ResourceBundle;

public class CashGate implements Initializable {

    @FXML
    private Label numberOfFiftiesLabel;

    @FXML
    private Label numberOfFiveHundradLabel;

    @FXML
    private Label numberOfFiveThousandLabel;

    @FXML
    private Label numberOfFivesLabel;

    @FXML
    private Label numberOfHundradsLabel;

    @FXML
    private Label numberOfOnesLabel;

    @FXML
    private Label numberOfTensLabel;

    @FXML
    private Label numberOfThousandLabel;

    @FXML
    private Label numberOfTwentiesLabel;

    @FXML
    private Label numberOfTwosLabel;

    @FXML
    private Label returnChangeLabel;

    @FXML
    private TextField tf_AmountCustomerGave;

    private Integer total;

    @FXML
    private Text totalLabel;

    @FXML
    void onCancel(ActionEvent event) {
        exit(event);
    }

    @FXML
    void onCardCliked(ActionEvent event) {
        WindowLoader.show("/view/pages/cashier/pos/paymentGate/CardGate.fxml");
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupAmountValidation();
        total = (int)(CartManager.getInstance().getTotal()-CartManager.getInstance().getDiscount());
    }


    private void setupAmountValidation() {
        tf_AmountCustomerGave.textProperty().addListener((obs, oldValue, newValue) -> {

            // Allow only digits
            if (!newValue.matches("\\d*")) {
                tf_AmountCustomerGave.setText(newValue.replaceAll("[^\\d]", ""));
                return;
            }

            if (!newValue.isEmpty()) {
                calculateChange();
            } else {
                clearAllChangeFields();
            }
        });
    }

    private void calculateChange() {
        try {
            int customerAmount = Integer.parseInt(tf_AmountCustomerGave.getText());

            if (customerAmount < total) {
                returnChangeLabel.setText("Insufficient");
                clearAllChangeFields();
                return;
            }

            int change = customerAmount - total;
            returnChangeLabel.setText("Rs. " + change);

            updateDenominations(change);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void updateDenominations(int change) {

        int remaining = change;

        int fiveThousand = remaining / 5000;
        remaining %= 5000;

        int thousand = remaining / 1000;
        remaining %= 1000;

        int fiveHundred = remaining / 500;
        remaining %= 500;

        int hundred = remaining / 100;
        remaining %= 100;

        int fifty = remaining / 50;
        remaining %= 50;

        int twenty = remaining / 20;
        remaining %= 20;

        int ten = remaining / 10;
        remaining %= 10;

        int five = remaining / 5;
        remaining %= 5;

        int two = remaining / 2;
        remaining %= 2;

        int one = remaining;

        numberOfFiveThousandLabel.setText("x" + fiveThousand);
        numberOfThousandLabel.setText("x" + thousand);
        numberOfFiveHundradLabel.setText("x" + fiveHundred);
        numberOfHundradsLabel.setText("x" + hundred);
        numberOfFiftiesLabel.setText("x" + fifty);
        numberOfTwentiesLabel.setText("x" + twenty);
        numberOfTensLabel.setText("x" + ten);
        numberOfFivesLabel.setText("x" + five);
        numberOfTwosLabel.setText("x" + two);
        numberOfOnesLabel.setText("x" + one);
    }
    private void clearAllChangeFields() {
        returnChangeLabel.setText("Rs. 0");

        numberOfFiveThousandLabel.setText("x0");
        numberOfThousandLabel.setText("x0");
        numberOfFiveHundradLabel.setText("x0");
        numberOfHundradsLabel.setText("x0");
        numberOfFiftiesLabel.setText("x0");
        numberOfTwentiesLabel.setText("x0");
        numberOfTensLabel.setText("x0");
        numberOfFivesLabel.setText("x0");
        numberOfTwosLabel.setText("x0");
        numberOfOnesLabel.setText("x0");
    }



}
