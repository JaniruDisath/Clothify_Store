package student.controller.POSController.Elements;

import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import student.controller.CashierMainController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import student.model.dto.LoyaltyCustomer;
import student.services.db.loyaltyCustomer.LoyaltyCustomerService;
import student.services.db.loyaltyCustomer.LoyaltyCustomerServiceImpl;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoyaltyCustomerController implements Initializable {

    @FXML
    private TextField tf_LoyaltyNumber;

    @FXML
    private Text status_label;

    private final LoyaltyCustomerService service = new LoyaltyCustomerServiceImpl();
    private boolean isNumberValid = false;

    @FXML
    void onEnter(MouseEvent event) throws IOException {

        String number = tf_LoyaltyNumber.getText().trim();

        if (number.isEmpty()) {
            setStatus("Phone number is required", Color.RED);
            return;
        }

        if (!isNumberValid) {
            setStatus("Enter a valid 10-digit phone number", Color.RED);
            return;
        }

        LoyaltyCustomer loyaltyCustomer = service.getLoyaltyCustomer(number);

        if (loyaltyCustomer == null) {
            setStatus("Loyalty Customer Not Found", Color.RED);
            return;
        }

        // SUCCESS
        CashierMainController.getInstance().loadPOSUI(loyaltyCustomer);
    }

    private void setStatus(String text, Color color) {
        status_label.setText(text);
        status_label.setFill(color);
    }

    @FXML
    void onNotLoyaltyMemberClick(MouseEvent event) {
        CashierMainController.getInstance()
                .loadUI("/view/pages/cashier/pos/AddLoyaltyCustomer.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupNumberValidation();
    }

    private void setupNumberValidation() {
        String NUMBER_REGEX = "^[0-9]{10}$";
        tf_LoyaltyNumber.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.matches(NUMBER_REGEX)) {
                tf_LoyaltyNumber.setStyle("-fx-border-color: green;");
                isNumberValid = true;
            } else {
                tf_LoyaltyNumber.setStyle("-fx-border-color: red;");
                isNumberValid = false;
            }
        });
    }
}


