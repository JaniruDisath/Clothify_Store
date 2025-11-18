package student.controller.POSController.Elements.paymentGate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class CashGate {

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

    @FXML
    private Text totalLabel;

    @FXML
    void onCancel(ActionEvent event) {

    }

    @FXML
    void onCardCliked(ActionEvent event) {

    }

    @FXML
    void onCompleteCheckout(ActionEvent event) {

    }

}
