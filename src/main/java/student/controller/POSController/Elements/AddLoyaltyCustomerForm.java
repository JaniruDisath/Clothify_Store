package student.controller.POSController.Elements;

import javafx.fxml.Initializable;
import student.controller.CashierMainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import student.model.dto.LoyaltyCustomer;
import student.services.db.loyaltyCustomer.LoyaltyCustomerService;
import student.services.db.loyaltyCustomer.LoyaltyCustomerServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddLoyaltyCustomerForm implements Initializable {

    private final LoyaltyCustomerService service = new LoyaltyCustomerServiceImpl();

    @FXML
    private TextField tf_LoyaltyEmail;

    @FXML
    private TextField tf_LoyaltyName;

    @FXML
    private TextField tf_LoyaltyNumber;

    private boolean isEmailValid = false;
    private boolean isNameValid = false;
    private boolean isNumberValid = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupNameValidation();
        setupEmailValidation();
        setupNumberValidation();
    }

    @FXML
    void onButtonLater(ActionEvent event) {
        CashierMainController.getInstance().loadUI("/view/pages/cashier/pos/POS.fxml");
    }

    @FXML
    void onEnter(ActionEvent event) throws IOException {

        if (!isNameValid || !isNumberValid || !isEmailValid) {
            return;
        }

        LoyaltyCustomer newCustomer = new LoyaltyCustomer(
                tf_LoyaltyNumber.getText(),
                tf_LoyaltyName.getText(),
                tf_LoyaltyEmail.getText()
        );

        LoyaltyCustomer result = service.addLoyaltyCustomer(newCustomer);

        CashierMainController.getInstance().loadPOSUI(result);
    }

    private void setupEmailValidation() {
        String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        tf_LoyaltyEmail.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.matches(EMAIL_REGEX)) {
                tf_LoyaltyEmail.setStyle("-fx-border-color: green;");
                isEmailValid = true;
            } else {
                tf_LoyaltyEmail.setStyle("-fx-border-color: red;");
                isEmailValid = false;
            }
        });
    }

    private void setupNameValidation() {
        String NAME_REGEX = "^[A-Za-z ]+$";
        tf_LoyaltyName.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.matches(NAME_REGEX)) {
                tf_LoyaltyName.setStyle("-fx-border-color: green;");
                isNameValid = true;
            } else {
                tf_LoyaltyName.setStyle("-fx-border-color: red;");
                isNameValid = false;
            }
        });
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
